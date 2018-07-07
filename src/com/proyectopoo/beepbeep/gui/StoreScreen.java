/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.gui;

import com.proyectopoo.beepbeep.classes.Parte;
import com.proyectopoo.beepbeep.classes.Usuario;
import com.proyectopoo.beepbeep.data.ParteData;
import com.proyectopoo.beepbeep.data.UsuarioData;
import com.proyectopoo.beepbeep.engine.ItemInteractions;
import com.proyectopoo.beepbeep.engine.UserInteractions;
import com.sun.glass.events.WindowEvent;
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
import javax.swing.JTextArea;

/**
 *
 * @author allan
 */
public class StoreScreen extends JFrame implements ActionListener {

    //labels para botones del menu principal
    private JLabel buyBtn, backBtn, windowBkg;
    private JLabel itemName, itemPrice, itemSpeed, itemAccel, itemMan;
    private JTextArea itemDesc;
    //labels para botones de Partes a la venta
    private ArrayList<JLabel> partIcons = new ArrayList();

    private ArrayList<Parte> parts = new ArrayList();

    //labels para textos varios
    private JLabel usernameLbl, moneyLbl, pointsLbl;

    private Usuario player;
    private UserInteractions userInteractions;

    private final int CATEGORIA_MOTOR = 1;
    private int CATEGORIA_LLANTA = 2;
    private final int CATEGORIA_ACCESORIO = 3;

    private ItemInteractions itemInteractions = new ItemInteractions();

    private UsuarioData userDAO = new UsuarioData();
    private ParteData itemDAO = new ParteData();

    private int selected = 0; //Item seleccionado de la tienda
    private Parte selectedPart;

    public StoreScreen(Usuario player) {
        super("Bienvenido a la tienda!");
        this.player = player;
        initStoreScreen();
    }

    private void initStoreScreen() {
        buyBtn = new JLabel();
        backBtn = new JLabel();
        windowBkg = new JLabel();
        usernameLbl = new JLabel();
        moneyLbl = new JLabel();
        pointsLbl = new JLabel();
        itemName = new JLabel();
        itemDesc = new JTextArea();
        itemPrice = new JLabel();
        itemSpeed = new JLabel();
        itemAccel = new JLabel();
        itemMan = new JLabel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Container container = getContentPane();

        //Configurando fondo
        this.windowBkg.setIcon(new ImageIcon(getClass().getResource("bkg-Store.png")));
        this.windowBkg.setBounds(0, 0, 1024, 768);

        //Configurando boton de volver al menu principal
        backBtn.setIcon(new ImageIcon(getClass().getResource("btn-VolverA.png")));
        backBtn.setBounds(676, 34, 297, 170);
        backBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                closeWindow();
            }
        });

        //Configurando boton de tienda
        buyBtn.setIcon(new ImageIcon(getClass().getResource("btn-Comprar.png")));
        buyBtn.setBounds(646, 524, 291, 88);
        buyBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                buyItem();
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

        //configurando texto de nombre del item
        itemName.setBounds(656, 275, 260, 50);
        itemName.setText("");
        itemName.setFont(new Font("system", Font.PLAIN, 30));
        itemName.setForeground(Color.white);

        //configurando texto de descripcion de item
        itemDesc.setBounds(604, 325, 345, 130);
        itemDesc.setText("");
        itemDesc.setSize(345, 130);
        itemDesc.setFont(new Font("system", Font.PLAIN, 18));
        itemDesc.setForeground(Color.white);
        itemDesc.setEditable(false);
        itemDesc.setLineWrap(true);
        itemDesc.setWrapStyleWord(true);
        itemDesc.setBackground(Color.black);

        //configurando texto de modificador de velocidad
        itemSpeed.setBounds(604, 467, 96, 34);
        itemSpeed.setText("");
        itemSpeed.setFont(new Font("system", Font.PLAIN, 16));
        itemSpeed.setForeground(Color.white);

        //configurando texto de modificador de aceleracion
        itemAccel.setBounds(729, 467, 96, 34);
        itemAccel.setText("");
        itemAccel.setFont(new Font("system", Font.PLAIN, 16));
        itemAccel.setForeground(Color.white);

        //configurando texto de modificador de aceleracion
        itemMan.setBounds(853, 467, 96, 34);
        itemMan.setText("");
        itemMan.setFont(new Font("system", Font.PLAIN, 16));
        itemMan.setForeground(Color.white);

        //configurando iconos de tienda
        itemInteractions.setupStore();
        parts = itemInteractions.getParts();
        this.setupStore();

        /**
         * ********** AGREGANDO ELEMENTOS AL CONTENEDOR *************
         */
        container.add(usernameLbl);
        container.add(pointsLbl);
        container.add(moneyLbl);
        container.add(itemName);
        container.add(itemDesc);
        container.add(itemSpeed);
        container.add(itemAccel);
        container.add(itemMan);

        for (JLabel j : this.partIcons) {
            container.add(j);
        }

        container.add(buyBtn);
        container.add(backBtn);

        container.add(windowBkg);

        //estableciendo tamaño de la pantalla
        setSize(1024, 768);

    }

    private void setupStore() {
        JLabel part;
        int category;

        for (Parte p : parts) {
            part = new JLabel();
            category = p.getCategoria();

            switch (category) {
                case 1:
                    part.setIcon(new ImageIcon(getClass().getResource("ic-motor.png")));
                    break;
                case 2:
                    part.setIcon(new ImageIcon(getClass().getResource("ic-llanta.png")));
                    break;
                case 3:
                    part.setIcon(new ImageIcon(getClass().getResource("ic-accesorio.png")));
                    break;
            }

            part.setBounds(p.getPosX(), p.getPoxY(), 40, 40);
            part.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    showItemDetails(p);
                }
            });

            this.partIcons.add(part);
        }
    }

    public void showItemDetails(Parte p) {
        selectedPart = p;
        this.itemName.setText("<html>" + p.getNombre() + "</html>");
        this.itemDesc.setText(p.getDescripcion() + " / PRECIO:" + p.getPrecio());
        this.itemSpeed.setText("MaxVel: " + p.getVelocModifier());
        this.itemAccel.setText("Accel: " + p.getAccelModifier());
        this.itemMan.setText("Maniobra:" + p.getManModifier());
    }

    public void buyItem() {
        boolean userChange;
        boolean itemChange;
        
        try {
            Object[] options = {"Si", "No", "Cancelar"};

            int n = JOptionPane.showOptionDialog(this, "Deseas comprar un " + selectedPart.getNombre() + " por un valor de " + selectedPart.getPrecio(), "Confirmar compra", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

            if (n == 0) {
                if (this.player.getDinero() - selectedPart.getPrecio() >= 0) {
                    this.player.setDinero(this.player.getDinero() - selectedPart.getPrecio());
                    
                    userDAO = new UsuarioData();
                    userChange = userDAO.update(player);
                    
                    itemDAO = new ParteData();
                    itemChange = itemDAO.buyPart(player.getCodUsuario(), selectedPart.getCodParte());
                    
                    if (userChange && itemChange) {
                        moneyLbl.setText("" + player.getDinero());
                        pointsLbl.setText("" + player.getPuntos());
                        JOptionPane.showMessageDialog(this, "Compra realizada con exito, ve a probar tu nuevo equipo.", "Compra exitosa", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Ha ocurrido un error al momento de realizar la compra, estamos trabajando en resolverlo", "Ha ocurrido un problema", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No tienes suficiente dinero para comprar esta parte.", "No hay dinero!", JOptionPane.ERROR_MESSAGE);
                }

            }

        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna parte para comprar.", "No hay parte seleccionada", JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void closeWindow() {
        Object[] options = {"Si", "No", "Cancelar"};

        int n;
        n = JOptionPane.showOptionDialog(this, "Deseas regresar al menu principal?", "Confirmar accion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

        if (n == 0) {
            this.setVisible(false);
        }

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
