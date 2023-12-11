package com.bezkoder.spring.security.mongodb.models;

public class Respuesta {
    private String mensaje;

    public Respuesta(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.mensaje = Mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
