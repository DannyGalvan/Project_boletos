/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets;

import com.mycompany.ventatickets.models.Asientos;
import com.mycompany.ventatickets.models.AsientosEventoBoletos;
import com.mycompany.ventatickets.models.DatesEvent;
import com.mycompany.ventatickets.models.Events;
import com.mycompany.ventatickets.models.PagosBoleto;
import com.mycompany.ventatickets.models.User;
import java.util.ArrayList;

/**
 * Clase de contexto para la aplicación VentaTickets.
 * Esta clase representa el contexto global de la aplicación.
 * Contiene información y configuraciones compartidas entre las diferentes partes de la aplicación.
 * 
 * @author cgalv
 */
public class Context {
    private static User user = null;
    private static boolean isLogIn = false;
    private static Events event = null;
    private static DatesEvent date = null;
    private static PagosBoleto pago = null;
    private static ArrayList<Asientos> asientosPago = new ArrayList<>();
    private static ArrayList<AsientosEventoBoletos> datosBoleto = new ArrayList<>();
    private static double total = 0.00;
    
     /**
     * Constructor predeterminado de la clase Context.
     * Este constructor inicializa el contexto de la aplicación.
     */
    public Context() {
        // Código del constructor
    }

    /**
     * Obtiene el usuario logeado
     * 
     * @return the user
     */
    public static User getUser() {
        return user;
    }

    /**
     * Obtiene si ya iniciamos sesion
     * 
     * @return the isLogIN
     */
    public static boolean isIsLogIn() {
        return isLogIn;
    }

    /**
     * Asigna el valor a si estamos logeados
     * 
     * @param aIsLogIN the isLogIN to set
     */
    public static void setIsLogIN(boolean aIsLogIN) {
        isLogIn = aIsLogIN;
    }    
    
    /**
     * metodo que nos deja ver el boton del Dashboar
     * si en dado caso somos administradores
     * @return true or false
     */
    public static boolean isVisibleDashboard(){
        
        if (!isLogIn) {
            return false;
        }
        
        return user.getIdRol() == 1;
        
    }
    
    /**
     * metodo que nos redirije al dashboard
     * 
     */
    public static void redirectToDashboard(){
        App.setRoot("Home", "Dashboard");
    }
    
     /**
     * Método para iniciar sesión.
     * Permite iniciar sesión con el usuario proporcionado.
     * @param model El objeto de usuario para iniciar sesión.
     */
    public static void LogIn(User model){
        user = model;
        setIsLogIN(true);
    }
    
     /**
     * Método para cerrar sesión.
     * Permite iniciar sesión con el usuario proporcionado.
     */
    public static void LogOut(){
        user = null;
        setIsLogIN(false);
        App.setRoot("Auth");
    }    

    /**
     * @return the event
     */
    public static Events getEvent() {
        return event;
    }

    /**
     * @param aEvent the event to set
     */
    public static void setEvent(Events aEvent) {
        event = aEvent;
    }

    /**
     * @return the date
     */
    public static DatesEvent getDate() {
        return date;
    }

    /**
     * @param aDate the date to set
     */
    public static void setDate(DatesEvent aDate) {
        date = aDate;
    }

    /**
     * @return the asientosPago
     */
    public static ArrayList<Asientos> getAsientosPago() {
        return asientosPago;
    }

    /**
     * @param aAsientosPago the asientosPago to set
     */
    public static void setAsientosPago(ArrayList<Asientos> aAsientosPago) {
        asientosPago = aAsientosPago;
    }

    /**
     * @return the total
     */
    public static double getTotal() {
        return total;
    }

    /**
     * @param aTotal the total to set
     */
    public static void setTotal(double aTotal) {
        total = aTotal;
    }

    /**
     * @return the datosBoleto
     */
    public static ArrayList<AsientosEventoBoletos> getDatosBoleto() {
        return datosBoleto;
    }

    /**
     * @param aDatosBoleto the datosBoleto to set
     */
    public static void setDatosBoleto(ArrayList<AsientosEventoBoletos> aDatosBoleto) {
        datosBoleto = aDatosBoleto;
    }

    /**
     * @return the pago
     */
    public static PagosBoleto getPago() {
        return pago;
    }

    /**
     * @param aPago the pago to set
     */
    public static void setPago(PagosBoleto aPago) {
        pago = aPago;
    }
}
