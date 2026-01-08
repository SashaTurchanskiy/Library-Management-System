package com.library.service;

import com.library.modal.User;
import com.library.payload.dto.UserDTO;

import java.util.List;

public interface UserService {
    User getCurrentUser() throws Exception;
    List<UserDTO> getAllUsers();

}
