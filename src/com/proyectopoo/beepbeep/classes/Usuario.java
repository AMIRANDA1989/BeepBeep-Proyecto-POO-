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
public class Usuario {
    private int codUsuario;
    private String username;
    private String password;
    private int codrol;
    private int codmotor;
    private int codllantas;
    private int codaccesorio;
    private int maxvelocidad;
    private int aceleracion;
    private int maniobrabilidad;
    private int dinero;
    private int puntos;
    
    public Usuario(){
        
    }

    public Usuario(int codUsuario, String username, String password, int codrol, int codmotor, int codllantas, int codaccesorio, int maxvelocidad, int aceleracion, int maniobrabilidad, int dinero, int puntos) {
        this.codUsuario = codUsuario;
        this.username = username;
        this.password = password;
        this.codrol = codrol;
        this.codmotor = codmotor;
        this.codllantas = codllantas;
        this.codaccesorio = codaccesorio;
        this.maxvelocidad = maxvelocidad;
        this.aceleracion = aceleracion;
        this.maniobrabilidad = maniobrabilidad;
        this.dinero = dinero;
        this.puntos = puntos;
    }

    

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCodrol() {
        return codrol;
    }

    public void setCodrol(int codrol) {
        this.codrol = codrol;
    }

    public int getCodmotor() {
        return codmotor;
    }

    public void setCodmotor(int codmotor) {
        this.codmotor = codmotor;
    }

    public int getCodllantas() {
        return codllantas;
    }

    public void setCodllantas(int codllantas) {
        this.codllantas = codllantas;
    }

    public int getCodaccesorio() {
        return codaccesorio;
    }

    public void setCodaccesorio(int codaccesorio) {
        this.codaccesorio = codaccesorio;
    }

    public int getMaxvelocidad() {
        return maxvelocidad;
    }

    public void setMaxvelocidad(int maxvelocidad) {
        this.maxvelocidad = maxvelocidad;
    }

    public int getAceleracion() {
        return aceleracion;
    }

    public void setAceleracion(int aceleracion) {
        this.aceleracion = aceleracion;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getManiobrabilidad() {
        return maniobrabilidad;
    }

    public void setManiobrabilidad(int maniobrabilidad) {
        this.maniobrabilidad = maniobrabilidad;
    }
    
    
    
}
