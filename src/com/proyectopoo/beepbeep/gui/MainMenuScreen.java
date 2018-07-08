/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.gui;

import com.proyectopoo.beepbeep.classes.Carrera;
import com.proyectopoo.beepbeep.classes.Usuario;
import com.proyectopoo.beepbeep.data.CarreraData;
import com.proyectopoo.beepbeep.data.UsuarioData;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author allan
 */
public class MainMenuScreen extends JFrame implements ActionListener {

    //labels para botones del menu principal
    private JLabel retoBtn, tiendaBtn, tallerBtn, topBtn, windowBkg;

    //labels para textos varios
    private JLabel usernameLbl, moneyLbl, pointsLbl;

    private Usuario player;
    private UsuarioData playerDAO;
    private Carrera race;
    private CarreraData raceDAO;

    public MainMenuScreen(Usuario player) {
        super("Bienvenido a Beep Beep!");
        this.player = player;
        initMainMenuScreen();
    }

    private void initMainMenuScreen() {
        retoBtn = new JLabel();
        tiendaBtn = new JLabel();
        tallerBtn = new JLabel();
        topBtn = new JLabel();
        windowBkg = new JLabel();
        usernameLbl = new JLabel();
        moneyLbl = new JLabel();
        pointsLbl = new JLabel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Container container = getContentPane();

        //Configurando fondo
        this.windowBkg.setIcon(new ImageIcon(getClass().getResource("bkg-MainMenu.png")));
        this.windowBkg.setBounds(0, 0, 1024, 768);

        //Configurando boton de buscar reto
        retoBtn.setIcon(new ImageIcon(getClass().getResource("btn-Carrera.png")));
        retoBtn.setBounds(237, 210, 636, 184);
        retoBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                boolean found = searchReto();
                
                if(found){
                    joinRace(1); //se une a la carrera
                }else{
                    joinRace(2); //crea una carrera nueva
                }
                
            }
        });

        //Configurando boton de tienda
        tiendaBtn.setIcon(new ImageIcon(getClass().getResource("btn-Tienda.png")));
        tiendaBtn.setBounds(237, 396, 297, 170);
        tiendaBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                sendToMenu(1);
            }
        });

        //configurando boton de taller
        tallerBtn.setIcon(new ImageIcon(getClass().getResource("btn-Taller.png")));
        tallerBtn.setBounds(562, 396, 297, 170);
        tallerBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                sendToMenu(2);
            }
        });

        //configurando boton de top
        topBtn.setIcon(new ImageIcon(getClass().getResource("btn-Salon.png")));
        topBtn.setBounds(237, 575, 636, 146);
        topBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                sendToMenu(3);
            }
        });

        //configurando texto de username
        usernameLbl.setBounds(312, 25, 300, 50);
        usernameLbl.setText(player.getUsername());
        usernameLbl.setFont(new Font("system", Font.PLAIN, 30));
        usernameLbl.setForeground(Color.white);

        //configurando texto de puntos
        pointsLbl.setBounds(408, 100, 300, 50);
        pointsLbl.setText("" + player.getPuntos());
        pointsLbl.setFont(new Font("system", Font.PLAIN, 30));
        pointsLbl.setForeground(Color.white);

        //configurando texto de dinero
        moneyLbl.setBounds(408, 65, 300, 50);
        moneyLbl.setText("" + player.getDinero());
        moneyLbl.setFont(new Font("system", Font.PLAIN, 30));
        moneyLbl.setForeground(Color.white);

        /**
         * ********** AGREGANDO ELEMENTOS AL CONTENEDOR *************
         */
        container.add(usernameLbl);
        container.add(pointsLbl);
        container.add(moneyLbl);

        container.add(retoBtn);
        container.add(tiendaBtn);
        container.add(tallerBtn);
        container.add(topBtn);

        container.add(windowBkg);

        //estableciendo tamaño de la pantalla
        setSize(1024, 768);

    }

    private void sendToMenu(int menu) {
        switch (menu) {
            case 1: //tienda
                this.setVisible(false);
                new StoreScreen(player).setVisible(true);
                break;

            case 2: //taller
                this.setVisible(false);
                new ItemScreen(player).setVisible(true);
                break;

            case 3://top
                this.setVisible(false);
                new TopScreen(player).setVisible(true);
                break;

        }
    }

    private boolean searchReto() {
        raceDAO = new CarreraData();
        race = raceDAO.search(player.getCodUsuario());
        if (race == null) {
            return false;
        } else {
            playerDAO = new UsuarioData();
            Usuario u = playerDAO.read(race.getCodJugador1());
            
            JOptionPane.showMessageDialog(this, "Competiras para mejorar el tiempo de: " + u.getUsername() + " Que terminó su carrera en: " + race.getTiempoJugador1() + " segundos.", "Informacion de reto", JOptionPane.INFORMATION_MESSAGE);
            
            return true;
        }
    }
    
    private void joinRace(int option){
        Carrera reto = null;
        
        switch(option){
            case 1:
                raceDAO = new CarreraData();
                race.setCodJugador2(player.getCodUsuario());
                raceDAO.update(race);
                reto = race;
                break;
                
            case 2:
                raceDAO = new CarreraData();
                raceDAO.createNewRace(player.getCodUsuario());
                raceDAO = new CarreraData();
                reto = raceDAO.getNewRace(player.getCodUsuario());
                break;
        }
        
        this.setVisible(false);
        new GameScreen(player,reto).setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
