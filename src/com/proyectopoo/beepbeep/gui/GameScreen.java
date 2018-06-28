/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author allan
 */
public class GameScreen extends JFrame implements ActionListener, KeyListener {

    private JLabel carSprite, streetSprite;
    private int CAR_POS_X = 462;
    private int CAR_POS_Y = 538;
    private int STREET_POS_X = 332;
    private int STREET_POS_Y = -2232;
    private final Set<Integer> pressed = new HashSet<Integer>();

    public GameScreen() {
        super("Beep Beep");
        this.addKeyListener(this);
        this.setFocusTraversalKeysEnabled(false); //sirve para desactivar llaves como tab y otras
        initGameScreen();

    }

    public void initGameScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Container container = getContentPane();

        //Inicializando labels(imagenes) de la pantalla
        carSprite = new JLabel(); //Inicializando auto
        streetSprite = new JLabel(); //Inicializando carretera

        //configurando carretera
        streetSprite.setIcon(new ImageIcon(getClass().getResource("street.jpg"))); //Agregando icono
        streetSprite.setBounds(STREET_POS_X, STREET_POS_Y, 360, 3000); //Ingresando ubicacion inicial
        streetSprite.addKeyListener(new MKeyListener());

        //configurando auto
        carSprite.setIcon(new ImageIcon(getClass().getResource("car.png"))); //Agregando icono
        carSprite.setBounds(CAR_POS_X, CAR_POS_Y, 60, 130); //Ingresando ubicacion inicial
        carSprite.addKeyListener(new MKeyListener());

        //agregando elementos al contenedor
        container.add(carSprite);
        container.add(streetSprite);

        //estableciendo tamaño de la pantalla
        setSize(1024, 768);
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());

        for (Integer i : pressed) {
            switch (i) {
                case KeyEvent.VK_LEFT:
                    System.out.println("Left");
                    CAR_POS_X -= 5;
                    carSprite.setBounds(CAR_POS_X, CAR_POS_Y, 60, 130);
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("Right");
                    CAR_POS_X += 5;
                    carSprite.setBounds(CAR_POS_X, CAR_POS_Y, 60, 130);
                    break;
                case KeyEvent.VK_UP:
                    STREET_POS_Y += 5;
                    streetSprite.setBounds(STREET_POS_X, STREET_POS_Y, 360, 3000);
                    break;
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
    }

}
