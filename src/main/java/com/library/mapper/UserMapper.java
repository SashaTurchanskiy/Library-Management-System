package com.library.mapper;

import com.library.modal.Genre;
import com.library.modal.User;
import com.library.payload.dto.GenreDTO;
import com.library.payload.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

    List<UserDTO> toDTOList(List<User> userList);

    Set<UserDTO> toDTOSet(Set<User> userSet);
}
