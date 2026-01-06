package com.library.controller;

import com.library.exception.UserException;
import com.library.payload.dto.UserDTO;
import com.library.payload.request.ForgotPasswordRequest;
import com.library.payload.request.LoginRequest;
import com.library.payload.request.ResetPasswordRequest;
import com.library.payload.response.ApiResponse;
import com.library.payload.response.AuthResponse;
import com.library.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(@Valid @RequestBody UserDTO req) throws UserException {

        AuthResponse authResponse = authService.signup(req);
        return ResponseEntity.ok(authResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@Valid @RequestBody LoginRequest req) throws UserException {

        AuthResponse authResponse = authService.login(req.getEmail(), req.getPassword());
        return ResponseEntity.ok(authResponse);
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(@Valid @RequestBody ForgotPasswordRequest req) throws UserException {
        authService.createPasswordResetToken(req.getEmail());

        ApiResponse apiResponse = new ApiResponse("A reset link was sent to your email ", true);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@Valid @RequestBody ResetPasswordRequest req) throws Exception {
        authService.resetPassword(req.getToken(), req.getPassword());

        ApiResponse apiResponse = new ApiResponse("Password reset successfully", true);
        return ResponseEntity.ok(apiResponse);
    }

}
