/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author cgalv
 */
public class DatesEvent {
    private int idfecha;
    private LocalDateTime fecha_evento;
    private String duration;
    private int idevento;
    private boolean active;

    /**
     * @return the idfecha
     */
    public int getIdfecha() {
        return idfecha;
    }

    /**
     * @param idfecha the idfecha to set
     */
    public void setIdfecha(int idfecha) {
        this.idfecha = idfecha;
    }

    /**
     * @return the fecha_evento
     */
    public LocalDateTime getFecha_evento() {
        return fecha_evento;
    }
    
     /**
     * @param pattern the fecha_evento to set
     * @return the string fecha_evento
     */
    public String getFecha_evento(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern); 
        return fecha_evento.format(formatter);
    }

    /**
     * @param fecha_evento the fecha_evento to set
     */
    public void setFecha_evento(LocalDateTime fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * @return the idevento
     */
    public int getIdevento() {
        return idevento;
    }

    /**
     * @param idevento the idevento to set
     */
    public void setIdevento(int idevento) {
        this.idevento = idevento;
    }   

    /**
     * @return the active
     */
    public boolean getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
}
