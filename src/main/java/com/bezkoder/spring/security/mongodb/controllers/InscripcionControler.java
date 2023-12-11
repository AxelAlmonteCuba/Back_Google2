package com.bezkoder.spring.security.mongodb.controllers;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.mongodb.models.Evento;
import com.bezkoder.spring.security.mongodb.models.Respuesta;
import com.bezkoder.spring.security.mongodb.security.services.EventoService;
import com.bezkoder.spring.security.mongodb.security.services.InscripcionService;

@RestController
@RequestMapping("/api/enrollments")
public class InscripcionControler {
    @Autowired
    InscripcionService inscripcionService;

    @Autowired
    EventoService eventoService;

    @PostMapping()
    public ResponseEntity<Respuesta> registerUsuario(@RequestBody Map<String, ObjectId> payload) {
        return new ResponseEntity<Respuesta>(
                eventoService.Registrarse(payload.get("idUser"), payload.get("idEv")),
                HttpStatus.OK);
    }

    @PostMapping("/unenroll")
    public ResponseEntity<Respuesta> EliminarInscripcion(@RequestBody Map<String, ObjectId> payload) {
        return new ResponseEntity<Respuesta>(
                inscripcionService.EliminarInscripcion(payload.get("idUser"), payload.get("idEv")), HttpStatus.OK);
    }

    @GetMapping("/{UserId}")
    public ResponseEntity<List<Evento>> GetAllEventosByUser(@PathVariable ObjectId IdUser) {
        return new ResponseEntity<List<Evento>>(inscripcionService.obtenerEventoByIdUser(IdUser), HttpStatus.OK);
    }
}
