package com.bezkoder.spring.security.mongodb.controllers;

import java.util.List;
import java.util.Optional;

//import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.mongodb.models.Doctor;
import com.bezkoder.spring.security.mongodb.security.services.DoctorService;

@RestController
@RequestMapping("/api/doctors/")
public class DoctorControler {
    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> GetAllDoctors() {
        return new ResponseEntity<List<Doctor>>(doctorService.AllDoctors(), HttpStatus.OK);
    }

    @GetMapping("/{idDc}")
    public ResponseEntity<Optional<Doctor>> GetDoctor(@PathVariable String idDc) {
        return new ResponseEntity<Optional<Doctor>>(doctorService.SearchDoctor(idDc), HttpStatus.OK);
    }

    @GetMapping("/ByEspecialidad/{especialidad}")
    public ResponseEntity<Optional<Doctor>> GetByEspecialidad(@PathVariable String especialidad) {
        especialidad = especialidad.replace("+", " ");
        return new ResponseEntity<Optional<Doctor>>(doctorService.SearchDoctorByEspecialidad(especialidad),
                HttpStatus.OK);
    }

}
