/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.models;

import java.time.LocalDateTime;

/**
 *
 * @author cgalv
 */
public class AsientosEventoBoletos {
    private int id;
    private int idasiento;
    private int idfecha;
    private double precio;
    private String codigoAsiento;
    private LocalDateTime fechaCompra;
    private String name;
    private String lastName;
    private String email;
    private String number;

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
     * @return the idasiento
     */
    public int getIdasiento() {
        return idasiento;
    }

    /**
     * @param idasiento the idasiento to set
     */
    public void setIdasiento(int idasiento) {
        this.idasiento = idasiento;
    }

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
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the codigoAsiento
     */
    public String getCodigoAsiento() {
        return codigoAsiento;
    }

    /**
     * @param codigoAsiento the codigoAsiento to set
     */
    public void setCodigoAsiento(String codigoAsiento) {
        this.codigoAsiento = codigoAsiento;
    }

    /**
     * @return the fechaCompra
     */
    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    /**
     * @param fechaCompra the fechaCompra to set
     */
    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
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
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }
}
