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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author allan
 */
public class RegisterScreen extends JFrame implements ActionListener {

    private JTextField username;
    private JPasswordField password;
    private JLabel btnRegister, bkgScreen;
    private Usuario player;
    private UsuarioData playerDAO;

    public RegisterScreen() {
        super("Registrar nuevo usuario");
        initTopScreen();
    }

    public void initTopScreen() {
        username = new JTextField();
        password = new JPasswordField();
        btnRegister = new JLabel();
        bkgScreen = new JLabel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        Container container = getContentPane();

        username.setBounds(128, 94, 237, 38);
        username.setFont(new Font("system", Font.PLAIN, 30));
        username.setForeground(Color.black);

        password.setBounds(128, 173, 237, 38);
        password.setFont(new Font("system", Font.PLAIN, 30));
        password.setForeground(Color.black);

        bkgScreen.setIcon(new ImageIcon(getClass().getResource("bkg-login.png")));
        bkgScreen.setBounds(0, 0, 512, 384);

        btnRegister.setIcon(new ImageIcon(getClass().getResource("btn-registrar.png")));
        btnRegister.setBounds(273, 309, 171, 59);
        btnRegister.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                register();
            }
        });

        container.add(username);
        container.add(password);
        container.add(btnRegister);
        container.add(bkgScreen);

        setSize(512, 384);
    }

    private void register() {
        playerDAO = new UsuarioData();
        player = playerDAO.readByUsername(username.getText());
        if (player == null) {
            player = new Usuario();
            player.setUsername(username.getText());
            player.setPassword(new String(password.getPassword()));
            playerDAO = new UsuarioData();
            playerDAO.insert(player);
            JOptionPane.showMessageDialog(this, "Usuario ingresado con exito", "Registrando usuario", JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(false);
            new LoginScreen().setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "El usuario que se desea ingresar ya existe, pruebe con uno nuevo", "Ha ocurrido un problema", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
