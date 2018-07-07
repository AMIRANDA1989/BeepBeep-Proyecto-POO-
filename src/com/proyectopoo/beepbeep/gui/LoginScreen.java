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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author allan
 */
public class LoginScreen extends JFrame implements ActionListener {

    private JTextField username;
    private JPasswordField password;
    private JLabel btnLogin, btnRegister, bkgScreen;
    private Usuario player;
    private UsuarioData playerDAO;

    public LoginScreen() {
        super("Bienvenido a BEEP BEEP");
        initTopScreen();
    }

    public void initTopScreen() {
        username = new JTextField();
        password = new JPasswordField();
        btnLogin = new JLabel();
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

        btnLogin.setIcon(new ImageIcon(getClass().getResource("btn-ingresar.png")));
        btnLogin.setBounds(70, 309, 171, 59);
        btnLogin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                login();
            }
        });

        btnRegister.setIcon(new ImageIcon(getClass().getResource("btn-registrar.png")));
        btnRegister.setBounds(273, 309, 171, 59);
        btnRegister.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                activateRegister();
            }
        });

        container.add(username);
        container.add(password);
        container.add(btnLogin);
        container.add(btnRegister);
        container.add(bkgScreen);

        setSize(512, 384);
    }

    private void login() {
        String pass = new String(password.getPassword());
        System.out.println(pass);
        playerDAO = new UsuarioData();
        player = playerDAO.loginUser(username.getText(), pass);
        if (player == null) {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrecta", "Ha ocurrido un problema", JOptionPane.ERROR_MESSAGE);
        } else {
            this.setVisible(false);
            new MainMenuScreen(player).setVisible(true);
        }

    }

    private void activateRegister() {
        RegisterScreen rd = new RegisterScreen();
        this.setVisible(false);
        rd.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
