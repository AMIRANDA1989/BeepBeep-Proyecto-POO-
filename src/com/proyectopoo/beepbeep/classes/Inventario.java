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
public class Inventario {
    private int codParte;
    private int codUsuario;

    public Inventario(int codParte, int codUsuario) {
        this.codParte = codParte;
        this.codUsuario = codUsuario;
    }

    public int getCodParte() {
        return codParte;
    }

    public void setCodParte(int codParte) {
        this.codParte = codParte;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }
    
    
}
