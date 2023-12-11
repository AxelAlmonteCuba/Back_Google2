package com.bezkoder.spring.security.mongodb.security.services;

import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.mongodb.models.Doctor;
import com.bezkoder.spring.security.mongodb.models.Evento;
import com.bezkoder.spring.security.mongodb.models.Respuesta;
import com.bezkoder.spring.security.mongodb.models.RespuestaUpdate;
import com.bezkoder.spring.security.mongodb.repository.EventoRepository;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private InscripcionService inscripcionService;

    public List<Evento> AllEventos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> findEventoById(ObjectId idEv) {
        return eventoRepository.findEventoById(idEv);
    }

    public Evento CreateEvento(String nombre_Ev, String idDc, String fechaHoraInicio,
            String fechaHoraFin, int aforo) {
        Evento nuevoEvento = new Evento(nombre_Ev, idDc, fechaHoraInicio, fechaHoraFin, aforo);
        try {
            eventoRepository.insert(nuevoEvento);
            ObjectId idEv = nuevoEvento.getId();
            mongoTemplate.update(Doctor.class)
                    .matching(Criteria.where("idDc").is(idDc))
                    .apply(new Update().push("eventos").value(idEv))
                    .first();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nuevoEvento;
    }

    public RespuestaUpdate ActualizarEvento(ObjectId Id, String nombre_Ev, String idDc, String fechaHoraInicio,
            String fechaHoraFin, int aforo) {
        try {
            mongoTemplate.update(Evento.class)
                    .matching(Criteria.where("id").is(Id))
                    .apply(new Update().set("nombre_Ev", nombre_Ev))
                    .first();
            mongoTemplate.update(Evento.class)
                    .matching(Criteria.where("id").is(Id))
                    .apply(new Update().set("idDc", idDc))
                    .first();
            mongoTemplate.update(Evento.class)
                    .matching(Criteria.where("id").is(Id))
                    .apply(new Update().set("fechaHoraInicio", fechaHoraInicio))
                    .first();
            mongoTemplate.update(Evento.class)
                    .matching(Criteria.where("id").is(Id))
                    .apply(new Update().set("fechaHoraFin", fechaHoraFin))
                    .first();
            mongoTemplate.update(Evento.class)
                    .matching(Criteria.where("id").is(Id))
                    .apply(new Update().set("aforo", aforo))
                    .first();

            Evento eventoActualizado = eventoRepository.findEventoById(Id).orElseThrow(RuntimeException::new);
            return new RespuestaUpdate(eventoActualizado, "Actualizado Exitosamente");
        } catch (Exception e) {
            Evento eventoActualizado = eventoRepository.findEventoById(Id).orElseThrow(RuntimeException::new);
            return new RespuestaUpdate(eventoActualizado, "Error al Actualizar");
        }
    }

    public Respuesta EliminarEvento(ObjectId idEv) {
        try {
            Evento evento = eventoRepository.findEventoById(idEv).orElseThrow(RuntimeException::new);
            eventoRepository.delete(evento);
            return new Respuesta("Eliminado");
        } catch (Exception e) {
            return new Respuesta("Error de servidor");
        }
    }

    public Respuesta Registrarse(ObjectId IdUser, ObjectId idEv) {
        Evento evento = eventoRepository.findEventoById(idEv).orElseThrow(RuntimeException::new);
        int aforo = evento.getAforo();

        if (aforo > 0) {
            try {

                mongoTemplate.update(Evento.class)
                        .matching(Criteria.where("idEv").is(idEv))
                        .apply(new Update().inc("aforo", -1))
                        .first();
                return inscripcionService.CrearInscripcion(IdUser, idEv);

            } catch (Exception e) {
                e.printStackTrace();
                return new Respuesta("Error de servidor");

            }
        } else {
            return new Respuesta("Aforo lleno");
        }

    }
}
