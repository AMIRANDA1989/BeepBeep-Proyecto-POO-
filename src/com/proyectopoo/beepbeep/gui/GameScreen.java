/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.gui;

import com.proyectopoo.beepbeep.classes.Carrera;
import com.proyectopoo.beepbeep.classes.Parte;
import com.proyectopoo.beepbeep.classes.Usuario;
import com.proyectopoo.beepbeep.data.CarreraData;
import com.proyectopoo.beepbeep.data.ParteData;
import com.proyectopoo.beepbeep.data.UsuarioData;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    //Variables de interfaz
    private JLabel carSprite, streetSprite, timeCounter, finishLine, background, finishMessage, startMessage, cautionSign;
    private int elapsedTime = 0;
    private int STREET_POS_X = 332;
    private int STREET_POS_Y = -5232;
    private int FLINE_POS_X = 332;
    private int FLINE_POS_Y = -5052;
    private final Set<Integer> pressed = new HashSet<Integer>();
    private Usuario player;
    private Carrera race;

    private UsuarioData playerDAO;
    private CarreraData raceDAO;
    private Parte eqEngine;
    private Parte eqTires;
    private Parte eqAccesory;

    //Variables de jugabilidad
    private int START_COUNTDOWN = 5;
    private int START_MSG_TIME = 1;
    private boolean ACTIVATED_KEYBOARD = false;
    private boolean ACTIVATED_TIME = false;
    private boolean VISIBLE_FINISH_MSG = false;
    private boolean VISIBLE_START_MSG = false;
    private boolean VISIBLE_CAUTION_SIGN = false;
    private boolean FINISHED_RACE = false;
    private int hitcount = 0; //contador contacto de obstaculos

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
            //System.out.println(FINISHED_RACE);
            //validando si ya se toco la meta
            reachedFinish();

            if (!FINISHED_RACE) {

                if (START_COUNTDOWN == 0) {
                    //activando el teclado

                    ACTIVATED_KEYBOARD = true;
                    //Mostrando por un segundo el mensaje de inicio
                    if (START_MSG_TIME > 0) {
                        VISIBLE_START_MSG = true;
                        startMessage.setVisible(VISIBLE_START_MSG);
                        START_MSG_TIME--;
                    } else {
                        VISIBLE_START_MSG = false;
                        startMessage.setVisible(VISIBLE_START_MSG);
                    }

                    elapsedTime++;
                    timeCounter.setText("" + elapsedTime);

                    cautionSign.setVisible(VISIBLE_CAUTION_SIGN);

                    if (CAR_SPEED <= 50 + player.getMaxvelocidad()) {
                        if (ACCELERATING) {
                            CAR_SPEED += (5 + player.getAceleracion());
                        }
                    }
                } else {
                    START_COUNTDOWN--;
                }
            } else {

                ACTIVATED_KEYBOARD = false;

                finishRace();

            }
        }
    });

    public GameScreen(Usuario player, Carrera race) {
        super("Beep Beep");
        this.player = player;
        this.addKeyListener(this);
        this.setFocusTraversalKeysEnabled(false); //sirve para desactivar llaves como tab y otras
        this.race = race;
        setupPlayerGear();
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
        this.timeCounter.setFont(new Font("system", Font.PLAIN, 30));

        this.finishLine = new JLabel(); //label de meta
        this.background = new JLabel(); //label de fondo de pantalla
        this.finishMessage = new JLabel();
        this.startMessage = new JLabel();
        this.cautionSign = new JLabel();

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
                    //System.out.println("Colocando Obstaculo");
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

        //configurando fondo de pantalla
        this.background.setIcon(new ImageIcon(getClass().getResource("grassbackground.png")));
        this.background.setBounds(0, 0, 1024, 768);

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

        //configurando mensaje de inicio
        this.startMessage.setIcon(new ImageIcon(getClass().getResource("start.png")));
        this.startMessage.setBounds(44, 250, 937, 238);
        this.startMessage.setVisible(this.VISIBLE_START_MSG);

        //configurando senal de choque con obstaculo
        this.cautionSign.setIcon(new ImageIcon(getClass().getResource("cuidadoSign.png")));
        this.cautionSign.setBounds(0, 600, 113, 122);
        this.cautionSign.setVisible(this.VISIBLE_CAUTION_SIGN);

        //configurando mensaje de finalizacion
        this.finishMessage.setIcon(new ImageIcon(getClass().getResource("raceOver.png")));
        this.finishMessage.setBounds(151, 276, 705, 215);
        this.finishMessage.setVisible(false);

        timeCounter.setBounds(20, 20, 300, 50); //Ingresando ubicacion inicial
        timeCounter.setText("0");

        /**
         * ********** AGREGANDO ELEMENTOS AL CONTENEDOR *************
         */
        container.add(this.startMessage);
        container.add(timeCounter);
        container.add(cautionSign);
        container.add(finishMessage);
        container.add(carSprite);
        container.add(finishLine);
        //agregando obstaculos
        for (JLabel o : obstacles) {
            container.add(o);
        }
        //agregando la calle
        container.add(streetSprite);
        //agregando el fondo de pantalla
        container.add(this.background);

        timer.start();
        //estableciendo tamaño de la pantalla
        setSize(1024, 768);
    }

    private boolean obstacleHit(JLabel obstacle) {
        int carX = this.carSprite.getX();
        int carY = this.carSprite.getY();
        int carX2 = this.carSprite.getX() + 60;
        int carY2 = this.carSprite.getY() + 130;

        int obstacleX = obstacle.getX();
        int obstacleY = obstacle.getY();
        int obstacleX2 = obstacle.getX() + 120;
        int obstacleY2 = obstacle.getY() + 100;

        if (carX < obstacleX2 && carX2 > obstacleX && carY < obstacleY2 && carY2 > obstacleY) {
            return true;
        } else {
            return false;
        }

    }

    private void reachedFinish() {
        int carX = this.carSprite.getX();
        int carY = this.carSprite.getY();
        int carX2 = this.carSprite.getX() + 60;
        int carY2 = this.carSprite.getY() + 130;

        int finishX = this.finishLine.getX() - 200;
        int finishY = this.finishLine.getY();
        int finishX2 = this.finishLine.getX() + 360;
        int finishY2 = this.finishLine.getY() + 180;

        if (carX < finishX2 && carX2 > finishX && carY < finishY2) {
            //System.out.println("choque");
            this.FINISHED_RACE = true;
            finishMessage.setVisible(true);
        } else {
            this.FINISHED_RACE = false;
        }
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());

        if (ACTIVATED_KEYBOARD) {
            for (Integer i : pressed) {
                switch (i) {
                    case KeyEvent.VK_LEFT:
                        //System.out.println("Left");
                        if (!(CAR_POS_X <= STREET_POS_X)) {
                            CAR_POS_X -= (5 + player.getManiobrabilidad());
                        }

                        carSprite.setBounds(CAR_POS_X, CAR_POS_Y, 60, 130);
                        break;
                    case KeyEvent.VK_RIGHT:
                        //System.out.println("Right");
                        if (!(CAR_POS_X >= STREET_POS_X + 300)) {
                            CAR_POS_X += (5 + player.getManiobrabilidad());
                        }

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
                        for (JLabel o : this.obstacles) {
                            o.setBounds(o.getX(), o.getY() + CAR_SPEED, 120, 100);

                            if (this.obstacleHit(o)) {
                                CAR_SPEED = 5;
                                hitcount++;

                            }
                        }

                        //revisando si hubo algun impacto a un obstaculo
                        if (hitcount > 0) {
                            this.VISIBLE_CAUTION_SIGN = true;
                        } else {
                            this.VISIBLE_CAUTION_SIGN = false;
                        }

                        //reiniciando el contador
                        hitcount = 0;

                        //this.obstacles.get(0).setBounds(332, OBS_BLOCK_INIT_Y, 120, 100);
                        //System.out.println(STREET_POS_Y);
                        break;
                }
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

    public void closeWindow() {
        this.setVisible(false);
        playerDAO = new UsuarioData();
        new MainMenuScreen(playerDAO.read(player.getCodUsuario())).setVisible(true);

    }

    public void setupPlayerGear() {
        ParteData partDAO = new ParteData();
        eqEngine = partDAO.read(player.getCodmotor());
        partDAO = new ParteData();
        eqTires = partDAO.read(player.getCodllantas());
        partDAO = new ParteData();
        eqAccesory = partDAO.read(player.getCodaccesorio());

        player.setAceleracion(eqEngine.getAccelModifier() + eqTires.getAccelModifier() + eqAccesory.getAccelModifier());
        player.setMaxvelocidad(eqEngine.getVelocModifier() + eqTires.getVelocModifier() + eqAccesory.getVelocModifier());
        player.setManiobrabilidad(eqEngine.getManModifier() + eqEngine.getManModifier() + eqAccesory.getManModifier());
        System.out.println("Aceleracion: " + player.getAceleracion());
        System.out.println("Velocidad Maxima: " + player.getMaxvelocidad());
        System.out.println("maniobrabilidad: " + player.getManiobrabilidad());
    }

    public void finishRace() {
        finishMessage.setVisible(true);
        this.startMessage.setIcon(new ImageIcon(getClass().getResource("start.png")));
        startMessage.setVisible(true);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        raceDAO = new CarreraData();
        if (race.getCodJugador1() == player.getCodUsuario()) {
            //Si el jugador es el jugador 1 entonces
            race.setTiempoJugador1(elapsedTime);
        } else {
            race.setTiempoJugador2(elapsedTime);
        }

        raceDAO.update(race);
        checkWinner();
        closeWindow();

        this.timer.stop();
    }

    public void checkWinner() {
        int winnerNum = 0;
        Usuario winner;

        if (race.getTiempoJugador2() != 0) {
            if (race.getTiempoJugador1() < race.getTiempoJugador2()) {
                winnerNum = 1;
                System.out.println("Gana 1");
            }

            if (race.getTiempoJugador2() < race.getTiempoJugador1()) {
                winnerNum = 2;
                System.out.println("Gana 2");
            }

            if (winnerNum == 1) {
                playerDAO = new UsuarioData();
                winner = playerDAO.read(race.getCodJugador1());
                winner.setPuntos(winner.getPuntos() + 100);
                winner.setDinero(winner.getDinero() + 100);
                playerDAO = new UsuarioData();
                playerDAO.update(winner);
            }

            if (winnerNum == 2) {
                playerDAO = new UsuarioData();
                winner = playerDAO.read(race.getCodJugador2());
                winner.setPuntos(winner.getPuntos() + 100);
                winner.setDinero(winner.getDinero() + 100);
                playerDAO = new UsuarioData();
                playerDAO.update(winner);
            }
        }

    }

}
