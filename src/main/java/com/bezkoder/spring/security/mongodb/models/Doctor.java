package com.bezkoder.spring.security.mongodb.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "doctores")
@Data
@AllArgsConstructor // con parametros
@NoArgsConstructor // sin parametros

public class Doctor {
    @Id // declaro q es id unico
    private ObjectId id;

    private String idDc;
    private String nombre;
    private String correo_electronico;
    private String telefono;
    private String direccion_consulta;
    private String numero_licencia;
    private String entidad_licencia;
    private String credenciales;
    private String especialidad;
    private String experiencia;
    private Object horario_consulta;
    private boolean disponibilidadEnLinea;
    private List<String> idiomas_hablados;
    private String educacion_continua;
    private String politica_pago;
    private String metodos_tratamiento;
    private String enfoque_terapeutico;
    private Object contacto_emergencia;

    @Field("eventos")
    private List<ObjectId> eventos;
}