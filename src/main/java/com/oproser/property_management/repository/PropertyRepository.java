package com.oproser.property_management.repository;

import com.oproser.property_management.entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

}
