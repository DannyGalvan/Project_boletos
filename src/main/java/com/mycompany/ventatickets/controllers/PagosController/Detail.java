/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ventatickets.controllers.PagosController;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.Context;
import com.mycompany.ventatickets.Util.Html;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author cgalv
 */
public class Detail implements Initializable {
    
    @FXML
    public Button regresar;

    @FXML
    public WebView web;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            regresar.setOnMouseClicked(event -> {
                App.setRoot("Home");
            });
            WebEngine webEngine = web.getEngine();
            String path = Html.generarteHTMl(Context.getEvent(), Context.getDate(), Context.getDatosBoleto(), Context.getAsientosPago(), Context.getPago());
            // Carga el archivo HTML local
            webEngine.load(path);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    
}
