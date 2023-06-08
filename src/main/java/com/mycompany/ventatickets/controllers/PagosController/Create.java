/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ventatickets.controllers.PagosController;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.Context;
import com.mycompany.ventatickets.Util.Email;
import com.mycompany.ventatickets.Validations;
import com.mycompany.ventatickets.models.AsientosEventoBoletos;
import com.mycompany.ventatickets.models.DAsientosEventoBoletos;
import com.mycompany.ventatickets.models.DPagosBoleto;
import com.mycompany.ventatickets.models.Events;
import com.mycompany.ventatickets.models.PagosBoleto;
import com.mycompany.ventatickets.models.Params;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author cgalv
 */
public class Create implements Initializable {
    
    @FXML
    public Button cancelar;
    @FXML
    public TextField cvv;
    @FXML
    public ComboBox<String> mes;
    @FXML
    public TextField nombre;
    @FXML
    public TextField numero;
    @FXML
    public Button procesar;
    @FXML
    public Label timer;
    @FXML
    public ComboBox<Integer> year;
    @FXML
    public TextField postal;
    @FXML
    public TextField direccion;
    @FXML
    public Label progress;

    
    private Timeline timeline;
    private  int DURATION_IN_SECONDS = 300;
    private final DAsientosEventoBoletos _boletos = new DAsientosEventoBoletos();
    private final DPagosBoleto _pago = new DPagosBoleto();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cancelar.setOnMouseClicked(event -> {
            Params<Events> param = new Params<>();
            param.setDato(Context.getEvent());
            App.setRoot("Home", "EventDetail", param);
        });
        
        procesar.setOnMouseClicked(event -> {    
            progress.setVisible(true);
            Validations.AlertMessage("Espera mientras se procesa su pago",Alert.AlertType.INFORMATION, "Informacion");
            this.process();     
            progress.setVisible(false);
        });
        
        for (int i = 1; i <= 12; i++) {
            DecimalFormat formato = new DecimalFormat("00");
            String resultado = formato.format(i);
            mes.getItems().add(resultado);
        }
        mes.setValue("00");
        
        LocalDate currentDate = LocalDate.now();
        int CurrentYear = currentDate.getYear();
        
        for (int i = CurrentYear; i < CurrentYear+8; i++) {
            year.getItems().add(i);
        }
        year.setValue(0000);
        
        
        
        this.startTimer();        
    }    
    
     private void startTimer() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            DURATION_IN_SECONDS--;
            updateTimerLabel();
            
            if (DURATION_IN_SECONDS <= 0) {
                timeline.stop();
                Params<Events> param = new Params<>();
                param.setDato(Context.getEvent());
                 // Aquí puedes agregar la lógica que deseas ejecutar después de que el temporizador llegue a cero.
                Validations.AlertMessageNotWait("El Tiempo De Ingreso De Datos Se Ha Terminado", Alert.AlertType.WARNING, "Timeout"); 
                App.view("EventDetail", param);               
            }
        });
        
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
     
     private void updateTimerLabel() {
        int minutes = DURATION_IN_SECONDS / 60;
        int seconds = DURATION_IN_SECONDS % 60;
        
        String minutesString = String.format("%02d", minutes);
        String secondsString = String.format("%02d", seconds);
        
        timer.setText(minutesString + ":" + secondsString);
    }  
     
     
    private void process(){
        
         PagosBoleto pago = new PagosBoleto();
         String name = Validations.ValidarCampo(nombre.getText(), "El campo nombre no puede ser vacio");
         if (name.isEmpty()) {
            return;
         }
         String number = Validations.validarTarjetaCredito(numero.getText(), "Ingresa un numero de tarjeta valido");
         if (number.isEmpty()) {
            return;
         }
         String code = Validations.ValidarCampo(postal.getText(), "El campo codigo postal no puede ser vacio");
         if (code.isEmpty()) {
            return;
         }
         String address = Validations.ValidarCampo(direccion.getText(), "El campo direccion no puede ser vacio");
         if (address.isEmpty()) {
            return;
         }
         int month = Validations.ValidarCampo(Integer.valueOf(mes.getValue()), "Debes seleccionar un mes valido");
         if (month == 0) {
            return;
         }
         int anio = Validations.ValidarCampo(year.getValue(), "Debes seleccionar un año valido");
         if (anio == 0) {
            return;
         }
         int CVV = Validations.validarCVV(cvv.getText(), "Ingresa un cvv valido");
         if (CVV == 0) {
            return;
         }
         pago.setAdress(address);
         pago.setCode(code);
         pago.setDate(LocalDateTime.now());
         pago.setIdCliente(3);
         pago.setName(name);
         pago.setNumber(number);
         pago.setQuantity(Context.getDatosBoleto().size());
         pago.setTotal(Context.getTotal());
         
         if (_pago.CreatePago(pago)) {
             for (AsientosEventoBoletos asientosEventoBoletos : Context.getDatosBoleto()) {
                 if (!_boletos.CreateTicket(asientosEventoBoletos)) {
                     Validations.AlertMessage("Error al comprar Ticket!",Alert.AlertType.ERROR, "Error");
                     return;
                 }
                 Email.sendHTMLEmail("Ticket Comprado!", Context.getEvent(), Context.getDate(), asientosEventoBoletos);
             }
            Validations.AlertMessage("Compra realizada con exito",Alert.AlertType.INFORMATION, "Operacion Exitosa");
            App.setRoot("Home");
         }else{
             Validations.AlertMessage("Error al realizar cobro", Alert.AlertType.ERROR, "Error en transaccion!");
         }
          
    }
    
}
