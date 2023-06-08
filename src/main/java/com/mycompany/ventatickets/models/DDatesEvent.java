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
 *
 * @author cgalv
 */
public class DDatesEvent {
    
    
    public ArrayList<DatesEvent> ListDateEvents(int idEvento){
        
         ArrayList<DatesEvent> listEvents = new ArrayList<>();
         
         try (Statement sql = Conexion.getConection().createStatement()) {
             StringBuilder query = new StringBuilder();
             query.append(String.format("select * from fechas_eventos where idevento = %s", idEvento));
             ResultSet resultado = sql.executeQuery(query.toString());
             
             while(resultado.next()){
                 DatesEvent events = new DatesEvent();
                 events.setIdfecha(resultado.getInt("idfecha"));
                 events.setDuration(resultado.getString("duracion_evento"));
                 events.setFecha_evento(resultado.getTimestamp("fecha_evento").toLocalDateTime());
                 events.setIdevento(resultado.getInt("idevento"));
                 events.setActive(resultado.getBoolean("Activo"));
                 listEvents.add(events);
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return listEvents;
    }
    
       /**
     * Devuelve una lista de fechas de un evento.
     * 
     * @param idEvento id del evento
     * @return Una lista de fechas de un evento.
     */
    public ArrayList<DatesEvent> ListDateEvents(int idEvento, boolean active){
        
         ArrayList<DatesEvent> listEvents = new ArrayList<>();
         
         try (Statement sql = Conexion.getConection().createStatement()) {
             StringBuilder query = new StringBuilder();
             query.append(String.format("select * from fechas_eventos where idevento = %s and activo = %s order by fecha_evento asc", idEvento,active));
             ResultSet resultado = sql.executeQuery(query.toString());
             
             while(resultado.next()){
                 DatesEvent events = new DatesEvent();
                 events.setIdfecha(resultado.getInt("idfecha"));
                 events.setDuration(resultado.getString("duracion_evento"));
                 events.setFecha_evento(resultado.getTimestamp("fecha_evento").toLocalDateTime());
                 events.setIdevento(resultado.getInt("idevento"));
                 events.setActive(resultado.getBoolean("Activo"));
                 listEvents.add(events);
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return listEvents;
    }    
   
    
      /**
     * Devuelve una lista de fechas de un evento.
     * 
     * @param date recibe una fecha evento
     * @return si fue creada o no exitosamente.
     */
    public boolean CreateDateEvent(DatesEvent date){
        
         boolean result = false;
         
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("insert into fechas_eventos(fecha_evento, duracion_evento, idevento)");
             query.append("values(?,?,?)");
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setTimestamp(1, Timestamp.valueOf(date.getFecha_evento()));
             sql.setString(2, date.getDuration());
             sql.setInt(3, date.getIdevento());  
             
             result = sql.executeUpdate() != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return result;
    }
    
      /**
     * Devuelve una lista de fechas de un evento.
     * 
     * @param date recibe una fecha evento
     * @return si fue creada o no exitosamente.
     */
    public boolean ModifyDateEvent(DatesEvent date){
        
         boolean result = false;
         
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("update fechas_eventos set fecha_evento = ?, duracion_evento = ?, idevento = ?, activo = ? ");
             query.append("where idfecha = ?");
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setTimestamp(1, Timestamp.valueOf(date.getFecha_evento()));
             sql.setString(2, date.getDuration());
             sql.setInt(3, date.getIdevento());  
             sql.setBoolean(4, date.getActive());
             sql.setInt(5, date.getIdfecha());
             
             result = sql.executeUpdate() != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return result;
    }
    
}
