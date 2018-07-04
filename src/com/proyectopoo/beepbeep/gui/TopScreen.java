/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.gui;

import com.proyectopoo.beepbeep.classes.Usuario;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author allan
 */
public class TopScreen extends JFrame implements ActionListener{

    //labels para botones del menu principal
    private JLabel backBtn, windowBkg;
    
    //labels para textos varios
    private JLabel usernameLbl, moneyLbl, pointsLbl;
    
    private Usuario player;
    
    private int selected = 0; //Item seleccionado de la tienda
            
    public TopScreen(Usuario player){
        super("Bienvenido al salon de la fama!");
        this.player = player;
        initItemScreen();
    }
    
    private void initItemScreen(){
        backBtn = new JLabel();
        windowBkg = new JLabel();
        usernameLbl = new JLabel();
        moneyLbl = new JLabel();
        pointsLbl = new JLabel();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        Container container = getContentPane();
        
        //Configurando fondo
        this.windowBkg.setIcon(new ImageIcon(getClass().getResource("bkg-Top.png")));
        this.windowBkg.setBounds(0, 0, 1024, 768);
        
        //Configurando boton de volver al menu principal
        backBtn.setIcon(new ImageIcon(getClass().getResource("btn-VolverC.png")));
        backBtn.setBounds(372,695, 280, 64); 
    
  
        
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
        
        container.add(backBtn);
        
        container.add(windowBkg);
        
        
        //estableciendo tamaño de la pantalla
        setSize(1024, 768);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
