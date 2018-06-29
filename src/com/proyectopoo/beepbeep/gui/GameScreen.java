/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.gui;

import com.proyectopoo.beepbeep.classes.Usuario;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author allan
 */
public class GameScreen extends JFrame implements ActionListener, KeyListener {

    private JLabel carSprite, streetSprite, timeCounter, finishLine;
    private int elapsedTime = 0;
    private int STREET_POS_X = 332;
    private int STREET_POS_Y = -5232;
    private int FLINE_POS_X = 332;
    private int FLINE_POS_Y = -5052;
    private final Set<Integer> pressed = new HashSet<Integer>();
    private Usuario player;
    
    //Variables para los obstaculos
    private ArrayList<JLabel> obstacles = new ArrayList();
    private int OBS_QTY = 50;
    private int OBS_BLOCK_INIT_Y = -4852;
    private int hasObstacle; //determina si el bloque de la carretera tendrá obstaculo
    private int obstacleLine; //determina que carril va a tener obstaculo;
    private int obsIndex = 0;

    //variables que sirven para manejar el vehiculo
    private int CAR_SPEED = 2; //velocidad inicial en pixeles del vehiculo
    private int CAR_POS_X = 462;
    private int CAR_POS_Y = 538;
    private int INIT_SPEED = 2;
    private boolean ACCELERATING = false;

    //Objeto timer que sirve para eventos de tiempo
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Date currentTime = new Date();
            elapsedTime++;
            timeCounter.setText(""+elapsedTime);

            if (CAR_SPEED <= player.getMaxvelocidad()) {
                if (ACCELERATING) {
                    CAR_SPEED += player.getAceleracion();
                }
            }
        }
    });

    public GameScreen(Usuario player) {
        super("Beep Beep");
        this.player = player;
        this.addKeyListener(this);
        this.setFocusTraversalKeysEnabled(false); //sirve para desactivar llaves como tab y otras
        initGameScreen();

    }

    public void initGameScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Container container = getContentPane();

        //Inicializando labels(imagenes) de la pantalla
        this.carSprite = new JLabel(); //Inicializando auto
        this.streetSprite = new JLabel(); //Inicializando carretera
        this.timeCounter = new JLabel(); //label de cronometro
        this.finishLine = new JLabel(); //label de meta

        //inicializacion de obstaculos
        for (int i = 0; i < 50; i++) {
            //Primero se revisa que no hayan llegado a su limite la cantidad de obstaculos
            if (this.OBS_QTY > 0) {
                
                //Para que exista un obstaculo en un bloque de la carretera, hay una probabilidad de 1 en 4
                hasObstacle = ThreadLocalRandom.current().nextInt(1, 4 + 1);
                if (hasObstacle == 1) {
                    this.obstacles.add(new JLabel());
                    this.obstacles.get(obsIndex).setIcon(new ImageIcon(getClass().getResource("rubble.png")));
                    obstacleLine = ThreadLocalRandom.current().nextInt(1, 3 + 1);
                    System.out.println("Colocando Obstaculo");
                    switch (obstacleLine) {
                        case 1:
                            this.obstacles.get(obsIndex).setBounds(322, OBS_BLOCK_INIT_Y, 120, 100);
                            break;

                        case 2:
                            this.obstacles.get(obsIndex).setBounds(444, OBS_BLOCK_INIT_Y, 120, 100);
                            break;

                        case 3:
                            this.obstacles.get(obsIndex).setBounds(576, OBS_BLOCK_INIT_Y, 120, 100);
                            break;
                    }
                    
                    obsIndex++;
                    
                }
            }
            OBS_BLOCK_INIT_Y += 100; //pasando al siguiente bloque
            this.OBS_QTY--;
        }

        this.obstacles.add(new JLabel());
        this.obstacles.get(0).setIcon(new ImageIcon(getClass().getResource("rubble.png")));
        this.obstacles.get(0).setBounds(332, OBS_BLOCK_INIT_Y, 120, 100);

        //configurando carretera
        streetSprite.setIcon(new ImageIcon(getClass().getResource("street.jpg"))); //Agregando icono
        streetSprite.setBounds(STREET_POS_X, STREET_POS_Y, 360, 6000); //Ingresando ubicacion inicial
        streetSprite.addKeyListener(new MKeyListener());

        //configurando auto
        carSprite.setIcon(new ImageIcon(getClass().getResource("car.png"))); //Agregando icono
        carSprite.setBounds(CAR_POS_X, CAR_POS_Y, 60, 130); //Ingresando ubicacion inicial
        carSprite.addKeyListener(new MKeyListener());

        //configurando meta
        finishLine.setIcon(new ImageIcon(getClass().getResource("finishline.png"))); //Agregando icono
        finishLine.setBounds(FLINE_POS_X, FLINE_POS_Y, 360, 180); //Ingresando ubicacion inicial

        timeCounter.setBounds(0, 0, 300, 50); //Ingresando ubicacion inicial
        timeCounter.setText("0");

        //agregando elementos al contenedor
        container.add(timeCounter);
        container.add(carSprite);
        container.add(finishLine);
        
        for(JLabel o : obstacles){
            container.add(o);
        }
        
        container.add(streetSprite);

        timer.start();
        //estableciendo tamaño de la pantalla
        setSize(1024, 768);
    }
    
    private boolean obstacleHit(JLabel obstacle){
        int carX = this.carSprite.getX();
        int carY = this.carSprite.getY();
        int carX2 = this.carSprite.getX()+60;
        int carY2 = this.carSprite.getY()+130;
        
        int obstacleX = obstacle.getX();
        int obstacleY = obstacle.getY();
        int obstacleX2 = obstacle.getX() + 120;
        int obstacleY2 = obstacle.getY() + 100;
        
        if(carX < obstacleX2 && carX2 > obstacleX && carY < obstacleY2 && carY2 > obstacleY){
            System.out.println("choque");
            return true;
        }
        
        return false;
    }
    
    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());

        for (Integer i : pressed) {
            switch (i) {
                case KeyEvent.VK_LEFT:
                    //System.out.println("Left");
                    CAR_POS_X -= 5;
                    carSprite.setBounds(CAR_POS_X, CAR_POS_Y, 60, 130);
                    break;
                case KeyEvent.VK_RIGHT:
                    //System.out.println("Right");
                    CAR_POS_X += 5;
                    carSprite.setBounds(CAR_POS_X, CAR_POS_Y, 60, 130);
                    break;
                case KeyEvent.VK_UP:
                    ACCELERATING = true;
                    //Moviendo carretera
                    STREET_POS_Y += CAR_SPEED;
                    
                    //moviendo meta
                    FLINE_POS_Y += CAR_SPEED;
                    
                    
                    
                    streetSprite.setBounds(STREET_POS_X, STREET_POS_Y, 360, 6000);
                    finishLine.setBounds(FLINE_POS_X, FLINE_POS_Y, 360, 180);
                    
                    //moviendo obstaculos
                    for(JLabel o : this.obstacles){
                        o.setBounds(o.getX(),o.getY() + CAR_SPEED, 120, 100);
                        
                        if(this.obstacleHit(o)){
                            CAR_SPEED = 5;
                        }
                    }
                    
                    //this.obstacles.get(0).setBounds(332, OBS_BLOCK_INIT_Y, 120, 100);
                    //System.out.println(STREET_POS_Y);
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

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                ACCELERATING = false;
                this.CAR_SPEED = this.INIT_SPEED;
                break;
        }

    }

}
