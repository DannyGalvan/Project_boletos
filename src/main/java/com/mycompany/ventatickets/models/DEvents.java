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
import java.util.ArrayList;


/**
 * clase que manipula la data de los eventos
 * @author cgalv
 */
public class DEvents {
   
    /**
    * constructor por defecto de la clase DEvents
    * 
    */
    public DEvents(){
    
    }   
    
        /**
     * Devuelve una lista de eventos.
     * 
     * @return Una lista de eventos.
     */
    public ArrayList<Events> ListEvents(){
        
         ArrayList<Events> listEvents = new ArrayList<>();
         
         try (Statement sql = Conexion.getConection().createStatement()) {
             StringBuilder query = new StringBuilder();
             query.append("select idevento, e.nombre, sinopsis, fechainicio, fechafin, ");
             query.append("foto, visible, fechavisible, fechaoculto, e.idresponsable, ");
             query.append("vip, planta_a, planta_b, vip_mg, estado, r.nombre as responsable, tiporesponsable, duracion ");
             query.append("from eventos as e inner join responsables as r on e.idevento = r.idresponsable");
             ResultSet resultado = sql.executeQuery(query.toString());
             
             while(resultado.next()){
                 Events events = new Events();
                 events.setId(resultado.getInt("idevento"));
                 events.setName(resultado.getString("nombre"));  
                 events.setSynopsis(resultado.getString("sinopsis"));
                 events.setInitialDate(resultado.getTimestamp("fechainicio").toLocalDateTime());
                 events.setFinalDate(resultado.getTimestamp("fechafin").toLocalDateTime());
                 events.setImage(resultado.getString("foto"));
                 events.setVisible(resultado.getBoolean("visible"));
                 events.setVisibleDate(resultado.getTimestamp("fechavisible").toLocalDateTime());
                 events.setHiddenDate(resultado.getTimestamp("fechaoculto").toLocalDateTime());
                 events.setIdResponsible(resultado.getInt("idresponsable"));
                 events.setVip(resultado.getDouble("vip"));
                 events.setPlanta_a(resultado.getDouble("planta_a"));
                 events.setPlanta_b(resultado.getDouble("planta_b"));
                 events.setVip_mg(resultado.getDouble("vip_mg"));
                 Responsible responsible = new Responsible();
                 responsible.setId(resultado.getInt("idresponsable"));
                 responsible.setName(resultado.getString("responsable"));
                 responsible.setType(resultado.getInt("tiporesponsable"));
                 events.setResponsible(responsible);
                 events.setDuration(resultado.getString("duracion"));
                 listEvents.add(events);
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return listEvents;
    }
    
     
      /**
     * Devuelve un eventos.
     * 
     * @param id es el id del evento
     * @return Una evento.
     */
     public Events EventById(int id){
        
         Events model = null;
         
        try (Statement sql = Conexion.getConection().createStatement()) {
             StringBuilder query = new StringBuilder();
             query.append("select idevento, e.nombre, sinopsis, fechainicio, fechafin, ");
             query.append("foto, visible, fechavisible, fechaoculto, e.idresponsable, ");
             query.append("vip, planta_a, planta_b, vip_mg, estado, r.nombre as responsable, tiporesponsable, duracion ");
             query.append("from eventos as e inner join responsables as r on e.idevento = r.idresponsable ");
             query.append(String.format("where idevento = %s", id));
             ResultSet resultado = sql.executeQuery(query.toString());
             
             while(resultado.next()){
                 Events events = new Events();
                 events.setId(resultado.getInt("idevento"));
                 events.setName(resultado.getString("nombre"));  
                 events.setSynopsis(resultado.getString("sinopsis"));
                 events.setInitialDate(resultado.getTimestamp("fechainicio").toLocalDateTime());
                 events.setFinalDate(resultado.getTimestamp("fechafin").toLocalDateTime());
                 events.setImage(resultado.getString("foto"));
                 events.setVisible(resultado.getBoolean("visible"));
                 events.setVisibleDate(resultado.getTimestamp("fechavisible").toLocalDateTime());
                 events.setHiddenDate(resultado.getTimestamp("fechaoculto").toLocalDateTime());
                 events.setIdResponsible(resultado.getInt("idresponsable"));
                 events.setVip(resultado.getDouble("vip"));
                 events.setPlanta_a(resultado.getDouble("planta_a"));
                 events.setPlanta_b(resultado.getDouble("planta_b"));
                 events.setVip_mg(resultado.getDouble("vip_mg"));
                 Responsible responsible = new Responsible();
                 responsible.setId(resultado.getInt("idresponsable"));
                 responsible.setName(resultado.getString("responsable"));
                 responsible.setType(resultado.getInt("tiporesponsable"));
                 events.setResponsible(responsible);
                 events.setDuration(resultado.getString("duracion"));
                 model = events;
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return model;
    }
     
      /**
     * Devuelve una booleano que indica si se creo el evento o no.
     * 
     * @param event recibe un evento para luego crearlo
     * @return Un boleano.
     */
    public boolean CreateEvent(Events event){
        boolean results = false;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("insert into eventos(nombre,sinopsis,fechainicio,fechafin,foto,visible,fechavisible,fechaoculto,idresponsable,vip,planta_a,planta_b,vip_mg,duracion)");
             query.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
             
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setString(1, event.getName());
             sql.setString(2, event.getSynopsis());
             sql.setTimestamp(3, Timestamp.valueOf(event.getInitialDate()));
             sql.setTimestamp(4, Timestamp.valueOf(event.getFinalDate()));
             sql.setString(5,event.getImage());
             sql.setBoolean(6, event.isVisible());
             sql.setTimestamp(7, Timestamp.valueOf(event.getVisibleDate()));
             sql.setTimestamp(8, Timestamp.valueOf(event.getHiddenDate()));
             sql.setInt(9, event.getIdResponsible());
             sql.setDouble(10, event.getVip());
             sql.setDouble(11, event.getPlanta_a());
             sql.setDouble(12, event.getPlanta_b());
             sql.setDouble(13, event.getVip_mg());
             sql.setString(14, event.getDuration());
             
             int rowAffected = sql.executeUpdate();
             
             results = rowAffected != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return results;        
    }
    
     /**
     * Devuelve una booleano que indica si se modifico el evento o no.
     * 
     * @param event recibe un evento para luego modificarlo
     * @return Un boleano.
     */
    public boolean ModifyEvent(Events event){
        boolean results = false;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("update eventos set nombre = ?, sinopsis = ?, fechainicio = ?, fechafin = ?, foto = ?, visible = ?, fechavisible = ?, fechaoculto = ?, idresponsable = ?,");
             query.append("vip = ?,planta_a = ?,planta_b = ?,vip_mg = ?, duracion = ? ");
             query.append("where idevento = ?");
             
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setString(1, event.getName());
             sql.setString(2, event.getSynopsis());
             sql.setTimestamp(3, Timestamp.valueOf(event.getInitialDate()));
             sql.setTimestamp(4, Timestamp.valueOf(event.getFinalDate()));
             sql.setString(5,event.getImage());
             sql.setBoolean(6, event.isVisible());
             sql.setTimestamp(7, Timestamp.valueOf(event.getVisibleDate()));
             sql.setTimestamp(8, Timestamp.valueOf(event.getHiddenDate()));
             sql.setInt(9, event.getIdResponsible());
             sql.setDouble(10, event.getVip());
             sql.setDouble(11, event.getPlanta_a());
             sql.setDouble(12, event.getPlanta_b());
             sql.setDouble(13, event.getVip_mg());
             sql.setString(14, event.getDuration());
             sql.setInt(15, event.getId());
            
             
             int rowAffected = sql.executeUpdate();
             
             results = rowAffected != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return results;        
    }

}
