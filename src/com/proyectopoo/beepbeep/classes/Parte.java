/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.classes;

/**
 *
 * @author allan
 */
public class Parte {
    private int codParte;
    private String nombre;
    private String descripcion;
    private int precio;
    private int categoria;
    private int velocModifier;
    private int accelModifier;
    private int manModifier;
    private int posX;
    private int poxY;

    public Parte(int codParte, String nombre, String descripcion, int precio, int categoria, int velocModifier, int accelModifier, int manModifier) {
        this.codParte = codParte;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.velocModifier = velocModifier;
        this.accelModifier = accelModifier;
        this.manModifier = manModifier;
    }

    

    public int getCodParte() {
        return codParte;
    }

    public void setCodParte(int codParte) {
        this.codParte = codParte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getVelocModifier() {
        return velocModifier;
    }

    public void setVelocModifier(int velocModifier) {
        this.velocModifier = velocModifier;
    }

    public int getAccelModifier() {
        return accelModifier;
    }

    public void setAccelModifier(int accelModifier) {
        this.accelModifier = accelModifier;
    }

    public int getManModifier() {
        return manModifier;
    }

    public void setManModifier(int manModifier) {
        this.manModifier = manModifier;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPoxY() {
        return poxY;
    }

    public void setPoxY(int poxY) {
        this.poxY = poxY;
    }
    
    
}
