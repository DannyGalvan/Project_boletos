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
import java.util.ArrayList;

/**
 *
 * @author cgalv
 */
public class DResponsible {
    
     /**
    * constructor por defecto de la clase DResponsible
    * 
    */
    public DResponsible(){
    
    }   
    
        /**
     * metodo de la lista de responsables.
     * 
     * @return lista de responsables.
     */
    public ArrayList<Responsible> ListResponsible(){
        
         ArrayList<Responsible> listResponsible = new ArrayList<>();
         
         try (Statement sql = Conexion.getConection().createStatement()) {
             String query = "select * from responsables";
             ResultSet resultado = sql.executeQuery(query);
             
             while(resultado.next()){
                 
                Responsible responsible = new Responsible();
                responsible.setId(resultado.getInt("idresponsable"));
                responsible.setName(resultado.getString("nombre"));  
                responsible.setType(resultado.getInt("tiporesponsable"));
                 
                listResponsible.add(responsible);
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return listResponsible;
    }
    
     /**
     * Devuelve un eventos.
     * 
     * @param id es el id del responsable
     * @return Un responsable.
     */
     public Responsible ResponsibleById(int id){
        
         Responsible model = null;
         
         try (Connection conn = Conexion.getConection()) {
             String query = "select * from responsables where id = ?";
             PreparedStatement sql = conn.prepareStatement(query);
             sql.setInt(1, id);
             ResultSet resultado = sql.executeQuery();
             
             while(resultado.next()){
                 
                 Responsible responsible = new Responsible();
                 responsible.setId(resultado.getInt("id"));
                 responsible.setName(resultado.getString("nombre"));  
                 responsible.setType(resultado.getInt("tiporesponsable"));
                 
                 model = responsible;
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return model;
    }
    
      /**
     * Devuelve una booleano que indica si se creo el responsable o no.
     * 
     * @param  responsible recibe un responsable para luego crearlo
     * @return Un boleano.
     */
    public boolean CreateResponsible(Responsible responsible){
        boolean results = false;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("insert into responsables(nombre, tiporesponsable)");
             query.append("VALUES (?,?)");
             
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setString(1, responsible.getName());
             sql.setInt(2, responsible.getType());
             
             int rowAffected = sql.executeUpdate();
             
             results = rowAffected != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return results;        
    }
    
      /**
     * Devuelve una booleano que indica si se modifico el responsable o no.
     * 
     * @param  responsible recibe un responsable para luego modificarlo
     * @return Un boleano.
     */
    public boolean ModifyResponsible(Responsible responsible){
        boolean results = false;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("update responsables set nombre = ?, tiporesponsable = ? ");
             query.append("where id = ?");
             
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setString(1, responsible.getName());
             sql.setInt(2, responsible.getType());
             sql.setInt(3,responsible.getId());
             int rowAffected = sql.executeUpdate();
             
             results = rowAffected != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return results;        
    }
}
