package com.scheduled.repository;

import com.scheduled.model.Plane;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaneRepository extends MongoRepository<Plane, Integer> {
}
