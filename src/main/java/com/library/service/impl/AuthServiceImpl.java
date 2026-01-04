package com.library.service.impl;

import com.library.config.JwtProvider;
import com.library.domain.UserRole;
import com.library.exception.UserException;
import com.library.mapper.UserMapper;
import com.library.modal.User;
import com.library.payload.dto.UserDTO;
import com.library.payload.response.AuthResponse;
import com.library.repository.UserRepository;
import com.library.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;
    private final CustomUserServiceImpl customUserServiceImpl;


    @Override
    public AuthResponse login(String username, String password) throws UserException {
        Authentication authentication = authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        String role = authorities.iterator().next().getAuthority();
        String token = jwtProvider.generateToken(authentication);

        User user = userRepository.findByEmail(username);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse response = new AuthResponse();
        response.setTitle("Login success");
        response.setMessage("Welcome Back" + username);
        response.setJwt(token);
        response.setUser(userMapper.toDTO(user));

        return response;
    }

    private Authentication authenticate(String username, String password) throws UserException {
        UserDetails userDetails = customUserServiceImpl.loadUserByUsername(username);

        if (userDetails == null){
            throw new UserException("user not found with email " + password);
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new UserException("password not match");
        }
        return new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
    }

    @Override
    public AuthResponse signup(UserDTO req) throws UserException {
        User user = userRepository.findByEmail(req.getEmail());

        if (user != null){
            throw new UserException("email already registered");
        }
        User createdUser = new User();
        createdUser.setEmail(req.getEmail());
        createdUser.setPassword(passwordEncoder.encode(req.getPassword()));
        createdUser.setPhone(req.getPhone());
        createdUser.setFullName(req.getFullName());
        createdUser.setLastLogin(LocalDateTime.now());
        createdUser.setRole(UserRole.USER);

        User savedUser = userRepository.save(createdUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(), savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = jwtProvider.generateToken(auth);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setTitle("Welcome " + createdUser.getFullName());
        authResponse.setMessage("register success");
        authResponse.setUser(userMapper.toDTO(savedUser));
        return authResponse;
    }

    @Override
    public void createPasswordResetToken(String email) throws UserException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UserException("user not found with given email");
        }

    }

    @Override
    public void resetPassword(String token, String newPassword) {

    }
}
