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
public class Carrera {
    private int codCarrera;
    private int codJugador1;
    private int tiempoJugador1;
    private int codJugador2;
    private int tiempoJugador2;

    public Carrera(int codCarrera, int codJugador1, int tiempoJugador1, int codJugador2, int tiempoJugador2) {
        this.codCarrera = codCarrera;
        this.codJugador1 = codJugador1;
        this.tiempoJugador1 = tiempoJugador1;
        this.codJugador2 = codJugador2;
        this.tiempoJugador2 = tiempoJugador2;
    }

    public int getCodCarrera() {
        return codCarrera;
    }

    public void setCodCarrera(int codCarrera) {
        this.codCarrera = codCarrera;
    }

    public int getCodJugador1() {
        return codJugador1;
    }

    public void setCodJugador1(int codJugador1) {
        this.codJugador1 = codJugador1;
    }

    public int getTiempoJugador1() {
        return tiempoJugador1;
    }

    public void setTiempoJugador1(int tiempoJugador1) {
        this.tiempoJugador1 = tiempoJugador1;
    }

    public int getCodJugador2() {
        return codJugador2;
    }

    public void setCodJugador2(int codJugador2) {
        this.codJugador2 = codJugador2;
    }

    public int getTiempoJugador2() {
        return tiempoJugador2;
    }

    public void setTiempoJugador2(int tiempoJugador2) {
        this.tiempoJugador2 = tiempoJugador2;
    }
    
    
}
