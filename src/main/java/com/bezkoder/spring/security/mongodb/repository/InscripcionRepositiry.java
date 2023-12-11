package com.bezkoder.spring.security.mongodb.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.mongodb.models.Inscripcion;

@Repository
public interface InscripcionRepositiry extends MongoRepository<Inscripcion, ObjectId> {

    @Query("{'IdUser': ?0, 'IdEv': ?1}")
    Inscripcion findInscripcionByIdUserAndIdEv(ObjectId idUser, ObjectId idEv);

    List<Inscripcion> findInscripcionByidUser(ObjectId idUser);
}