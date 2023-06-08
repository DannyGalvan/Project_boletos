/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ventatickets.controllers.EventsController;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.Context;
import com.mycompany.ventatickets.models.DEvents;
import com.mycompany.ventatickets.models.Events;
import com.mycompany.ventatickets.models.Params;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
   
    @FXML
    public TableView<Events> tablaEventos;
    @FXML
    public TableColumn<Events,Integer> id;
    @FXML
    public TableColumn<Events, String> nombre;
    @FXML
    public TableColumn<Events,String> sinopsis;
    @FXML
    public TableColumn<Events, String> fecha;
    @FXML
    public TableColumn<Events, Button> acciones;
    @FXML
    public TableColumn<Events, String> responsable;
    @FXML
    public Button dashboard;
    
    private ObservableList<Events> eventos;
    
    private final DEvents _events = new DEvents();
    
    /**
    * Initializes the controller class.
     * @param url es la url
     * @param rb es el resourceBundle
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dashboard.setVisible(Context.isVisibleDashboard());
        dashboard.setOnMouseClicked(event -> {
            Context.redirectToDashboard();
        });
        
        ArrayList<Events> lista = _events.ListEvents();
        eventos = FXCollections.observableArrayList(lista);
        tablaEventos.setItems(eventos);
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setStyle("-fx-alignment: CENTER;");
        nombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        sinopsis.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
        responsable.setCellFactory((TableColumn<Events, String> param) -> {
            return new TableCell<Events, String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        // Obtener el objeto Usuario de la fila actual
                        Events evento = getTableView().getItems().get(getIndex());
                        // Llamar al método de tu modelo con el parámetro deseado
                        String texto = evento.getResponsible().getName();
                        setText(texto);
                    }
                }
            };
        });        
        fecha.setCellFactory((TableColumn<Events, String> param) -> new TableCell<Events, String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    // Obtener el objeto Usuario de la fila actual
                    Events evento = getTableView().getItems().get(getIndex());
                    // Llamar al método de tu modelo con el parámetro deseado
                    String texto = evento.getInitialDate("dd/MM/yyy");
                    setText(texto);
                }
            }
        });        
        fecha.setStyle("-fx-alignment: CENTER;");
        acciones.setCellFactory(param -> {
            return new TableCell<Events, Button>() {
                private final Button modificar = new Button("Modificar");

                {
                    modificar.getStyleClass().add("button-success");
                    modificar.setOnAction(event -> {
                        // Lógica para manejar el evento del botón
                        Events evento = getTableRow().getItem();
                        // Realizar acciones con el usuario seleccionado
                        Params<Events> parametro = new Params();
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
    }    
    
    /**
     * metodo para cambiar a la vista create
     * 
     */
    public void irACreate(){
        App.view("Create");
    }
    
}
