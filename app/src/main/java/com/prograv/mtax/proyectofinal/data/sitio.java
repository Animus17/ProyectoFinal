package com.prograv.mtax.proyectofinal.data;

/**
 * Created by mtax on 03/06/2018.
 */

public class sitio {
    private String ID;
    private String nombre;
    private int departamento;
    private int municipio;
    private String longitud;
    private String latitud;
    private String foto1;
    private String foto2;
    private String foto3;
    private String despcripcion;
    private int amenidad1;
    private int amenidad2;
    private int amenidad3;
    private int tipo;

    public sitio(){
        this.ID = "";
        this.nombre = "";
        this.departamento = 0;
        this.municipio = 0;
        this.longitud = "";
        this.latitud = "";
        this.foto1 = "";
        this.foto2 = "";
        this.foto3 = "";
        this.despcripcion = "";
        this.amenidad1 = 0;
        this.amenidad2 = 0;
        this.amenidad3 = 0;
        this.tipo = 0;
    }

    public sitio(String id, String nombre, int departamento, int municipio, String longitud, String latitud, String foto1, String foto2, String foto3, String despcripcion, int amenidad1, int amenidad2, int amenidad3, int tipo){
        this.ID = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.municipio = municipio;
        this.longitud = longitud;
        this.latitud = latitud;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.foto3 = foto3;
        this.despcripcion = despcripcion;
        this.amenidad1 = amenidad1;
        this.amenidad2 = amenidad2;
        this.amenidad3 = amenidad3;
        this.tipo = tipo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public int getMunicipio() {
        return municipio;
    }

    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public String getDespcripcion() {
        return despcripcion;
    }

    public void setDespcripcion(String despcripcion) {
        this.despcripcion = despcripcion;
    }

    public int getAmenidad1() {
        return amenidad1;
    }

    public void setAmenidad1(int amenidad1) {
        this.amenidad1 = amenidad1;
    }

    public int getAmenidad2() {
        return amenidad2;
    }

    public void setAmenidad2(int amenidad2) {
        this.amenidad2 = amenidad2;
    }

    public int getAmenidad3() {
        return amenidad3;
    }

    public void setAmenidad3(int amenidad3) {
        this.amenidad3 = amenidad3;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
