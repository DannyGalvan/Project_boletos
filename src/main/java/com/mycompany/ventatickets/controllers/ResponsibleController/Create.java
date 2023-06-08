/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ventatickets.controllers.ResponsibleController;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.Validations;
import com.mycompany.ventatickets.models.DResponsible;
import com.mycompany.ventatickets.models.Responsible;
import com.mycompany.ventatickets.models.TipoResponsable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author cgalv
 */
public class Create implements Initializable {
    
    /**
     *Boton para crear responsables
     */
    @FXML
    public Button crear;

    /**
     *input que recibe el nombre del responsable
     */
    @FXML
    public TextField nombre;
    
    /**
     *boton para regresar a la lista
     */
    @FXML
    public Button regresar;

    /**
     *Combo box para asignar tipo de responsable
     */
    @FXML
    public ComboBox<TipoResponsable> tipos;
    
    private final DResponsible _responsible = new DResponsible();

    /**
     * Initializes the controller class.
     * 
     * @param url parametro url
     * @param rb parametro resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        tipos.getItems().add(new TipoResponsable(0, "-- Selecciona un tipo --"));
        tipos.getItems().add(new TipoResponsable(1, "Persona"));
        tipos.getItems().add(new TipoResponsable(2, "Empresa"));      
        tipos.setPrefWidth(Double.MAX_VALUE);
        
         tipos.setCellFactory((ListView<TipoResponsable> listView) -> new ListCell<TipoResponsable>() {
             @Override
             protected void updateItem(TipoResponsable option, boolean empty) {
                 super.updateItem(option, empty);
                 
                 if (option != null) {
                     setText(option.getNombre());
                 } else {
                     setText(null);
                 }
             }
         });
         
         tipos.setConverter(new StringConverter<TipoResponsable>() {
            @Override
            public String toString(TipoResponsable option) {
                if (option != null) {
                    return option.getNombre();
                } else {
                    return null;
                }
            }

            @Override
            public TipoResponsable fromString(String string) {
                // No se necesita implementación para la conversión inversa
                return null;
            }
        });
            
        tipos.setButtonCell(tipos.getCellFactory().call(null));        
        tipos.setValue(new TipoResponsable(0, "-- Selecciona un tipo --"));
        
        regresar.setOnAction((ActionEvent event) -> {
            App.view("Index");
        });
        
        crear.setOnAction((ActionEvent event) -> {
            TipoResponsable tipo = tipos.getSelectionModel().getSelectedItem();
            Responsible responsible = new Responsible();
            String name = Validations.ValidarCampo(nombre.getText(),"Campo nombre es obligatorio");
            if (name.isBlank()) {
                return;
            }
            responsible.setName(name);
            int id = Validations.ValidarCampo(tipo.getId(),"Debes seleccionar un tipo valido");
            if (id == 0) {
                return;
            }           
            responsible.setType(id);
            
            if ( _responsible.CreateResponsible(responsible)) {
                Validations.AlertMessage("Responsable Creado Con Exito", Alert.AlertType.INFORMATION, "Operacion Exitosa!");
                App.view("Index");
            }else{
               Validations.AlertMessage("Upss! Ha Ocurrido Un Error", Alert.AlertType.ERROR, "Error");
            }
        });
    }    
}
