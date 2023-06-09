/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.models;

import com.mycompany.ventatickets.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author cgalv
 */
public class DAsientosEventoBoletos {
    
    
     /**
     * metodo que consulta lista de usuarios
     * 
     * @return a List User
     */
    public ArrayList<AsientosEventoBoletos> ListadoDeBoletos(){
        
         ArrayList<AsientosEventoBoletos> listaBoletos = new ArrayList<>();
         
         try (Statement sql = Conexion.getConection().createStatement()) {
             String query = "select * from asientos_evento_boletos";
             ResultSet resultado = sql.executeQuery(query);
             
             while(resultado.next()){
                 AsientosEventoBoletos model = new AsientosEventoBoletos();
                 model.setId(resultado.getInt("idboleto"));
                 model.setIdasiento(resultado.getInt("idasiento"));  
                 model.setIdfecha(resultado.getInt("idfecha"));
                 model.setPrecio(resultado.getDouble("precio"));
                 model.setCodigoAsiento(resultado.getString("codigoasiento"));
                 model.setFechaCompra(resultado.getTimestamp("fechacompra").toLocalDateTime());
                 model.setName(resultado.getString("nombre"));
                 model.setLastName(resultado.getString("apellido"));
                 model.setEmail(resultado.getString("correo"));
                 model.setNumber(resultado.getString("numero"));
                
                 listaBoletos.add(model);
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return listaBoletos;
    }
    
    public int CreateTicket(AsientosEventoBoletos model){
        int results = 0;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("insert into asientos_evento_boletos(idasiento,idfecha,precio,codigoasiento,nombre,apellido,correo,numero)");
             query.append("VALUES (?,?,?,?,?,?,?,?)");
             
             PreparedStatement sql = conn.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
             sql.setInt(1, model.getIdasiento());
             sql.setInt(2, model.getIdfecha());
             sql.setDouble(3, model.getPrecio());
             sql.setString(4, model.getCodigoAsiento());
             sql.setString(5, model.getName());
             sql.setString(6, model.getLastName());
             sql.setString(7,model.getEmail());
             sql.setString(8,model.getNumber());
             
             int rowAffected = sql.executeUpdate();
             
             if (rowAffected != 0) {
                 ResultSet generatedKey = sql.getGeneratedKeys();
                 if (generatedKey.next()) {
                     results = generatedKey.getInt(1);
                 }
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return results;        
    }
}
