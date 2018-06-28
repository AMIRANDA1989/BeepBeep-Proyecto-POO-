/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author allan
 */
class MKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
            //Obteniendo el evento de la llave presionada
            int key = event.getKeyCode();
            System.out.println("" + key);

            //Validando llave presionada
            switch (key) {
                case KeyEvent.VK_LEFT:
                    System.out.println("LEFT");
                    break;

                case KeyEvent.VK_RIGHT:
                    System.out.println("RIGHT");
                    break;
            }
        }
    }
