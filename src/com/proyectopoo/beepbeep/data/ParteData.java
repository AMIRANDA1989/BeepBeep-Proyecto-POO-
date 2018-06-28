/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.data;

import com.mysql.jdbc.PreparedStatement;
import com.proyectopoo.beepbeep.classes.Parte;
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
public class ParteData implements DataAccess<Parte> {

    private static final String SQL_INSERT = "INSERT INTO PARTE(nombre, descripcion, precio, categoria) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE PARTE SET nombre = ?, descripcion = ?, precio = ?, categoria = ? WHERE codParte = ?";
    private static final String SQL_DELETE = "DELETE FROM PARTE WHERE codParte = ?";
    private static final String SQL_READ = "SELECT * FROM PARTE WHERE codParte = ?";
    private static final String SQL_READALL = "SELECT * FROM PARTE";
    PreparedStatement ps;
    ConnectionBeep conn = ConnectionBeep.initConnection();
    UserInteractions ui;
    
    @Override
    public boolean insert(Parte g) {
        try {
            conn = ConnectionBeep.initConnection();
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNombre());
            ps.setString(2, g.getDescripcion());
            ps.setInt(3, g.getPrecio());
            ps.setInt(4, g.getCategoria());
            
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE,  "No se pudo realizar el insert " + SQL_INSERT + ex.getMessage());
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
    public boolean update(Parte c) {
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getPrecio());
            ps.setInt(4, c.getCategoria());
            ps.setInt(5, c.getCodParte());
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
    public Parte read(Object key) {
        Parte res = null;
        ResultSet rs;
        try{
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            
            while (rs.next()){
                res = new Parte(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
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
    public ArrayList<Parte> readAll() {
        ArrayList<Parte> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s = conn.getConnection().prepareStatement(SQL_READALL);
            
            rs = ps.executeQuery(SQL_READALL);
            
            while (rs.next()){
                all.add(new Parte(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
            }
            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_READ);
        }
        
        return all;
    }



}
