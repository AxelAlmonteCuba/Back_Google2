package com.bezkoder.spring.security.mongodb.models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Eventos")
@Data
@AllArgsConstructor // con parametros
@NoArgsConstructor // sin parametros

public class Evento {

    @Id
    private ObjectId id;

    private String idEv;
    private String nombre_Ev;
    private String idDc;
    private String fechaHoraInicio;
    private String fechaHoraFin;
    private int aforo;
    private List<String> participantes;

    public Evento(String nombre_Ev, String idDc, String fechaHoraInicio, String fechaHoraFin, int aforo) {
        this.nombre_Ev = nombre_Ev;
        this.idDc = idDc;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.aforo = aforo;
        participantes = new ArrayList<String>();
    }

    public int getAforo() {
        return aforo;
    }

    public ObjectId getId() {
        return id;
    }
}
