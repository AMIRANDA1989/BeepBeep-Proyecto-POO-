/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.data;

import java.util.ArrayList;

/**
 *
 * @author allan
 */
public interface DataAccess <Generic>{
    public boolean insert(Generic g);
    public boolean delete(Object key);
    public boolean update(Generic c);
    public Generic read(Object key);
    public ArrayList<Generic> readAll();
}
