/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.models;

/**
 * clase para el modelo de asientos
 * @author cgalv
 */
public class Asientos {
    private int id;
    private String file;
    private int column;
    private String lado;
    private int idSection;
    private boolean active;
    private boolean occupied;
    private Seccions section = new Seccions();
    private AsientosEventoBoletos ticket = new AsientosEventoBoletos();
    
    /**
     * Constructor por defecto de la clase Asientos.
     * Crea una instancia de Asientos sin parámetros.
     */
    public Asientos() {
        // Código del constructor
    }

     /**
      * Obtiene el ID del asiento.
      *
      * @return El ID del asiento.
      */
    public int getId() {
        return id;
    }

    /**
     * Asigna valor al Id
     * 
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el valor de la fila
     * 
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * Asigna valor a la fila
     * 
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * Obtiene el valor de la columna
     * 
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Asigna el valor de la columna
     * 
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Obtiene el valor del Lado
     * 
     * @return the lado
     */
    public String getLado() {
        return lado;
    }

    /**
     * Asigna el valor del lado
     * 
     * @param lado the lado to set
     */
    public void setLado(String lado) {
        this.lado = lado;
    }

    /**
     * Obtiene el id de la Seccion
     * 
     * @return the idSection
     */
    public int getIdSection() {
        return idSection;
    }

    /**
     * Asigna el id de la seccion
     * 
     * @param idSection the idSection to set
     */
    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }

    /**
     * Obtiene la seccion con sus valores
     * 
     * @return the section
     */
    public Seccions getSection() {
        return section;
    }

    /**
     * Asigna el valor de la seccion
     * 
     * @param section the section to set
     */
    public void setSection(Seccions section) {
        this.section = section;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the occupied
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * @param occupied the occupied to set
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * @return the ticket
     */
    public AsientosEventoBoletos getTicket() {
        return ticket;
    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(AsientosEventoBoletos ticket) {
        this.ticket = ticket;
    }
}
