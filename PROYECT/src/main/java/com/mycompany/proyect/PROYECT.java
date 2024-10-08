/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author jose
 */
public class PROYECT {

    public static void main(String[] args) {
        conectarBasseDeDatos();
        jFrameIndex frame = new jFrameIndex();
        frame.setVisible(true);
    }
    
    public static void conectarBasseDeDatos(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca","root","");
            System.out.println("Conectado");
            
        }catch(Exception ex){
            System.out.println("no Conectado");
            ex.printStackTrace();
        }
    }
}
