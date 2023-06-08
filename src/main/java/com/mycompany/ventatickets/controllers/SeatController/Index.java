/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ventatickets.controllers.SeatController;

import com.mycompany.ventatickets.Context;
import com.mycompany.ventatickets.models.Asientos;
import com.mycompany.ventatickets.models.DAsientos;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author cgalv
 */
public class Index implements Initializable {
    
    @FXML
    public TableColumn<Asientos, ToggleButton> activo;

    @FXML
    public TableColumn<Asientos, Integer> columna;

    @FXML
    public Button dashboard;

    @FXML
    public TableColumn<Asientos, String> fila;

    @FXML
    public TableColumn<Asientos, Integer> id;

    @FXML
    public TableColumn<Asientos, String> lado;

    @FXML
    public TableColumn<Asientos, String> seccion;

    @FXML
    public TableView<Asientos> tablaAsientos;
    
    private final DAsientos _asientos = new DAsientos();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dashboard.setVisible(Context.isVisibleDashboard());
        dashboard.setOnMouseClicked(event -> {
            Context.redirectToDashboard();
        });
        
        ArrayList<Asientos> lista = _asientos.ListAsientos();
        ObservableList<Asientos> asientos = FXCollections.observableArrayList(lista);
        tablaAsientos.setItems(asientos);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setStyle("-fx-alignment: CENTER;");
        lado.setCellValueFactory(new PropertyValueFactory<>("lado"));
        lado.setStyle("-fx-alignment: CENTER;");
        fila.setCellValueFactory(new PropertyValueFactory<>("file"));
        fila.setStyle("-fx-alignment: CENTER;");
        columna.setCellValueFactory(new PropertyValueFactory<>("column"));
        columna.setStyle("-fx-alignment: CENTER;");
        seccion.setCellFactory((TableColumn<Asientos, String> param) -> {
            return new TableCell<Asientos, String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        // Obtener el objeto Usuario de la fila actual
                        Asientos asiento = getTableView().getItems().get(getIndex());
                        // Llamar al método de tu modelo con el parámetro deseado
                        String texto = asiento.getSection().getDescription();
                        setText(texto);
                    }
                }
            };
        });       
        activo.setCellFactory(param -> {
            return new TableCell<Asientos, ToggleButton>() {
                private final ToggleButton modificar = new ToggleButton();
                {
                    modificar.getStyleClass().add("toggle-button");
                    modificar.setOnAction(event -> {
                        // Lógica para manejar el evento del botón
                        Asientos asiento = getTableRow().getItem();
                        // Realizar acciones con el usuario seleccionado
                        boolean ac = !asiento.isActive();
                        asiento.setActive(ac);
                        _asientos.ModifyAsiento(asiento);
                    });          
                }

                @Override
                protected void updateItem(ToggleButton item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                         setGraphic(null);
                    }else{
                         Asientos asiento = getTableRow().getItem();
                         modificar.setSelected(asiento.isActive());
                         setGraphic(modificar);
                    }
                }
            };  
        });
        activo.setStyle("-fx-alignment: CENTER;");
    }    
    
}
