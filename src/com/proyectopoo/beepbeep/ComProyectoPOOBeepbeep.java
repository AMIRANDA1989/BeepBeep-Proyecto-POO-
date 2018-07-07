/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.proyectopoo.beepbeep.classes.Parte;
import com.proyectopoo.beepbeep.classes.Rol;
import com.proyectopoo.beepbeep.classes.Usuario;
import com.proyectopoo.beepbeep.data.ConnectionBeep;
import com.proyectopoo.beepbeep.data.ParteData;
import com.proyectopoo.beepbeep.data.RolData;
import com.proyectopoo.beepbeep.data.UsuarioData;
import com.proyectopoo.beepbeep.engine.UserInteractions;
import com.proyectopoo.beepbeep.gui.GameScreen;
import com.proyectopoo.beepbeep.gui.ItemScreen;
import com.proyectopoo.beepbeep.gui.LoginScreen;
import com.proyectopoo.beepbeep.gui.MainMenuScreen;
import com.proyectopoo.beepbeep.gui.StoreScreen;
import com.proyectopoo.beepbeep.gui.TopScreen;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author allan
 */
public class ComProyectoPOOBeepbeep {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserInteractions userInteractions = new UserInteractions();
        UsuarioData ud = new UsuarioData();
        Usuario user = ud.read(1);
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginScreen().setVisible(true);
                //new TopScreen(user).setVisible(true);
            }
            
        });

        /*JTextField textField = new JTextField();

        textField.addKeyListener(new MKeyListener());

        JFrame jframe = new JFrame();

        jframe.add(textField);

        jframe.setSize(400, 350);

        jframe.setVisible(true);**/

    }
}

/*class MKeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {

        /*int ch = event.getKeyCode();
        
        System.out.println(ch);

        if (ch == 'a' || ch == 'b' || ch == 'c') {

            System.out.println(event.getKeyChar());

        }

        if (event.getKeyCode() == KeyEvent.VK_HOME) {

            System.out.println("Key codes: " + event.getKeyCode());

        }
    }

}*/


