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
public class CarreraData implements DataAccess<Carrera> {

    private static final String SQL_INSERT = "INSERT INTO CARRERA(codJugador1, tiempoJugador1, codJugador2, tiempojugador2) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE CARRERA SET codJugador1 = ?, tiempoJugador1 = ?, codJugador2 = ?, tiempojugador2 = ? WHERE codCarrera = ?";
    private static final String SQL_DELETE = "DELETE FROM CARRERA WHERE codcarrera = ?";
    private static final String SQL_READ = "SELECT codcarrera,codjugador1,tiempojugador1,codjugador2,tiempojugador2 FROM carrera WHERE codcarrera = ?";
    private static final String SQL_READALL = "SELECT * FROM carrera";
    private static final String SQL_SEARCH = "SELECT CODCARRERA, CODJUGADOR1, TIEMPOJUGADOR1, CODJUGADOR2, TIEMPOJUGADOR2 FROM carrera where codJugador1 <> ? and codjugador2 = 0 order by creation_date ASC limit 1";
    private static final String SQL_CREATE_RACE = "INSERT INTO CARRERA(CODJUGADOR1) VALUES (?)";
    private static final String SQL_GET_NEW_RACE = "SELECT CODCARRERA, CODJUGADOR1, TIEMPOJUGADOR1, CODJUGADOR2, TIEMPOJUGADOR2 FROM carrera where codJugador1 = ? and codjugador2 is null order by creation_date DESC limit 1";

    PreparedStatement ps;
    ConnectionBeep conn = ConnectionBeep.initConnection();
    UserInteractions ui;

    @Override
    public boolean insert(Carrera g) {
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_CREATE_RACE);
            ps.setInt(1, g.getCodJugador1());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el insert " + SQL_CREATE_RACE);
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
    public boolean update(Carrera c) {
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_UPDATE);
            ps.setInt(1, c.getCodJugador1());
            ps.setInt(2, c.getTiempoJugador1());
            ps.setInt(3, c.getCodJugador2());
            ps.setInt(4, c.getTiempoJugador2());
            ps.setInt(5, c.getCodCarrera());
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
    public Carrera read(Object key) {
        Carrera res = null;
        ResultSet rs;
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());

            rs = ps.executeQuery();

            while (rs.next()) {
                res = new Carrera(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
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
    public ArrayList<Carrera> readAll() {
        ArrayList<Carrera> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try {
            s = conn.getConnection().prepareStatement(SQL_READALL);

            rs = ps.executeQuery(SQL_READALL);

            while (rs.next()) {
                all.add(new Carrera(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
            }
            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_READ);
        }

        return all;
    }

    /*Search sirve para buscar una carrera abierta en la base*/
    public Carrera search(Object key) {
        Carrera res = null;
        ResultSet rs;
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_SEARCH);
            ps.setString(1, key.toString());

            rs = ps.executeQuery();

            while (rs.next()) {
                res = new Carrera(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
            }

            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_SEARCH);
        } finally {
            conn.closeConnection();
        }

        return res;
    }

    public boolean createNewRace(int key) {
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_CREATE_RACE);
            ps.setInt(1, key);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el insert " + SQL_CREATE_RACE);
            return false;
        } finally {
            conn.closeConnection();
        }

        return false;
    }

    public Carrera getNewRace(int key) {
        Carrera res = null;
        ResultSet rs;
        try {
            ps = (PreparedStatement) conn.getConnection().prepareStatement(SQL_GET_NEW_RACE);
            ps.setInt(1, key);

            rs = ps.executeQuery();

            while (rs.next()) {
                res = new Carrera(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
            }
            rs.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "No se pudo realizar el query: " + SQL_GET_NEW_RACE);
        } finally {
            conn.closeConnection();
        }

        return res;
    }

}
