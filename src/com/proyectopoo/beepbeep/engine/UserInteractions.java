/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.engine;

import com.proyectopoo.beepbeep.classes.Usuario;
import com.proyectopoo.beepbeep.data.UsuarioData;

/**
 *
 * @author allan
 */
public class UserInteractions {
    public final int INFO_MESSAGE = 1;
    public final int WARNING_MESSAGE = 2;
    public final int ERROR_MESSAGE = 3;
    public final int ALERT_MESSAGE = 4;
    
    private Usuario user;
    private UsuarioData userDao;
    
    public void showMessage(int messageType, String message) {
        switch (messageType) {
            case (1)://INFO_MESSAGE
                System.out.println("");
                System.out.println("[INFO] - " + message);
                break;

            case (2): //WARNING_MESSAGE
                System.out.println("");
                System.out.println("*ADVERTENCIA* - " + message);
                break;

            case (3): //ERROR_MESSAGE
                System.out.println("***");
                System.out.println("<ERROR> - " + message);
                System.out.println("***");
                break;

            case (4)://ALERT_MESSAGE
                System.out.println("");
                System.out.println("!!ALERTA!! - " + message);
        }
    }
    
    public boolean modUser(Usuario u){
        return userDao.update(u);
    }
    
    public Usuario getUserByKey(int key){
        return userDao.read(key);
    }
}
