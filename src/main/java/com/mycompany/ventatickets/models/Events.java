/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * clase que modela lose ventos
 * @author cgalv
 */
public class Events implements Serializable {

    private int id;
    private String name;
    private String synopsis;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private String image;
    private boolean visible;
    private LocalDateTime visibleDate;
    private LocalDateTime hiddenDate;
    private int idResponsible;   
    private Responsible responsible;
    private double vip;
    private double planta_a;
    private double planta_b;
    private double vip_mg;
    private boolean active;
    private String duration;
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the synopsis
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * @param synopsis the synopsis to set
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * @return the initialDate
     */
    public LocalDateTime getInitialDate() {
        return initialDate;
    }
    
    public String getInitialDate(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern); 
        return initialDate.format(formatter);
    }

    /**
     * @param initialDate the initialDate to set
     */
    public void setInitialDate(LocalDateTime initialDate) {
        this.initialDate = initialDate;
    }

    /**
     * @return the finalDate
     */
    public LocalDateTime getFinalDate() {
        return finalDate;
    }
    
    public String getFinalDate(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern); 
        return finalDate.format(formatter);
    }

    /**
     * @param finalDate the finalDate to set
     */
    public void setFinalDate(LocalDateTime finalDate) {
        this.finalDate = finalDate;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the visibleDate
     */
    public LocalDateTime getVisibleDate() {
        return visibleDate;
    }

    /**
     * @param visibleDate the visibleDate to set
     */
    public void setVisibleDate(LocalDateTime visibleDate) {
        this.visibleDate = visibleDate;
    }
    
    public String getVisibleDate(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern); 
        return visibleDate.format(formatter);
    }

    /**
     * @return the hiddenDate
     */
    public LocalDateTime getHiddenDate() {
        return hiddenDate;
    }
    
    public String getHiddenDate(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern); 
        return hiddenDate.format(formatter);
    }

    /**
     * @param hiddenDate the hiddenDate to set
     */
    public void setHiddenDate(LocalDateTime hiddenDate) {
        this.hiddenDate = hiddenDate;
    }

    /**
     * @return the idResponsible
     */
    public int getIdResponsible() {
        return idResponsible;
    }

    /**
     * @param idResponsible the idResponsible to set
     */
    public void setIdResponsible(int idResponsible) {
        this.idResponsible = idResponsible;
    }

    /**
     * @return the responsible
     */
    public Responsible getResponsible() {
        return responsible;
    }

    /**
     * @param responsible the responsible to set
     */
    public void setResponsible(Responsible responsible) {
        this.responsible = responsible;
    }

    /**
     * @return the vip
     */
    public double getVip() {
        return vip;
    }

    /**
     * @param vip the vip to set
     */
    public void setVip(double vip) {
        this.vip = vip;
    }

    /**
     * @return the planta_a
     */
    public double getPlanta_a() {
        return planta_a;
    }

    /**
     * @param planta_a the planta_a to set
     */
    public void setPlanta_a(double planta_a) {
        this.planta_a = planta_a;
    }

    /**
     * @return the planta_b
     */
    public double getPlanta_b() {
        return planta_b;
    }

    /**
     * @param planta_b the planta_b to set
     */
    public void setPlanta_b(double planta_b) {
        this.planta_b = planta_b;
    }

    /**
     * @return the vip_mg
     */
    public double getVip_mg() {
        return vip_mg;
    }

    /**
     * @param vip_mg the vip_mg to set
     */
    public void setVip_mg(double vip_mg) {
        this.vip_mg = vip_mg;
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
    

}
