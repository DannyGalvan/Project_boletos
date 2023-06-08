/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.models;

/**
 *
 * @author cgalv
 */
public class TipoResponsable {
    private int id;
    private String Nombre;
    
     /**
     * constructor para inicializar el objeto
     * 
     * @param id recibe el id 
     * @param nombre recibe el nombre
     */
    public TipoResponsable(int id, String nombre){
        this.id = id;
        this.Nombre = nombre;
    }

    /**
     * retorna el id del tipo
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * asigna el id del tipo
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * retorna el nombre del tipo
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * asigna el nombre al tipo de dato
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
}
