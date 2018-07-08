/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.data;

import com.mysql.jdbc.PreparedStatement;
import com.proyectopoo.beepbeep.classes.Carrera;
import com.proyectopoo.beepbeep.classes.Parte;
import com.proyectopoo.beepbeep.classes.Rol;
import com.proyectopoo.beepbeep.classes.Usuario;
import com.proyectopoo.beepbeep.engine.UserInteractions;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allan
 */
public class UsuarioData implements DataAccess<Usuario> {

    private static final String SQL_INSERT = "INSERT INTO USUARIO(username, password) VALUES (?,?)";
    private static final String SQL_UPDATE = "UPDATE USUARIO SET username = ?, password = ?, codrol = ?, codmotor = ?, codllantas = ?, codaccesorio = ?, maxvelocidad = ?, maniobrabilidad = ?, aceleracion = ?, dinero = ?, puntos = ? WHERE codusuario = ?";
    private static final String SQL_DELETE = "DELETE FROM USUARIO WHERE codusuario = ?";
    private static final String SQL_READ = "SELECT * FROM Usuario WHERE codusuario = ?";
    private static final String SQL_READALL = "SELECT * FROM usuario";
    private static final String SQL_TOP10 = "select * from usuario a order by puntos desc LIMIT 10 ";
    private static final String SQL_LOGIN = "SELECT * FROM usuario where username = ? and password = ?";
    private static final String SQL_READ_USERNAME = "SELECT * FROM Usuario WHERE username = ?";
    
    PreparedStatement ps;
    ConnectionBeep conn = ConnectionBeep.initConnection();
    UserInteractions ui;
    
    @Override
    public boolean insert(Usuario g) {
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getUsername());
            ps.setString(2, g.getPassword());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el insert " + SQL_INSERT);
            return false;
        } finally {
            conn.closeConnection();
        }

        return false;
    }

    @Override
    public boolean delete(Object key) {
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_DELETE);
            return false;
        } finally {
            conn.closeConnection();
        }
        
        return false;
    }

    @Override
    public boolean update(Usuario c) {
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getUsername());
            ps.setString(2, c.getPassword());
            ps.setInt(3, c.getCodrol());
            ps.setInt(4, c.getCodmotor());
            ps.setInt(5, c.getCodllantas());
            ps.setInt(6, c.getCodaccesorio());
            ps.setInt(7, c.getMaxvelocidad());
            ps.setInt(8, c.getManiobrabilidad());
            ps.setInt(9, c.getAceleracion());
            ps.setInt(10, c.getDinero());
            ps.setInt(11, c.getPuntos());
            ps.setInt(12, c.getCodUsuario());
            if (ps.executeUpdate() > 0) {
                return true;
            }
            
        } catch (Exception ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_UPDATE);
            return false;
        } finally {
            conn.closeConnection();
        }
        
        return false;
    }

    @Override
    public Usuario read(Object key) {
        Usuario res = null;
        ResultSet rs;
        try{
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            
            while (rs.next()){
                res = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12));
            }
            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_READ);
        }finally{
            conn.closeConnection();
        }
        
        return res;
    }

    @Override
    public ArrayList<Usuario> readAll() {
        ArrayList<Usuario> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s = conn.getConnection().prepareStatement(SQL_READALL);
            
            rs = s.executeQuery(SQL_READALL);
            
            while (rs.next()){
                all.add(new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12)));
            }
            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_READ);
        }finally{
            conn.closeConnection();
        }
        
        return all;
    }
    
    
    public ArrayList<Usuario> readTop10() {
        ArrayList<Usuario> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s = conn.getConnection().prepareStatement(SQL_TOP10);
            
            rs = s.executeQuery(SQL_TOP10);
            
            while (rs.next()){
                all.add(new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12)));
            }
            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_TOP10);
        } finally{
            conn.closeConnection();
        }
        
        return all;
    }

    public Usuario loginUser(String username, String password){
        Usuario res = null;
        ResultSet rs;
        try{
                        System.out.println("llegue");

            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_LOGIN);
            ps.setString(1, username);
            ps.setString(2, password);
            System.out.println("llegue");
            rs = ps.executeQuery();
            
            while (rs.next()){
                res = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12));
            }
            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_READ);
        }finally{
            conn.closeConnection();
        }
        
        return res;
    }
    
    public Usuario readByUsername(Object key) {
        Usuario res = null;
        ResultSet rs;
        try{
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_READ_USERNAME);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            
            while (rs.next()){
                res = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12));
            }
            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_READ_USERNAME);
        }finally{
            conn.closeConnection();
        }
        
        return res;
    }
   
}
