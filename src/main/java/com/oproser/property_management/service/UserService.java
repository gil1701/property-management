package com.oproser.property_management.service;

import com.oproser.property_management.dto.UserDTO;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO loginUser(String email, String password);
}
