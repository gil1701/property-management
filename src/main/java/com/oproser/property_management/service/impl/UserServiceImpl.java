package com.oproser.property_management.service.impl;

import com.oproser.property_management.converter.UserConverter;
import com.oproser.property_management.dto.UserDTO;
import com.oproser.property_management.entity.UserEntity;
import com.oproser.property_management.exception.BusinessException;
import com.oproser.property_management.exception.ErrorModel;
import com.oproser.property_management.repository.UserRepository;
import com.oproser.property_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserConverter userConverter;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        Optional<UserEntity> optUe = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if (optUe.isPresent()) {
            List<ErrorModel> errorModels = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXISTS");
            errorModel.setMessage("Email already exists");
            errorModels.add(errorModel);
            throw new BusinessException(errorModels);
        }
        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
        userEntity =  userRepository.save(userEntity);
        return userConverter.convertEntityToDTO(userEntity);
    }

    @Override
    public UserDTO loginUser(String email, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);
        if (optionalUserEntity.isPresent()) {
            userDTO = userConverter.convertEntityToDTO(optionalUserEntity.get());
        } else {
            List<ErrorModel> errorModels = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGING");
            errorModel.setMessage("Incorrect username or password");
            errorModels.add(errorModel);
            throw new BusinessException(errorModels);
        }
        return userDTO;
    }
}
