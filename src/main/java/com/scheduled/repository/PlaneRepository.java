package com.scheduled.repository;

import com.scheduled.model.Plane;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlaneRepository extends MongoRepository<Plane, Integer> {

    List<Plane> findAllByCompanyNameContainsIgnoreCase(String companyName);

}
