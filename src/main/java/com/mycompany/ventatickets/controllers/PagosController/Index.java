/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ventatickets.controllers.PagosController;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.Context;
import com.mycompany.ventatickets.Validations;
import com.mycompany.ventatickets.models.Asientos;
import com.mycompany.ventatickets.models.AsientosEventoBoletos;
import com.mycompany.ventatickets.models.Events;
import com.mycompany.ventatickets.models.Params;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author cgalv
 */
public class Index implements Initializable {
    
    @FXML
    public Label timerLabel;    
    @FXML
    public VBox container;
    @FXML
    public Button cancel, asientos, procesar, todos, tickets;


    private Timeline timeline;
    private boolean todosTicket = false;
    private boolean seatSelection = false;
    private  int DURATION_IN_SECONDS = 600;   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        todos.setOnMouseClicked(event -> {
            if (!todosTicket) {
                this.addDataForTodos();
                todosTicket = true;
            }else{
                container.getChildren().clear();
                this.addDataForTodos();
                todosTicket = true;
            }   
            seatSelection = false;
        });
        
        tickets.setOnMouseClicked(event -> {
            if (!todosTicket) {
                this.addDataForSeat();
                todosTicket = true;
            }else{
                container.getChildren().clear();
                this.addDataForSeat();
                todosTicket = true;
            }
            seatSelection = true;
        });
        
        procesar.setOnMouseClicked((var event) -> {
            if (seatSelection) {
                this.capturarDatosMultiples();
            }else{
                this.capturarDatos();
            }
            timeline.stop();
        });
        
        asientos.setOnMouseClicked(event -> {
            App.setRoot("Home", "Asientos");
        });
        
        cancel.setOnMouseClicked(event -> {
            Params<Events> param = new Params<>();
            param.setDato(Context.getEvent());
            App.setRoot("Home", "EventDetail", param);
        });
        
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
        
        timerLabel.setText(minutesString + ":" + secondsString);
    }  
     
    private VBox createDataForSeat(){
        VBox box = new VBox();
        for (int i = 0; i < Context.getAsientosPago().size(); i++) {
            box.getChildren().add(this.createDataTodos(i,Context.getAsientosPago().get(i)));
        }
        return box;
    }
    
    private VBox createDataTodos(){
        VBox box = new VBox();
        box.setPadding(new Insets(30));
        
        Label nameLabel = new Label("Nombres");
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        TextField nameField = new TextField();
        nameField.setId("name");
        
        Label lastNameLabel = new Label("Apellidos");
        lastNameLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        TextField lastNameField = new TextField();
        lastNameField.setId("lastName");
        
        Label emailLabel = new Label("Correo");
        emailLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        TextField emailField = new TextField();
        emailField.setId("email");
        
        Label confirmEmailLabel = new Label("Confirmar Correo");
        confirmEmailLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        TextField confirmEmailField = new TextField();   
        confirmEmailField.setId("confirmEmail");
           
        Label numberLabel = new Label("Numero de Telefono");
        numberLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        TextField numberField = new TextField();
        numberField.setId("number");
        
        if (!Context.getDatosBoleto().isEmpty()) {
            AsientosEventoBoletos dato = Context.getDatosBoleto().get(0);
            nameField.setText(dato.getName());
            lastNameField.setText(dato.getLastName());
            emailField.setText(dato.getEmail());
            confirmEmailField.setText(dato.getEmail());
            numberField.setText(dato.getNumber());
        }
        
        box.getChildren().addAll(nameLabel,nameField,lastNameLabel,lastNameField,emailLabel,emailField,confirmEmailLabel,confirmEmailField,numberLabel,numberField);
        
        return box;
    }
    
    private VBox createDataTodos(int index, Asientos asiento){
        VBox box = new VBox();
        box.setPadding(new Insets(30));
        
        Label title = new Label("Asiento: " + asiento.getFile()+asiento.getColumn()+asiento.getLado());
        title.setFont(Font.font("System", FontWeight.BOLD, 14));
        VBox.setMargin(title, new Insets(0,0,20,0));
        
        Label nameLabel = new Label("Nombres");
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        TextField nameField = new TextField();
        nameField.setId(String.format("name%s",index));
        
        Label lastNameLabel = new Label("Apellidos");
        lastNameLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        TextField lastNameField = new TextField();
        lastNameField.setId(String.format("lastname%s",index));
        
        Label emailLabel = new Label("Correo");
        emailLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        TextField emailField = new TextField();
        emailField.setId(String.format("email%s",index));
        
        Label confirmEmailLabel = new Label("Confirmar Correo");
        confirmEmailLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        TextField confirmEmailField = new TextField();
        confirmEmailField.setId(String.format("confirmemail%s",index));
           
        Label numberLabel = new Label("Numero de Telefono");
        numberLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        TextField numberField = new TextField();
        numberField.setId(String.format("number%s",index));
        
         if (Context.getDatosBoleto().size() == Context.getAsientosPago().size()) {
            AsientosEventoBoletos dato = Context.getDatosBoleto().get(index);
            nameField.setText(dato.getName());
            lastNameField.setText(dato.getLastName());
            emailField.setText(dato.getEmail());
            confirmEmailField.setText(dato.getEmail());
            numberField.setText(dato.getNumber());
        }
        
        box.getChildren().addAll(title,nameLabel,nameField,lastNameLabel,lastNameField,emailLabel,emailField,confirmEmailLabel,confirmEmailField,numberLabel,numberField);
        
        return box;
    }
    
    private void addDataForTodos(){
        VBox children = this.createDataTodos();
        ScrollPane scrollPane = new ScrollPane(children);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        container.getChildren().add(scrollPane);
    }
    
    private void addDataForSeat(){
        VBox children = this.createDataForSeat();
        ScrollPane scrollPane = new ScrollPane(children);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        container.getChildren().add(scrollPane);
    }
    
    private void capturarDatosMultiples(){
        ArrayList<AsientosEventoBoletos> boletos = new ArrayList<>();
        
        for (int i = 0; i < Context.getAsientosPago().size(); i++) {
             TextField nameField = (TextField) container.lookup("#"+String.format("name%s",i));
             System.out.println("#"+String.format("name%s",i));
             Asientos actual = Context.getAsientosPago().get(i);
             String nombre = actual.getFile()+actual.getColumn()+actual.getLado();
             String name = Validations.ValidarCampo(nameField.getText(), String.format("El Campo nombre del boleto %s no puede ser vacio", nombre));
             if (name.isEmpty()) {
                return;
             }
             TextField lastNameField = (TextField) container.lookup("#"+String.format("lastname%s",i));
             String lastName = Validations.ValidarCampo(lastNameField.getText(), String.format("El Campo apellido del boleto %s no puede ser vacio", nombre));
             if (lastName.isEmpty()) {
                return;
             }
             TextField emailField = (TextField) container.lookup("#"+String.format("email%s",i));
             String email = Validations.ValidarCorreo(emailField.getText(), String.format("El Campo Email del boleto %s es invalido", nombre));
             if (email.isEmpty()) {
                return;
             }
             TextField confirmEmailField = (TextField) container.lookup("#"+String.format("confirmemail%s",i));
             String confirmEmail = Validations.ValidarCorreo(confirmEmailField.getText(), String.format("El Campo Confirmar Correo del boleto %s es invalido", nombre));
             if (confirmEmail.isEmpty()) {
                return;
             }
             if (!email.equals(confirmEmail)) {
                Validations.AlertMessage(String.format("Correo y Confirmacion de Correo No Coinciden en el boleto %s", nombre), Alert.AlertType.ERROR, "Datos Invalidos");
                return;
             }
             TextField numberField = (TextField) container.lookup("#"+String.format("number%s",i));
             String number = Validations.ValidarCampo(numberField.getText(), String.format("El Campo Numero Correo del boleto %s no puede ser vacio", nombre));
             if (number.isEmpty()) {
                return;
             }
             
             AsientosEventoBoletos boleto = new AsientosEventoBoletos();
             boleto.setIdasiento(actual.getId());
             boleto.setIdfecha(Context.getDate().getIdfecha());
             boleto.setPrecio(this.precioAsiento(actual.getIdSection()));
             boleto.setCodigoAsiento(nombre);
             boleto.setName(name);
             boleto.setLastName(lastName);
             boleto.setEmail(email);
             boleto.setNumber(number);
             
             boletos.add(boleto);
        }
        
        timeline.stop();
        Context.setDatosBoleto(boletos);   
        App.view("Create");
    }
    
    private void capturarDatos(){
        ArrayList<AsientosEventoBoletos> boletos = new ArrayList<>();

        TextField nameField = (TextField) container.lookup("#name");
        String name = Validations.ValidarCampo(nameField.getText(), "El Campo nombre no puede ser vacio");
        if (name.isEmpty()) {
           return;
        }
        TextField lastNameField = (TextField) container.lookup("#lastName");
        String lastName = Validations.ValidarCampo(lastNameField.getText(), "El Campo apellido no puede ser vacio");
        if (lastName.isEmpty()) {
           return;
        }
        TextField emailField = (TextField) container.lookup("#email");
        String email = Validations.ValidarCorreo(emailField.getText(),"El Campo Correo es invalido");
        if (email.isEmpty()) {
           return;
        }
        TextField confirmEmailField = (TextField) container.lookup("#confirmEmail");
        String confirmEmail = Validations.ValidarCorreo(confirmEmailField.getText(), "El Campo Confirmar Correo es invalido");
        if (confirmEmail.isEmpty()) {
           return;
        }
        if (!email.equals(confirmEmail)) {
           Validations.AlertMessage("Correo y Confirmacion de Correo No Coinciden", Alert.AlertType.ERROR, "Datos Invalidos");
           return;
        }
        TextField numberField = (TextField) container.lookup("#number");
        String number = Validations.ValidarCampo(numberField.getText(), "El Campo Numero no puede ser vacio");
        if (number.isEmpty()) {
           return;
        }

        for (Asientos model : Context.getAsientosPago()) {
            String nombre = model.getFile()+model.getColumn()+model.getLado();
            AsientosEventoBoletos boleto = new AsientosEventoBoletos();
            boleto.setIdasiento(model.getId());
            boleto.setIdfecha(Context.getDate().getIdfecha());
            boleto.setPrecio(this.precioAsiento(model.getIdSection()));
            boleto.setCodigoAsiento(nombre);
            boleto.setName(name);
            boleto.setLastName(lastName);
            boleto.setEmail(email);
            boleto.setNumber(number);

            boletos.add(boleto);
        }
        
        timeline.stop();
        Context.setDatosBoleto(boletos);    
        App.view("Create");
    }
    
    private double precioAsiento(int idSeccion){
        double precio = 0.00;
        
        precio = switch (idSeccion) {
           case 1 -> Context.getEvent().getVip_mg();
           case 2 -> Context.getEvent().getVip();
           case 3 -> Context.getEvent().getPlanta_a();
           case 4 -> Context.getEvent().getPlanta_b();
           default -> 0.00;
       };
        
        return precio;
    }
    
}
