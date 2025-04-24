package com.oproser.property_management.converter;

import com.oproser.property_management.dto.UserDTO;
import com.oproser.property_management.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity convertDTOtoEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setOwnerName(userDTO.getOwnerName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setOwnerEmail(userDTO.getOwnerEmail());
        return userEntity;
    }

    public UserDTO convertEntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setOwnerName(userEntity.getOwnerName());
        userDTO.setId(userEntity.getId());
        userDTO.setPhone(userEntity.getPhone());
        userDTO.setOwnerEmail(userEntity.getOwnerEmail());
        return userDTO;
    }
}
