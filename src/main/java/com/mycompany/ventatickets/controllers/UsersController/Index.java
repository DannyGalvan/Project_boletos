/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ventatickets.controllers.UsersController;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.Context;
import com.mycompany.ventatickets.models.DUser;
import com.mycompany.ventatickets.models.Params;
import com.mycompany.ventatickets.models.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author cgalv
 */
public class Index implements Initializable {
    
    private final DUser _users = new DUser();
    
    @FXML
    public TableColumn<User, Button> acciones;

    @FXML
    public TableColumn<User, Boolean> activo;

    @FXML
    public TableColumn<User, String> correo;
    
    @FXML
    public Button create;

    @FXML
    public Button dashboard;

    @FXML
    public TableColumn<User, String> id;

    @FXML
    public TableColumn<User, String> nombre;

    @FXML
    public TableColumn<User, String> numero;

    @FXML
    public TableColumn<User, String> registro;

    @FXML
    public TableColumn<User, String> rol;

    @FXML
    public TableView<User> tablaUsers;
    
    private ObservableList<User> usuarios;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            dashboard.setVisible(Context.isVisibleDashboard());
            dashboard.setOnMouseClicked(event -> {
                Context.redirectToDashboard();
            });
            create.setOnMouseClicked(event -> {
                App.view("Create");
            });

            ArrayList<User> listUsers = _users.ListadoDeUsuarios();
            usuarios = FXCollections.observableArrayList(listUsers);
            tablaUsers.setItems(usuarios);

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            id.setStyle("-fx-alignment: CENTER;");
            nombre.setCellValueFactory(new PropertyValueFactory<>("name"));
            correo.setCellValueFactory(new PropertyValueFactory<>("email"));
            activo.setCellValueFactory(new PropertyValueFactory<>("active"));            
            rol.setCellFactory((TableColumn<User, String> param) -> new TableCell<User, String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        // Obtener el objeto Usuario de la fila actual
                        User evento = getTableView().getItems().get(getIndex());
                        // Llamar al método de tu modelo con el parámetro deseado
                        String texto = evento.getIdRol() == 1 ? "Administrador" : "Cliente";
                        setText(texto);
                    }
                }
            });
            numero.setCellValueFactory(new PropertyValueFactory<>("number"));
            registro.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
            acciones.setCellFactory(param -> {
                return new TableCell<User, Button>() {
                    private final Button modificar = new Button("Modificar");

                    {
                        modificar.getStyleClass().add("button-success");
                        modificar.setOnAction((ActionEvent event) -> {
                            // Lógica para manejar el evento del botón
                            User evento = getTableRow().getItem();
                            // Realizar acciones con el usuario seleccionado
                            Params<User> parametro = new Params();
                            parametro.setDato(evento);
                            App.view("Edit",parametro);
                        });
                    }

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                             setGraphic(null);
                        }else{
                             setGraphic(modificar);
                        }
                    }
                };  
            });
            acciones.setStyle("-fx-alignment: CENTER;");
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
       
    }    
    
}
