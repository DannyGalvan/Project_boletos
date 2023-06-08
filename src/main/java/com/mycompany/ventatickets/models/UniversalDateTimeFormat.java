/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * clase que maneja formatos de fecha y hora
 * @author cgalv
 */
public class UniversalDateTimeFormat {
    private static String ddMMyyy_HHmmss = "dd/MM/yyyy HH:mm:ss";
    private static String guionddMMyyy_HHmmss = "dd-MM-yyyy HH:mm:ss";
    private static String ddMMyyy = "dd/MM/yyyy";
    private static String guionddMMyyy = "dd-MM-yyyy";
    private static String ddMMYY_HHmmss12H = "dd/MM/yyyy HH:mm:ss a";

    /**
     * @return the ddMMyyy_HHmmss
     */
    public static String getDdMMyyy_HHmmss() {
        return ddMMyyy_HHmmss;
    }

    /**
     * @param aDdMMyyy_HHmmss the ddMMyyy_HHmmss to set
     */
    public static void setDdMMyyy_HHmmss(String aDdMMyyy_HHmmss) {
        ddMMyyy_HHmmss = aDdMMyyy_HHmmss;
    }

    /**
     * @return the guionddMMyyy_HHmmss
     */
    public static String getGuionddMMyyy_HHmmss() {
        return guionddMMyyy_HHmmss;
    }

    /**
     * @param aGuionddMMyyy_HHmmss the guionddMMyyy_HHmmss to set
     */
    public static void setGuionddMMyyy_HHmmss(String aGuionddMMyyy_HHmmss) {
        guionddMMyyy_HHmmss = aGuionddMMyyy_HHmmss;
    }

    /**
     * @return the ddMMyyy
     */
    public static String getDdMMyyy() {
        return ddMMyyy;
    }

    /**
     * @param aDdMMyyy the ddMMyyy to set
     */
    public static void setDdMMyyy(String aDdMMyyy) {
        ddMMyyy = aDdMMyyy;
    }

    /**
     * @return the guionddMMyyy
     */
    public static String getGuionddMMyyy() {
        return guionddMMyyy;
    }

    /**
     * @param aGuionddMMyyy the guionddMMyyy to set
     */
    public static void setGuionddMMyyy(String aGuionddMMyyy) {
        guionddMMyyy = aGuionddMMyyy;
    }
    
    public static LocalDateTime convertToDateTimeOfBD(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        
        return dateTime;
    }
    
     public static LocalDateTime convertToDateTimeOfString(String dateString, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        
        return dateTime;
    }

    /**
     * @return the ddMMYY_HHmmss12H
     */
    public static String getDdMMYY_HHmmss12H() {
        return ddMMYY_HHmmss12H;
    }
}
