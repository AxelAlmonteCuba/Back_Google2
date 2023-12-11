package com.bezkoder.spring.security.mongodb.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.mongodb.models.Evento;

@Repository
public interface EventoRepository extends MongoRepository<Evento, ObjectId> {
    Optional<Evento> findEventoById(ObjectId idEv);

    @Query(value = "{ '_idEv' : ?0 }", fields = "{ 'aforo' : 1 }")
    Integer findAforoByIdEv(String id);

}
