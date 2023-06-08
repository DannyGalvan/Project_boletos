/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.controllers.HomeController;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.Context;
import com.mycompany.ventatickets.models.Events;
import com.mycompany.ventatickets.models.DEvents;
import com.mycompany.ventatickets.models.Params;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import com.mycompany.ventatickets.models.UniversalDateTimeFormat;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * FxController
 * @author cgalv
 */
public class Index implements Initializable {

    @FXML
    public Button btnc;
    
    @FXML
    public GridPane panelGrid;
    
    @FXML 
    public Label title;
    
    @FXML
    public Button dashboard;
    
    private final DEvents _eventos = new DEvents();
    
    /**
     * initialize method
     * 
     * @param url url
     * @param rb resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {              
           
            dashboard.setVisible(Context.isVisibleDashboard());
            dashboard.setOnMouseClicked((MouseEvent event) -> {
                Context.redirectToDashboard();
            });

            ArrayList<Events> eventList = _eventos.ListEvents();

            VBox vbox = new VBox();
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(10);

            // Establecer el ancho de cada columna al 50% de la pantalla
            vbox.setStyle("-fx-column-count: 2; -fx-column-gap: 10px; -fx-pref-width: 50%; -fx-margin-top: 200px;");

            if (Context.isIsLogIn()) {
                String titulo = String.format("Bienvenido %s", Context.getUser().getName());
                title.setText(titulo);
            }

            btnc.setOnAction(e -> {
                if (Context.isIsLogIn()) {
                    Context.LogOut();
                }else{
                    App.setRoot("Auth");
                }
            });
            btnc.setText(Context.isIsLogIn() ? "Cerrar Sesión" : "Iniciar Sesión");

            for (Events event : eventList) {
                // Crear una tarjeta para cada usuario
                // Crear una tarjeta para cada usuario
                VBox card = createCard(event);

                // Agregar la tarjeta al VBox
                vbox.getChildren().add(card);
            }

            ScrollPane scrollPane = new ScrollPane(vbox);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);

            GridPane.setMargin(scrollPane, new Insets(60, 0, 0, 0));
            panelGrid.add(scrollPane, 1, 0);
        } catch (Exception e) {
             System.err.println(e.toString());
        }
    } 
    
    private VBox createCard(Events event) {
        // Crear la tarjeta del evento
        VBox card = new VBox();
        card.setSpacing(10);       
        card.setPadding(new Insets(10));
        card.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), null))); 
        card.setStyle("-fx-border-color: #3D373D; -fx-border-width: 1px; -fx-border-radius: 10px;");

        // Agregar los elementos de la tarjeta: nombre y foto (suponiendo que tienes propiedades 'name' y 'photo' en la clase User)
        Label nameLabel = new Label(event.getName());
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        nameLabel.setTextFill(Color.WHITE);
        
        String pathResource = System.getProperty("user.dir")+event.getImage();
        ImageView photoImageView = new ImageView(new Image("file:"+pathResource));
        photoImageView.setFitWidth(200);
        photoImageView.setFitHeight(200);
        
        Label descriptionText = new Label(event.getSynopsis());
        descriptionText.setFont(Font.font("System", FontWeight.NORMAL, 12));
        descriptionText.setPadding(new Insets(5));
        descriptionText.setWrapText(true);
        descriptionText.setTextFill(Color.WHITE);
        
        HBox container = new HBox();
        container.setSpacing(10);
        
        container.getChildren().addAll(photoImageView,descriptionText);  
        
         // Crear la etiqueta de fecha
        Text dateLabel = new Text("Fecha: " + event.getInitialDate(UniversalDateTimeFormat.getDdMMyyy()));
        dateLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        dateLabel.setFill(Color.WHITE);

        // Crear la etiqueta de hora
        Text timeLabel = new Text("Hora: " + event.getInitialDate("HH:mm:ss a"));
        timeLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        timeLabel.setFill(Color.WHITE);
        
        HBox fechas = new HBox();     
        fechas.setSpacing(50);
        
        fechas.getChildren().addAll(dateLabel,timeLabel);       
        
        Button seeDetal = new Button("Ver Detalle Evento");
        seeDetal.getStyleClass().add("button-primary");
        seeDetal.setFont(Font.font("System", FontWeight.BOLD, 12));
        seeDetal.setOnMouseClicked(e -> {
            Params<Events> param = new Params<>();
            param.setDato(event);
            App.view("EventDetail", param);
        });
        VBox.setMargin(seeDetal, new Insets(10));

        // Agregar los elementos al HBox de la tarjeta
        card.setAlignment(Pos.CENTER);
        card.getChildren().addAll(nameLabel,container, fechas, seeDetal);

        return card;
    }
}
