/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.Conexion;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Esta es la conexion
 * @author cgalv
 */

public class Conexion {  
    
    private static final Dotenv dotenv = Dotenv.load();
    
     /**
     * Constructor predeterminado de la clase Conexion.
     * Permite establecer una conexión a la base de datos.
     */
    public Conexion() {
        // Código del constructor
    }    
    
   /**
      *Obtiene una instancia de nuestra conexion
      *
      * @return la conexion.
      */
    public static Connection getConection(){
       try{
           Connection connection = DriverManager.getConnection(dotenv.get("URL"),dotenv.get("USER"), dotenv.get("PASSWORD_BD"));            
           return connection;
       }
       catch (SQLException e) {
           System.out.println(e.toString());
           return null;
       }
    }
}
