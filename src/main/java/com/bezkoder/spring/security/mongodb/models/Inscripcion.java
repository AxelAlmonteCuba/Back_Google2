package com.bezkoder.spring.security.mongodb.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Inscripcion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inscripcion {

    @Id
    private ObjectId id;

    private ObjectId idUser;
    private ObjectId idEv;

    public Inscripcion(ObjectId idUser, ObjectId idEv) {

        this.idUser = idUser;
        this.idEv = idEv;
    }

    public ObjectId getIdEv() {
        return this.idEv;
    }

}
