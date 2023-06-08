/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ventatickets.controllers.ResponsibleController;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.Context;
import com.mycompany.ventatickets.models.DResponsible;
import com.mycompany.ventatickets.models.Params;
import com.mycompany.ventatickets.models.Responsible;
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
    public TableView<Responsible> tablaResponsables;
    @FXML
    public Button dashboard;
    @FXML
    public TableColumn<Responsible,Integer> id;
    @FXML
    public TableColumn<Responsible, String> nombre;
    @FXML
    public TableColumn<Responsible,String> tipo;
    @FXML
    public TableColumn<Responsible, Button> acciones;
    
    private ObservableList<Responsible> responsibles;
    
    private final DResponsible _responsibles = new DResponsible();

    /**
     * Initializes the controller class.
     * @param url parametro url
     * @param rb parametro resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dashboard.setVisible(Context.isVisibleDashboard());
        dashboard.setOnMouseClicked(event -> Context.redirectToDashboard());
        
        ArrayList<Responsible> lista = _responsibles.ListResponsible();
        responsibles = FXCollections.observableArrayList(lista);
        tablaResponsables.setItems(responsibles);
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setStyle("-fx-alignment: CENTER;");
        
        nombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        tipo.setCellFactory((TableColumn<Responsible, String> param) -> {
            return new TableCell<Responsible, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                     if (empty) {
                        setText(null);
                    } else {
                        // Obtener el objeto responsable de la fila actual
                        Responsible responsable = getTableView().getItems().get(getIndex());
                        // Llamar al método de tu modelo con el parámetro deseado
                        String texto = responsable.getType() == 1 ? "Persona" : "Empresa";
                        setText(texto);
                    }
                }
            };
        });
       
        acciones.setCellFactory(param -> {
            return new TableCell<Responsible, Button>() {
                private final Button modificar = new Button("Modificar");

                {
                    modificar.getStyleClass().add("button-success");
                    modificar.setOnAction(event -> {
                        // Lógica para manejar el evento del botón
                        Responsible responsible = getTableRow().getItem();
                        // Realizar acciones con el usuario seleccionado
                        Params<Responsible> parametro = new Params();
                        parametro.setDato(responsible);
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
    
    public void irACreate(){
        App.view("Create");
    }
    
}
