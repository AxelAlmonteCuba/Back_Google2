package com.bezkoder.spring.security.mongodb.models;

public class RespuestaUpdate {
    private Evento evento;
    private Respuesta respuesta;

    public RespuestaUpdate(Evento evento, String respuesta) {
        this.evento = evento;
        this.respuesta = new Respuesta(respuesta);
    }

}
