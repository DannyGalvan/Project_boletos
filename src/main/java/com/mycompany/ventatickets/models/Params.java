/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.models;

/**
 * Clase de ejemplo que utiliza un parámetro genérico.
 * 
 * @param <T> el tipo de elemento que se utilizará en la clase.
 */

public class Params<T> {
    private T data;
    
    /**
     * get a Data
     * 
     * @return Data of Type
     */
    public T getDato() {
        return data;
    }

    /**
     * set a Data
     * @param dato Data of Type
     */
    public void setDato(T dato) {
        this.data = dato;
    } 
}
