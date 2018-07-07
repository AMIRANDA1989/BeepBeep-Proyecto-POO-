/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.gui;

import com.proyectopoo.beepbeep.classes.Usuario;
import com.proyectopoo.beepbeep.data.UsuarioData;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author allan
 */
public class TopScreen extends JFrame implements ActionListener {

    //labels para botones del menu principal
    private JLabel backBtn, windowBkg;

    //labels para textos varios
    private JLabel usernameLbl, moneyLbl, pointsLbl;

    private Usuario player;
    private UsuarioData userDAO;

    private ArrayList<JLabel> top10Usernames = new ArrayList();
    private ArrayList<JLabel> top10Points = new ArrayList();
    private ArrayList<Usuario> top10Players = new ArrayList();

    public TopScreen(Usuario player) {
        super("Bienvenido al salon de la fama!");
        this.player = player;
        initItemScreen();
    }

    private void initItemScreen() {
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
        backBtn.setBounds(372, 695, 280, 64);
        backBtn.setIcon(new ImageIcon(getClass().getResource("btn-VolverB.png")));
        backBtn.setBounds(676, 34, 275, 148);
        backBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                closeWindow();
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
        
        //cargando el top 10 de jugadores
        cargarTop();

        /**
         * ********** AGREGANDO ELEMENTOS AL CONTENEDOR *************
         */
        container.add(usernameLbl);
        container.add(pointsLbl);
        container.add(moneyLbl);
        
        for(JLabel a : top10Usernames){
            container.add(a);
        }
        for(JLabel a : top10Points){
            container.add(a);
        }

        container.add(backBtn);

        container.add(windowBkg);

        //estableciendo tamaño de la pantalla
        setSize(1024, 768);

    }

    private void cargarTop() {
        JLabel user, points;
        int usersX = 262;
        int pointsX = 600;
        int usersY = 296;
        int pointsY = 296;

        userDAO = new UsuarioData();
        top10Players = userDAO.readTop10();

        for (Usuario p : top10Players) {
            user = new JLabel();
            points = new JLabel();

            user.setBounds(usersX, usersY, 200, 34);
            user.setText(p.getUsername());
            user.setFont(new Font("system", Font.PLAIN, 30));
            user.setForeground(Color.white);
            
            this.top10Usernames.add(user);
            
            points.setBounds(pointsX, pointsY, 200, 34);
            points.setText(""+p.getPuntos());
            points.setFont(new Font("system", Font.PLAIN, 30));
            points.setForeground(Color.white);
            
            this.top10Points.add(points);
            
            usersY += 44;
            pointsY += 44;
        }

    }
    
    public void closeWindow() {
        Object[] options = {"Si", "No", "Cancelar"};

        int n;
        n = JOptionPane.showOptionDialog(this, "Deseas regresar al menu principal?", "Confirmar accion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

        if (n == 0) {
            this.setVisible(false);
            userDAO = new UsuarioData();
            new MainMenuScreen(userDAO.read(player.getCodUsuario())).setVisible(true);
        }

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
