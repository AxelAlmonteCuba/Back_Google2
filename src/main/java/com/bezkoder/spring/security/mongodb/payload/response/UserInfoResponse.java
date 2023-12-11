package com.bezkoder.spring.security.mongodb.payload.response;

import java.util.List;

public class UserInfoResponse {
  private String id;
  private String username;
  private String email;
  private List<String> roles;

  private String nombre;
  private String apellido;
  private String telefono;
  private int edad;
  private String fechaNacimiento;
  public UserInfoResponse(String id, String username, String email, List<String> roles,
                          String nombre, String apellido, String telefono, int edad, String fechaNacimiento) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
    this.nombre = nombre;
    this.apellido = apellido;
    this.telefono = telefono;
    this.edad = edad;
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public String getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(String fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }
}
