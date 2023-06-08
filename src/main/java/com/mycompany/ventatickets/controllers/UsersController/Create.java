/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ventatickets.controllers.UsersController;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.Validations;
import com.mycompany.ventatickets.models.DUser;
import com.mycompany.ventatickets.models.TipoResponsable;
import com.mycompany.ventatickets.models.User;
import java.net.URL;
import java.util.ResourceBundle;
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
    
    @FXML
    public ComboBox<TipoResponsable> activo;

    @FXML
    public TextField clave;

    @FXML
    public TextField correo;

    @FXML
    public TextField nombre;

    @FXML
    public TextField numero;

    @FXML
    public Button regresar;
    
    @FXML
    public Button crear;

    @FXML
    public ComboBox<TipoResponsable> rol;
    
    private DUser _usuarios = new DUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        regresar.setOnMouseClicked(event -> {
            App.view("Index");
        });        
        
        crear.setOnMouseClicked(event -> {
            this.saveUser();
        });
        
        rol.getItems().add(new TipoResponsable(0, "-- Selecciona un tipo --"));
        rol.getItems().add(new TipoResponsable(1, "Administrador"));
        rol.getItems().add(new TipoResponsable(2, "Cliente"));
        
         rol.setCellFactory((ListView<TipoResponsable> listView) -> new ListCell<TipoResponsable>() {
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
         
         rol.setConverter(new StringConverter<TipoResponsable>() {
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
            
        rol.setButtonCell(rol.getCellFactory().call(null));        
        rol.setValue(new TipoResponsable(0, "-- Selecciona un tipo --"));
        
        activo.getItems().add(new TipoResponsable(0, "-- Selecciona una opcion --"));
        activo.getItems().add(new TipoResponsable(1, "Si"));
        activo.getItems().add(new TipoResponsable(2, "No"));
        
        activo.setCellFactory((ListView<TipoResponsable> listView) -> new ListCell<TipoResponsable>() {
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
         
        activo.setConverter(new StringConverter<TipoResponsable>() {
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
        activo.setButtonCell(rol.getCellFactory().call(null));        
        activo.setValue(new TipoResponsable(0, "-- Selecciona un tipo --"));
    }    
    
    private void saveUser(){
        User model = new User();
        String name = Validations.ValidarCampo(nombre.getText(), "El Nombre no puede ser vacio");
        if (name.isEmpty()) {
            return;
        }
        model.setName(name);
        String email = Validations.ValidarCorreo(correo.getText(), "El Correo es invalido");
        if (email.isEmpty()) {
            return;
        }
        model.setEmail(email);
        String password = Validations.ValidarCampo(clave.getText(), "La Clave no puede ser vacia");
        if (password.isEmpty()) {
            return;
        }
        model.setPassword(password);
        String number = Validations.ValidarCampo(numero.getText(), "El Numero no puede ser vacio");
        if (number.isEmpty()) {
            return;
        }
        model.setNumber(number);
        int active = Validations.ValidarCampo(activo.getSelectionModel().getSelectedItem().getId(), "Seleccione si es activo");
        if (active == 0) {
            return;
        }
        model.setActive(activo.getSelectionModel().getSelectedItem().getId() == 1);
        int idrol = Validations.ValidarCampo(rol.getSelectionModel().getSelectedItem().getId(), "Seleccione un rol");
        if (idrol == 0) {
            return;
        }
        model.setIdRol(idrol);
        
        if (!_usuarios.CreateUser(model)) {
            Validations.AlertMessage("Error al Crear Usuario", Alert.AlertType.ERROR, "Upss Hubo Un Error!");
            return;
        }
        
        Validations.AlertMessage("Usuario Creado Con Exito!", Alert.AlertType.INFORMATION, "Operacion Exitosa!");
        App.view("Index");        
    }
    
}
