/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.data;

import com.mysql.jdbc.PreparedStatement;
import com.proyectopoo.beepbeep.classes.Inventario;
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
public class InventarioData implements DataAccess<Inventario> {

    private static final String SQL_INSERT = "INSERT INTO INVENTARIO (codusuario, codparte) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE INVENTARIO SET codusuario = ?, codparte = ? WHERE codUsuario = ? AND codParte = ?";
    private static final String SQL_DELETE = "DELETE FROM INVENTARIO WHERE codUsuario = ? and codParte = ?";
    private static final String SQL_READ = "SELECT * FROM INVENTARIO WHERE codUsuario = ? and codParte = ?";
    private static final String SQL_READALL = "SELECT * FROM INVENTARIO";
    PreparedStatement ps;
    ConnectionBeep conn = ConnectionBeep.initConnection();
    UserInteractions ui;
    
    @Override
    public boolean insert(Inventario g) {
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_INSERT);
            ps.setInt(1, g.getCodUsuario());
            ps.setInt(2, g.getCodParte());
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
    public boolean update(Inventario c) {
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, c.getCodUsuario());
            ps.setInt(2, c.getCodParte());
            ps.setInt(3, c.getCodUsuario());
            ps.setInt(4, c.getCodParte());
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
    public Inventario read(Object key) {
        Inventario res = null;
        ResultSet rs;
        try{
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            
            while (rs.next()){
                res = new Inventario(rs.getInt(1), rs.getInt(2));
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
    public ArrayList<Inventario> readAll() {
        ArrayList<Inventario> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try{
            s = conn.getConnection().prepareStatement(SQL_READALL);
            
            rs = ps.executeQuery(SQL_READALL);
            
            while (rs.next()){
                all.add(new Inventario(rs.getInt(1), rs.getInt(2)));
            }
            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_READ);
        }
        
        return all;
    }



}
