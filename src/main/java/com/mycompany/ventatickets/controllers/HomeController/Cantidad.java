/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ventatickets.controllers.HomeController;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.Context;
import com.mycompany.ventatickets.Validations;
import com.mycompany.ventatickets.models.Events;
import com.mycompany.ventatickets.models.Params;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author cgalv
 */
public class Cantidad implements Initializable {

    @FXML
    public ComboBox<Integer> cantidad;

    @FXML
    public Button regresar;
    
    @FXML
    public Button continuar;


    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        for (int i = 0; i <= 10; i++) {
            cantidad.getItems().add(i);
        }        
        cantidad.setValue(0);
        
        continuar.setOnMouseClicked(event -> {
            int value = cantidad.getValue();
            if (value > 0) {
                Params<Integer> cant = new Params<>();
                cant.setDato(value);
                App.view("Asientos", cant);
            }else{
                Validations.AlertMessage("Debes Seleccionar una cantidad de boletos antes de continuar.", Alert.AlertType.ERROR, "Error");
            }
        });
        
        regresar.setOnMouseClicked(event -> {
            Params<Events> evento = new Params<>();
            evento.setDato(Context.getEvent());
            App.view("EventDetail", evento);
        });
    }    
    
}
