/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectopoo.beepbeep.engine;

import com.proyectopoo.beepbeep.classes.Parte;
import com.proyectopoo.beepbeep.classes.Usuario;
import com.proyectopoo.beepbeep.data.ParteData;
import com.proyectopoo.beepbeep.data.UsuarioData;
import java.util.ArrayList;

/**
 *
 * @author allan
 */
public class ItemInteractions {
    ParteData partDAO = new ParteData(); //acceso a datos de partes de vehiculo
    private ArrayList<Parte> parts = new ArrayList();
    private Usuario user; //Usuario que está comprando items
    
    private int ICON_POS_X; //posicion en eje X para iconos de tienda o inventario
    private int ICON_POS_Y; //posicion en eje Y para iconos de tienda o inventario
    
    public ArrayList<Parte> getParts() {
        return parts;
    }
   
    /**
     * Sirve para colocar los items a la venta en la tienda
     */
    public void setupStore(){
        ICON_POS_X = 50;
        ICON_POS_Y = 260;
        
        int lineItem = 1;
        
        //Obteniendo el listado de partes disponibles
        parts = partDAO.readAll();
        
        //configurando la posicion de los iconos de los items
        for(Parte p : parts){
            p.setPosX(ICON_POS_X);
            p.setPoxY(ICON_POS_Y);
            
            if(lineItem <= 8){
                ICON_POS_X += 50;
                lineItem++;
            }else{
                ICON_POS_X = 50;
                ICON_POS_Y += 60;
                lineItem = 1;
            }
            
        }
        
    }
    
    public boolean buyItem(int userCode, int partCode){
        return this.partDAO.buyPart(userCode, partCode);
    }
}
