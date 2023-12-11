package com.bezkoder.spring.security.mongodb.security.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.mongodb.models.Evento;
import com.bezkoder.spring.security.mongodb.models.Inscripcion;
import com.bezkoder.spring.security.mongodb.models.Respuesta;
import com.bezkoder.spring.security.mongodb.repository.EventoRepository;
import com.bezkoder.spring.security.mongodb.repository.InscripcionRepositiry;

@Service
public class InscripcionService {
    @Autowired
    InscripcionRepositiry inscripcionRepositiry;

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public Respuesta CrearInscripcion(ObjectId IdUser, ObjectId IdEv) {
        try {
            Inscripcion inscripcion = new Inscripcion(IdUser, IdEv);
            inscripcionRepositiry.insert(inscripcion);
            return new Respuesta("Inscrito exitosamente");

        } catch (Exception e) {
            return new Respuesta("Error de Servidor");
        }

    }

    public Respuesta EliminarInscripcion(ObjectId IdUser, ObjectId IdEv) {
        try {
            Inscripcion inscripcion = inscripcionRepositiry.findInscripcionByIdUserAndIdEv(IdUser, IdEv);
            inscripcionRepositiry.delete(inscripcion);
            mongoTemplate.update(Evento.class)
                    .matching(Criteria.where("idEv").is(IdEv))
                    .apply(new Update().inc("aforo", +1))
                    .first();
            return new Respuesta("Desincrito exitosamente");
        } catch (Exception e) {
            return new Respuesta("Error de servidor");
        }

    }

    public List<Evento> obtenerEventoByIdUser(ObjectId idUser) {
        List<Inscripcion> inscripciones = inscripcionRepositiry.findInscripcionByidUser(idUser);
        List<ObjectId> IdsEventos = new ArrayList<ObjectId>();
        List<Evento> Eventos = new ArrayList<Evento>();
        for (Inscripcion inscripcion : inscripciones) {
            IdsEventos.add(inscripcion.getIdEv());
        }
        for (ObjectId idEv : IdsEventos) {
            Eventos.add(eventoRepository.findEventoById(idEv).orElseThrow(RuntimeException::new));
        }
        return Eventos;

    }
}
