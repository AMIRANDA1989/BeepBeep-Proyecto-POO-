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

    private static final String SQL_INSERT = "INSERT INTO PARTE(nombre, descripcion, precio, categoria, velocModifier, accelModifier, manModifier) VALUES (?,?,?,?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE PARTE SET nombre = ?, descripcion = ?, precio = ?, categoria = ?, velocModifier = ?, accelModifier = ?, manModifier = ? WHERE codParte = ?";
    private static final String SQL_DELETE = "DELETE FROM PARTE WHERE codParte = ?";
    private static final String SQL_READ = "SELECT * FROM PARTE WHERE codParte = ?";
    private static final String SQL_READALL = "SELECT * FROM PARTE";
    private static final String SQL_BUY_PART = "INSERT INTO INVENTARIO(CODUSUARIO, CODPARTE) VALUES(?,?)";
    private static final String SQL_GET_USER_PARTS = "SELECT a.* FROM parte a INNER JOIN inventario b ON a.codparte = b.codParte where b.codUsuario = ?";
    private static final String SQL_GET_USER_ENGINE = "SELECT a.* FROM parte a INNER JOIN usuario b ON a.codparte = b.codmotor where b.codUsuario = ?";
    private static final String SQL_GET_USER_TIRES = "SELECT a.* FROM parte a INNER JOIN usuario b ON a.codparte = b.codllantas where b.codUsuario = ?";
    private static final String SQL_GET_USER_ACCESORIES = "SELECT a.* FROM parte a INNER JOIN usuario b ON a.codparte = b.codaccesorio where b.codUsuario = ?";

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
            ps.setInt(5, g.getVelocModifier());
            ps.setInt(6, g.getAccelModifier());
            ps.setInt(7, g.getManModifier());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el insert " + SQL_INSERT + ex.getMessage());
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
            ps.setInt(5, c.getVelocModifier());
            ps.setInt(6, c.getAccelModifier());
            ps.setInt(7, c.getManModifier());
            ps.setInt(8, c.getCodParte());
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

        if (conn.getConnection() == null) {

            conn = ConnectionBeep.initConnection();

        }
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());

            rs = ps.executeQuery();

            while (rs.next()) {
                res = new Parte(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
            }
            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_READ);
        } finally {
            conn.closeConnection();
        }

        return res;
    }

    @Override
    public ArrayList<Parte> readAll() {
        ArrayList<Parte> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try {
            s = conn.getConnection().prepareStatement(SQL_READALL);

            rs = s.executeQuery(SQL_READALL);

            while (rs.next()) {
                all.add(new Parte(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
            }

            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_READ);
        }

        return all;
    }

    /*METODOS PERSONALIZADOS **/
    public boolean buyPart(int codUser, int codPart) {
        conn = ConnectionBeep.initConnection();
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_BUY_PART);
            ps.setInt(1, codUser);
            ps.setInt(2, codPart);
            System.out.println("llegue " + codUser + " - " + codPart);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_BUY_PART);
            return false;
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            conn.closeConnection();
        }

        return false;
    }

    public ArrayList<Parte> getInventory(int key) {
        ArrayList<Parte> inventory = new ArrayList();
        ResultSet rs;
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_GET_USER_PARTS);
            ps.setInt(1, key);

            rs = ps.executeQuery();

            while (rs.next()) {
                inventory.add(new Parte(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
            }
            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_GET_USER_PARTS);
        } finally {
            conn.closeConnection();
        }

        return inventory;
    }

}
