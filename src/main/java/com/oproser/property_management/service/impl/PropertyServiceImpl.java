package com.oproser.property_management.service.impl;

import com.oproser.property_management.converter.PropertyConverter;
import com.oproser.property_management.dto.PropertyDTO;
import com.oproser.property_management.entity.PropertyEntity;
import com.oproser.property_management.repository.PropertyRepository;
import com.oproser.property_management.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    private final PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        PropertyEntity pe = propertyConverter.convertDTOToEntity(propertyDTO);
        pe = propertyRepository.save(pe);
        propertyDTO = propertyConverter.convertEntitytoDTO(pe);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> listOfProps = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propertyDTOList = new ArrayList<>();
        for (PropertyEntity propertyEntity : listOfProps) {
            propertyDTOList.add(propertyConverter.convertEntitytoDTO(propertyEntity));
        }
        return propertyDTOList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(id);

        if (optEn.isPresent()) {
            PropertyEntity pe = updateValidFields(propertyDTO, optEn.get());
            propertyDTO = propertyConverter.convertEntitytoDTO(pe);
            propertyRepository.save(pe);
            return propertyDTO;
        }
        return null;
    }

    @Override
    public boolean deleteProperty(Long id) {
        if (propertyRepository.existsById(id)) {
            propertyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public PropertyDTO getPropertyById(Long id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(id);
        return optEn.map(propertyConverter::convertEntitytoDTO).orElse(null);
    }

    private PropertyEntity updateValidFields(PropertyDTO propertyDTO, PropertyEntity pe) {
        String title = propertyDTO.getTitle() != null && !(propertyDTO.getTitle().isBlank() || propertyDTO.getTitle().isEmpty()) ? propertyDTO.getTitle() : "";
        String desc = propertyDTO.getDescription() != null && !(propertyDTO.getDescription().isBlank() || propertyDTO.getDescription().isEmpty()) ? propertyDTO.getDescription() : "";
        double price = propertyDTO.getPrice() != 0 ? propertyDTO.getPrice() : 0;
        String address = propertyDTO.getAddress() != null && !(propertyDTO.getAddress().isBlank() || propertyDTO.getAddress().isEmpty()) ? propertyDTO.getAddress() : "";
        if (!title.isEmpty()) pe.setTitle(propertyDTO.getTitle());
        if (!desc.isEmpty()) pe.setDescription(propertyDTO.getDescription());
        if (price != 0) pe.setPrice(propertyDTO.getPrice());
        if (!address.isEmpty()) pe.setAddress(propertyDTO.getAddress());
        return pe;
    }
}
