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
public class ItemScreen extends JFrame implements ActionListener {

    //labels para botones del menu principal
    private JLabel equipBtn, backBtn, windowBkg;

    //labels para textos varios
    private JLabel usernameLbl, moneyLbl, pointsLbl;

    /**
     * LABELS DE ICONOS DE ITEMS**
     */
    //Labels de objetos equipados e inventario
    private int ICON_POS_X; //posicion en eje X para iconos de tienda o inventario
    private int ICON_POS_Y; //posicion en eje Y para iconos de tienda o inventario
    private JLabel equippedEngineIcon, equippedAccesoryIcon, equippedTiresIcon;
    private JLabel equippedEngineLbl, equippedAccesoryLbl, equippedTiresLbl;
    //labels para inventario de usuario
    private ArrayList<JLabel> partIcons = new ArrayList();
    private ArrayList<Parte> parts = new ArrayList();

    private JLabel itemName, itemPrice, itemSpeed, itemAccel, itemMan;
    private JTextArea itemDesc;

    private Usuario player;
    private Parte selectedPart;
    private Parte eqEngine;
    private Parte eqTires;
    private Parte eqAccesory;

    private ParteData partDAO = new ParteData();
    private UsuarioData userDAO = new UsuarioData();

    public ItemScreen(Usuario player) {
        super("Bienvenido a tu taller!");
        this.player = player;
        initItemScreen();
    }

    private void initItemScreen() {
        equipBtn = new JLabel();
        backBtn = new JLabel();
        windowBkg = new JLabel();
        usernameLbl = new JLabel();
        moneyLbl = new JLabel();
        pointsLbl = new JLabel();
        itemName = new JLabel();
        itemPrice = new JLabel();
        itemSpeed = new JLabel();
        itemAccel = new JLabel();
        itemMan = new JLabel();
        itemDesc = new JTextArea();
        equippedEngineIcon = new JLabel();
        equippedAccesoryIcon = new JLabel();
        equippedTiresIcon = new JLabel();
        equippedEngineLbl = new JLabel();
        equippedAccesoryLbl = new JLabel();
        equippedTiresLbl = new JLabel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Container container = getContentPane();

        //Configurando fondo
        this.windowBkg.setIcon(new ImageIcon(getClass().getResource("bkg-Taller.png")));
        this.windowBkg.setBounds(0, 0, 1024, 768);

        //Configurando boton de volver al menu principal
        backBtn.setIcon(new ImageIcon(getClass().getResource("btn-VolverB.png")));
        backBtn.setBounds(676, 34, 275, 148);
        backBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                closeWindow();
            }
        });

        //Configurando boton de tienda
        equipBtn.setIcon(new ImageIcon(getClass().getResource("btn-equipar.png")));
        equipBtn.setBounds(691, 640, 260, 77);
        equipBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                equipItem();
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
        itemName.setBounds(290, 215, 260, 50);
        itemName.setText("");
        itemName.setFont(new Font("system", Font.PLAIN, 30));
        itemName.setForeground(Color.black);

        //configurando texto de descripcion de item
        itemDesc.setBounds(172, 262, 500, 130);
        itemDesc.setText("");
        itemDesc.setSize(400, 130);
        itemDesc.setFont(new Font("system", Font.PLAIN, 18));
        itemDesc.setForeground(Color.black);
        itemDesc.setEditable(false);
        itemDesc.setLineWrap(true);
        itemDesc.setWrapStyleWord(true);
        itemDesc.setBackground(Color.white);

        //configurando texto de modificador de velocidad
        itemSpeed.setBounds(172, 385, 96, 34);
        itemSpeed.setText("");
        itemSpeed.setFont(new Font("system", Font.PLAIN, 16));
        itemSpeed.setForeground(Color.black);

        //configurando texto de modificador de aceleracion
        itemAccel.setBounds(325, 385, 96, 34);
        itemAccel.setText("");
        itemAccel.setFont(new Font("system", Font.PLAIN, 16));
        itemAccel.setForeground(Color.black);

        //configurando texto de modificador de aceleracion
        itemMan.setBounds(465, 385, 96, 34);
        itemMan.setText("");
        itemMan.setFont(new Font("system", Font.PLAIN, 16));
        itemMan.setForeground(Color.black);

        //configurando inventario
        setupInventory();
        showInventory();

        //configurando icono de motor
        System.out.println("Configurando Motor equipado");
        partDAO = new ParteData();
        eqEngine = partDAO.read(player.getCodmotor());
        equippedEngineIcon.setIcon(new ImageIcon(getClass().getResource("ic-motor.png")));
        equippedEngineIcon.setBounds(692, 249, 40, 40);
        equippedEngineIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showItemDetails(eqEngine);
            }
        });

        //configurando icono de llantas
        System.out.println("Configurando llantas equipadas " + player.getCodUsuario());
        partDAO = new ParteData();
        eqTires = partDAO.read(player.getCodllantas());
        equippedTiresIcon.setIcon(new ImageIcon(getClass().getResource("ic-llanta.png")));
        equippedTiresIcon.setBounds(692, 317, 40, 40);
        equippedTiresIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showItemDetails(eqTires);
            }
        });

        //configurando icono de accesorio
        System.out.println("Configurando accesorio equipado");
        partDAO = new ParteData();
        eqAccesory = partDAO.read(player.getCodaccesorio());
        equippedAccesoryIcon.setIcon(new ImageIcon(getClass().getResource("ic-accesorio.png")));
        equippedAccesoryIcon.setBounds(692, 384, 40, 40);
        equippedAccesoryIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showItemDetails(eqAccesory);
            }
        });

        //configurando texto de nombre del motor equipado
        equippedEngineLbl.setBounds(746, 253, 195, 30);
        equippedEngineLbl.setText(eqEngine.getNombre());
        equippedEngineLbl.setFont(new Font("system", Font.PLAIN, 25));
        equippedEngineLbl.setForeground(Color.black);

        //configurando texto de nombre de las llantas equipadas
        equippedTiresLbl.setBounds(746, 319, 195, 30);
        equippedTiresLbl.setText(eqTires.getNombre());
        equippedTiresLbl.setFont(new Font("system", Font.PLAIN, 25));
        equippedTiresLbl.setForeground(Color.black);

        //configurando texto de nombre de el accesorio equipado
        equippedAccesoryLbl.setBounds(746, 387, 195, 30);
        equippedAccesoryLbl.setText(eqAccesory.getNombre());
        equippedAccesoryLbl.setFont(new Font("system", Font.PLAIN, 25));
        equippedAccesoryLbl.setForeground(Color.black);

        //configurando las partes del inventario
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
        container.add(equippedEngineIcon);
        container.add(equippedTiresIcon);
        container.add(equippedAccesoryIcon);
        container.add(equippedEngineLbl);
        container.add(equippedTiresLbl);
        container.add(equippedAccesoryLbl);

        for (JLabel j : this.partIcons) {
            container.add(j);
        }

        container.add(equipBtn);
        container.add(backBtn);

        container.add(windowBkg);

        //estableciendo tamaño de la pantalla
        setSize(1024, 768);

    }

    public void setupInventory() {
        ICON_POS_X = 152;
        ICON_POS_Y = 520;

        int lineItem = 1;

        //Obteniendo el listado de partes disponibles
        parts = partDAO.getInventory(player.getCodUsuario());

        //configurando la posicion de los iconos de los items
        for (Parte p : parts) {
            p.setPosX(ICON_POS_X);
            p.setPoxY(ICON_POS_Y);

            if (lineItem <= 10) {
                ICON_POS_X += 50;
                lineItem++;
            } else {
                ICON_POS_X = 50;
                ICON_POS_Y += 60;
                lineItem = 1;
            }

            System.out.println("X: " + ICON_POS_X + " Y: " + ICON_POS_Y);

        }

    }

    private void showInventory() {
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
        this.itemDesc.setText(p.getDescripcion());
        this.itemSpeed.setText("MaxVel: " + p.getVelocModifier());
        this.itemAccel.setText("Accel: " + p.getAccelModifier());
        this.itemMan.setText("Maniobra:" + p.getManModifier());
    }

    public void equipItem() {

        try {
            Object[] options = {"Si", "No", "Cancelar"};

            int n = JOptionPane.showOptionDialog(this, "Deseas equipar " + selectedPart.getNombre() + "?", "Equipar parte", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

            if (n == 0) {
                switch (selectedPart.getCategoria()) {
                    case 1:
                        player.setCodmotor(selectedPart.getCodParte());
                        break;
                    case 2:
                        player.setCodllantas(selectedPart.getCodParte());
                        break;
                    case 3:
                        player.setCodaccesorio(selectedPart.getCodParte());
                        break;
                }

                userDAO = new UsuarioData();
                if (userDAO.update(player)) {
                    moneyLbl.setText("" + player.getDinero());
                    pointsLbl.setText("" + player.getPuntos());
                    JOptionPane.showMessageDialog(this, "Parte equipada con exito, sal a la calle a probar tu nuevo equipo.", "Equipar parte", JOptionPane.INFORMATION_MESSAGE);

                    switch (selectedPart.getCategoria()) {
                        case 1:
                            equippedEngineLbl.setText(selectedPart.getNombre());
                            break;
                        case 2:
                            equippedTiresLbl.setText(selectedPart.getNombre());
                            break;
                        case 3:
                            equippedAccesoryLbl.setText(selectedPart.getNombre());
                            break;
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error al momento de equipar la parte, estamos trabajando en resolverlo", "Ha ocurrido un problema", JOptionPane.ERROR_MESSAGE);
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
            userDAO = new UsuarioData();
            new MainMenuScreen(userDAO.read(player.getCodUsuario())).setVisible(true);
        }

        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
