package com.bezkoder.spring.security.mongodb.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.mongodb.models.Evento;
import com.bezkoder.spring.security.mongodb.models.Respuesta;
import com.bezkoder.spring.security.mongodb.models.RespuestaUpdate;
import com.bezkoder.spring.security.mongodb.security.services.EventoService;

@RestController
@RequestMapping("api/events")
public class EventoControler {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> GetAllEventos() {
        return new ResponseEntity<List<Evento>>(eventoService.AllEventos(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Evento>> FindEvento(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<Evento>>(eventoService.findEventoById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Evento> createEveneto(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Evento>(
                eventoService.CreateEvento(payload.get("nombre_Ev"), payload.get("idDc"),
                        payload.get("fechaHoraInicio"), payload.get("fechaHoraFin"),
                        Integer.parseInt(payload.get("aforo"))),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Respuesta> eliminarEvento(@PathVariable ObjectId id) {
        return new ResponseEntity<Respuesta>(eventoService.EliminarEvento(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaUpdate> UpdateEvento(@PathVariable ObjectId id,
            @RequestBody Map<String, String> payload) {
        return new ResponseEntity<RespuestaUpdate>(eventoService.ActualizarEvento(id, payload.get("nombre_Ev"),
                payload.get("idDc"), payload.get("fechaHoraInicio"), payload.get("fechaHoraFin"),
                Integer.parseInt(payload.get("aforo"))), HttpStatus.OK);
    }

}
