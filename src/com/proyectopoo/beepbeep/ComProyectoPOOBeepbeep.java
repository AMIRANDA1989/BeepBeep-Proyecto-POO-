/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.proyectopoo.beepbeep.classes.Rol;
import com.proyectopoo.beepbeep.data.ConnectionBeep;
import com.proyectopoo.beepbeep.data.RolData;

/**
 *
 * @author allan
 */
public class ComProyectoPOOBeepbeep {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RolData rd = new RolData();
        Rol r = rd.read(1);
        
        System.out.println(r.getCodRol() + " " + r.getNombreRol());        
        
        
        
    }
    
}
