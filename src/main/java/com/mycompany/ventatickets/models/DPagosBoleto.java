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
public class DPagosBoleto {
    
    public ArrayList<PagosBoleto> ListadoDePagos(){
        
         ArrayList<PagosBoleto> listaPagos = new ArrayList<>();
         
         try (Statement sql = Conexion.getConection().createStatement()) {
             String query = "select * from pago_boletos";
             ResultSet resultado = sql.executeQuery(query);
             
             while(resultado.next()){
                 PagosBoleto model = new PagosBoleto();
                 model.setId(resultado.getInt("idpago"));
                 model.setName(resultado.getString("nombre_cliente"));  
                 model.setNumber(resultado.getString("no_tarjeta"));
                 model.setCode(resultado.getString("codigo_postal"));
                 model.setAdress(resultado.getString("direccion"));
                 model.setDate(resultado.getTimestamp("fechacompra").toLocalDateTime());
                 model.setQuantity(resultado.getInt("cantidad_boletos"));
                 model.setTotal(resultado.getDouble("total"));
                 
                 listaPagos.add(model);
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return listaPagos;
    }
    
    public int CreatePago(PagosBoleto model){
        int results = 0;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("insert into pago_boletos(idcliente,nombre_cliente,no_tarjeta,codigo_postal,direccion,cantidad_boletos,total,fechacompra)");
             query.append("VALUES (?,?,?,?,?,?,?,?)");
             
             PreparedStatement sql = conn.prepareStatement(query.toString(),PreparedStatement.RETURN_GENERATED_KEYS);
             sql.setInt(1, model.getIdCliente());
             sql.setString(2, model.getName());
             sql.setString(3, model.getNumber());
             sql.setString(4, model.getCode());             
             sql.setString(5,model.getAdress());             
             sql.setInt(6, model.getQuantity());
             sql.setDouble(7, model.getTotal());
             sql.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
             
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
