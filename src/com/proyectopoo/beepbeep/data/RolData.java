/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.data;

import com.mysql.jdbc.PreparedStatement;
import com.proyectopoo.beepbeep.classes.Rol;
import com.proyectopoo.beepbeep.engine.UserInteractions;
import java.sql.SQLException;
import java.util.ArrayList;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Rol c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rol read(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Rol> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
