package com.bezkoder.spring.security.mongodb.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.mongodb.models.Doctor;

@Repository

public interface DoctorReposite extends MongoRepository<Doctor, ObjectId> {
    Optional<Doctor> findDoctorByid(String id);

    Optional<Doctor> findDoctorByespecialidad(String especialidad);
}
