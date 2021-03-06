/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.data;

import com.mysql.jdbc.PreparedStatement;
import com.proyectopoo.beepbeep.classes.Rol;
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
public class RolData implements DataAccess<Rol> {

    private static final String SQL_INSERT = "INSERT INTO ROL(nombreRol) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE ROL SET NOMBREROL = ? WHERE CODROL = ?";
    private static final String SQL_DELETE = "DELETE FROM ROL WHERE CODROL = ?";
    private static final String SQL_READ = "SELECT * FROM ROL WHERE CODROL = ?";
    private static final String SQL_READALL = "SELECT * FROM ROL";
    PreparedStatement ps;
    ConnectionBeep conn = ConnectionBeep.initConnection();
    UserInteractions ui;
    
    @Override
    public boolean insert(Rol g) {
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNombreRol());
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
    public boolean update(Rol c) {
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombreRol());
            ps.setInt(2, c.getCodRol());
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
    public Rol read(Object key) {
        Rol res = null;
        ResultSet rs;
        try{
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            
            while (rs.next()){
                res = new Rol(rs.getInt(1), rs.getString(2));
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
    public ArrayList<Rol> readAll() {
        ArrayList<Rol> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s = conn.getConnection().prepareStatement(SQL_READALL);
            
            rs = ps.executeQuery(SQL_READALL);
            
            while (rs.next()){
                all.add(new Rol(rs.getInt(1), rs.getString(2)));
            }
            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_READ);
        }
        
        return all;
    }



}
