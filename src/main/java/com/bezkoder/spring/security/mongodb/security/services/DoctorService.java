package com.bezkoder.spring.security.mongodb.security.services;

import java.util.List;
import java.util.Optional;

//import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.mongodb.models.Doctor;
import com.bezkoder.spring.security.mongodb.repository.DoctorReposite;

@Service

public class DoctorService {
    @Autowired
    private DoctorReposite doctorReposite;

    public List<Doctor> AllDoctors() {
        return doctorReposite.findAll();
    }

    public Optional<Doctor> SearchDoctor(String idDc) {
        return doctorReposite.findDoctorByid(idDc);
    }

    public Optional<Doctor> SearchDoctorByEspecialidad(String especialidad) {
        return doctorReposite.findDoctorByespecialidad(especialidad);
    }

}
