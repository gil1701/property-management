package com.oproser.property_management.controller;

import com.oproser.property_management.dto.PropertyDTO;
import com.oproser.property_management.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @PostMapping("/save")
    public ResponseEntity<PropertyDTO> saveProeprty(@RequestBody PropertyDTO property) {
        property =  propertyService.saveProperty(property);
        return new ResponseEntity<>(property, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<PropertyDTO> propertyDTOList = propertyService.getAllProperties();
        return new ResponseEntity<>(propertyDTOList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO> upsateProperty(@RequestBody PropertyDTO property, @PathVariable Long id) {
        PropertyDTO dto =  propertyService.updateProperty(property, id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProperty(@PathVariable Long id) {
        if(propertyService.deleteProperty(id))
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id) {
        PropertyDTO dto =  propertyService.getPropertyById(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
