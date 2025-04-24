package com.oproser.property_management.service.impl;

import com.oproser.property_management.converter.UserConverter;
import com.oproser.property_management.dto.UserDTO;
import com.oproser.property_management.entity.UserEntity;
import com.oproser.property_management.repository.UserRepository;
import com.oproser.property_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
        userEntity =  userRepository.save(userEntity);
        return userConverter.convertEntityToDTO(userEntity);
    }

    @Override
    public UserDTO loginUser(String email, String password) {
        return null;
    }
}
