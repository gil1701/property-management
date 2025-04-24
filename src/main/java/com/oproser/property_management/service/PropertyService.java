package com.oproser.property_management.service;

import com.oproser.property_management.dto.PropertyDTO;

import java.util.List;

public interface PropertyService {
    PropertyDTO saveProperty(PropertyDTO propertyDTO);
    List<PropertyDTO> getAllProperties();
    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long id);
    boolean deleteProperty(Long id);
    PropertyDTO getPropertyById(Long id);
}
