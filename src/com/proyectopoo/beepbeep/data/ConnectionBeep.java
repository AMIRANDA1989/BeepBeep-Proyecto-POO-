/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.data;

import com.mysql.jdbc.Connection;
import static com.oracle.nio.BufferSecrets.instance;
import com.proyectopoo.beepbeep.engine.UserInteractions;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allan
 */
public class ConnectionBeep {
    private String userName;
    private String password;
    private String driver;
    private String url;
    private Connection conn;
    public static ConnectionBeep instance;
    private UserInteractions ui = new UserInteractions();
    
    public synchronized static ConnectionBeep initConnection(){
        if(instance == null){
            return new ConnectionBeep();
        }
        return instance;
    }
    
    public ConnectionBeep(){
        //setupConnection();
        ui.showMessage(ui.INFO_MESSAGE, "Iniciando Conexion");
        readConfig(); //se leerá el archivo para ejecutar la configuracion
        try{
            Class.forName(this.driver);
            
            conn = (Connection) DriverManager.getConnection(this.url, this.userName, this.password);
            ui.showMessage(ui.INFO_MESSAGE, "Conexion realizada con exito");
            
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("" + ex.getMessage());
        }
    }
    
    private void setupConnection(){
        userName = "root";
        password = "";
        driver = "jdbc:mysql://localhost/db_beep";
        
        
    }
    
    public Connection getConnection(){
        return conn;
    }
    
    public void closeConnection(){
        try {
            this.conn.close();
        } catch (SQLException ex) {
            ui.showMessage(ui.ERROR_MESSAGE, "Conexion cerrada con exito");
        }
        ui.showMessage(ui.INFO_MESSAGE, "Conexion cerrada con exito");
    }
    
    /**
     * 
     */
    private void readConfig(){
        String fileName = "config.txt"; //Nombre del archivo
        String line = null; //inicializador de la linea a leers
        int counter = 1;
        try {
            //Filereader es el encargado de abrir el archivo
            FileReader fileReader = new FileReader(fileName);
            //BufferReader es el encargado de leer el archivo abierto
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                
                switch(counter){
                    case 1://La primera linea es el username
                        userName = line;
                        System.out.println("Username:" + userName);
                        //break;
                        
                    case 2://segunda linea es el password
                        password = ""+line;
                        System.out.println("Password:" + password);
                        
                    case 3://tercera linea es la url
                        url = line;
                        System.out.println("URL:" + url);
                    
                    case 4:
                        driver = line;
                        System.out.println(line);
                }
                
                counter++;
            }   

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("No se pudo abrir el archivo '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("No se pudo leer el archivo '" + fileName + "'");                  
        }  
    }
    
}
