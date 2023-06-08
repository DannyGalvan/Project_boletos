/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ventatickets.controllers.EventsController;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.Location;
import com.mycompany.ventatickets.Validations;
import com.mycompany.ventatickets.models.DDatesEvent;
import com.mycompany.ventatickets.models.DEvents;
import com.mycompany.ventatickets.models.DatesEvent;
import com.mycompany.ventatickets.models.Events;
import com.mycompany.ventatickets.models.Params;
import com.mycompany.ventatickets.models.TipoResponsable;
import com.mycompany.ventatickets.models.UniversalDateTimeFormat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author cgalv
 */
public class Dates implements Initializable {
    
    @FXML
    public DatePicker fecha;

    @FXML
    public Button guardar;

    @FXML
    public TextField hora;

    @FXML
    public TextField idfecha;

    @FXML
    public Button regresar;
    
    @FXML
    public TextField evento;
    
    @FXML
    public ComboBox<TipoResponsable> activo;

    private final DEvents _eventos = new DEvents();
    private final DDatesEvent _fechas = new DDatesEvent();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Params<DatesEvent> param = Location.getParams();
        Events _evento = _eventos.EventById(param.getDato().getIdevento());
        
        regresar.setOnMouseClicked(event -> {
           this.changeView(_evento);
        });
        
        guardar.setOnMouseClicked(event -> {
            this.saveDate(_evento);
        });
        
        idfecha.setText(String.valueOf(param.getDato().getIdfecha()));
        hora.setText(param.getDato().getFecha_evento("HH:mm"));
        fecha.setValue(param.getDato().getFecha_evento().toLocalDate()); 
        evento.setText(_evento.getName());
        activo.getItems().add(new TipoResponsable(0, "-- Selecciona una opcion --"));
        activo.getItems().add(new TipoResponsable(1, "Activo"));
        activo.getItems().add(new TipoResponsable(2, "Desactivado"));        
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
        activo.setButtonCell(activo.getCellFactory().call(null));    
        int active = param.getDato().getActive() ? 1 : 2;
        activo.setValue(activo.getItems().stream().filter(x -> x.getId() == active).toList().get(0));
    }    
    
    private void changeView(Events dato){
        Params<Events> ev = new Params<>();
        ev.setDato(dato);
        App.view("Edit", ev);
    }
    
    private void saveDate(Events dato){
        Params<DatesEvent> param = Location.getParams();
        DatesEvent date = new DatesEvent();
        String f = Validations.ValidarFecha(fecha.getValue(), "Seleccione una fecha valida");
        if (f.isEmpty()) {
            return;
        }
        String h = Validations.ValidarHora(hora.getText(), "Ingrese una hora valida en formato HH:mm");
        if (h.isEmpty()) {
            return;
        }
        int active = Validations.ValidarCampo(activo.getSelectionModel().getSelectedItem().getId(), "Seleccione si es activo");
        if (active == 0) {
            return;
        }
        date.setActive(activo.getSelectionModel().getSelectedItem().getId() == 1);
        date.setFecha_evento(UniversalDateTimeFormat.convertToDateTimeOfString(String.format("%s %s", f, h), "dd/MM/yyyy HH:mm"));
        date.setDuration(param.getDato().getDuration());
        date.setIdevento(param.getDato().getIdevento());
        date.setIdfecha(param.getDato().getIdfecha());
        
        if (_fechas.ModifyDateEvent(date)) {
            Validations.AlertMessage("Fecha modificada con exito", Alert.AlertType.INFORMATION, "Operacion Exitosa!");
            this.changeView(dato);
        }else{
            Validations.AlertMessage("Error al modificar fecha", Alert.AlertType.ERROR, "Error en operacion!");
        }        
    }
    
}
