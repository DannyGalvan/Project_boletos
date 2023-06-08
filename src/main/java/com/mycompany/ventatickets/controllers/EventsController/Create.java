/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.ventatickets.controllers.EventsController;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.Validations;
import com.mycompany.ventatickets.models.DEvents;
import com.mycompany.ventatickets.models.DResponsible;
import com.mycompany.ventatickets.models.Events;
import com.mycompany.ventatickets.models.Responsible;
import com.mycompany.ventatickets.models.UniversalDateTimeFormat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author cgalv
 */
public class Create implements Initializable {
    
    /**
     * boton para cargar la imagen
     */
    @FXML
    public Button uploadImage;
    
    /**
     * boton para regresar a la tabla
     */
    @FXML
    public Button regresar;

    /**
     * boton para guardar evento
     */
    @FXML
    public Button save;

    /**
     * input tipo Imagen para la visualizar imagen
     */
    @FXML
    public ImageView imageView; 

    /**
     * input tipo texto para el nombre
     */
    @FXML
    public TextField nombre;

    /**
     * input tipo text area para la synopsis
     */
    @FXML
    public TextArea synopsis;

    /**
     * input tipo fecha para la fecha inicio
     */
    @FXML
    public DatePicker fechaInicio;

    /**
     * input tipo texto para la hora inicio
     */
    @FXML
    public TextField horaInicio;

    /**
     * input tipo fecha para la fecha final
     */
    @FXML
    public DatePicker fechaFin;

    /**
     * input texto para la hora fin
     */
    @FXML
    public TextField horaFin;

    /**
     *input Fecha para la fecha visible
     */
    @FXML
    public DatePicker fechaVisible;

    /**
     *input texto para la hora visible
     */
    @FXML
    public TextField horaVisible;

    /**
     * input texto para la fecha oculto
     */
    @FXML
    public DatePicker fechaOculto;

    /**
     * input texto para la hora oculto
     */
    @FXML
    public TextField horaOculto;
    
    @FXML
    public ComboBox<Responsible> responsable;
    
    @FXML
    public TextField planta_a;

    @FXML
    public TextField planta_b;
    
    @FXML
    public TextField vip;

    @FXML
    public TextField vip_m;
    
    @FXML
    public TextField duracion;



    
    private String rutaImagen = "";
    private final DEvents _evento = new DEvents();
    private final DResponsible _responsable = new DResponsible();
    
    /**
     * Initializes the controller class.
     * @param url url del controlador
     * @param rb resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Responsible> list = _responsable.ListResponsible();
        Responsible res = new Responsible();
        res.setName("-- seleccione un responsable --");
        res.setId(0);
        responsable.getItems().add(res);
        for (Responsible responsible : list) {
            responsable.getItems().add(responsible);
        }
        
        responsable.setCellFactory((ListView<Responsible> listView) -> new ListCell<Responsible>() {
             @Override
             protected void updateItem(Responsible option, boolean empty) {
                 super.updateItem(option, empty);
                 
                 if (option != null) {
                     setText(option.getName());
                 } else {
                     setText(null);
                 }
             }
         });
         
         responsable.setConverter(new StringConverter<Responsible>() {
            @Override
            public String toString(Responsible option) {
                if (option != null) {
                    return option.getName();
                } else {
                    return null;
                }
            }

            @Override
            public Responsible fromString(String string) {
                // No se necesita implementación para la conversión inversa
                return null;
            }
        });
            
        responsable.setButtonCell(responsable.getCellFactory().call(null));
        responsable.setValue(res);
        
        
        uploadImage.setOnMouseClicked(e -> {
             FileChooser fileChooser = new FileChooser();
             fileChooser.setTitle("Buscar Imagen");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );

            // Obtener la imagen seleccionada
            File imgFile = fileChooser.showOpenDialog(null);
            
            // Mostar la imagen
            if (imgFile != null) {
                //guardamos la ruta de la imagen en una variable
                rutaImagen = imgFile.getAbsolutePath();

                Image image = new Image("file:" + imgFile.getAbsolutePath());
                imageView.setImage(image);                
            }      
        });
        
        save.setOnMouseClicked(e -> {
            saveEvent();
        });
        
        regresar.setOnMouseClicked(event -> {
            App.view("Index");
        });
    } 
    
    private String saveImage(String path, String nombre){
        String dirImage = System.getProperty("user.dir")+"/src/main/resources/images";
        String relativePath = "/src/main/resources/images/"+nombre.replace(" ","_")+".jpg";
        String pathResource = System.getProperty("user.dir")+relativePath;
        Path source = Paths.get(path);
        Path target = Paths.get(pathResource);
        try {
            File root = new File(dirImage);
            if (!root.exists()) {
               root.mkdirs();
           }
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            return relativePath;
        } catch (IOException ex) {
            System.out.println("Error al Guardar la Imagen: " + ex.toString());
            return "";
        }
    }
    
    private void saveEvent(){
        Events events = new Events();
        
        String name = Validations.ValidarCampo(nombre.getText(),"El Campo Nombre no puede ser vacio");
        if (name.isBlank()) {
            return;
        }
        events.setName(name);
        String sinop = Validations.ValidarCampo(synopsis.getText(),"El campo synopsis no puede ser vacio");
        if (sinop.isBlank()) {
            return;
        }
        events.setSynopsis(sinop);
        String ho = Validations.ValidarHora(horaOculto.getText(), "Formato Hora Ocultor Invalida");
        if (ho.isBlank()) {
            return;
        }       
        String fo = Validations.ValidarFecha(fechaOculto.getValue(),"Fecha Oculto Invalida");
        if (fo.isBlank()) {
            return;
        }    
        events.setHiddenDate(UniversalDateTimeFormat.convertToDateTimeOfString(String.format("%s %s", fo, ho), "dd/MM/yyyy HH:mm"));
        String hi = Validations.ValidarHora(horaInicio.getText(), "Formato Hora Inicio Invalida");
        if (hi.isBlank()) {
            return;
        }
        String fi = Validations.ValidarFecha(fechaInicio.getValue(),"Fecha Inicio Invalida");
        if (fi.isBlank()) {
            return;
        }
        events.setInitialDate(UniversalDateTimeFormat.convertToDateTimeOfString(String.format("%s %s", fi, hi), "dd/MM/yyyy HH:mm"));
        String hf = Validations.ValidarHora(horaFin.getText(), "Formato Hora Final Invalida");
        if (hf.isBlank()) {
            return;
        }
        String ff = Validations.ValidarFecha(fechaFin.getValue(),"Fecha Fin Invalida");
        if (ff.isBlank()) {
            return;
        }    
        events.setFinalDate(UniversalDateTimeFormat.convertToDateTimeOfString(String.format("%s %s", ff,hf), "dd/MM/yyyy HH:mm"));
        String hv = Validations.ValidarHora(horaVisible.getText(), "Formato Hora Visible Invalida");
        if (hv.isBlank()) {
            return;
        }
        String fv = Validations.ValidarFecha(fechaVisible.getValue(),"Fecha Visible Invalida");
        if (fv.isBlank()) {
            return;
        }
        events.setVisibleDate(UniversalDateTimeFormat.convertToDateTimeOfString(String.format("%s %s", fv, hv), "dd/MM/yyyy HH:mm")); 
        int res = Validations.ValidarCampo(responsable.getSelectionModel().getSelectedItem().getId(), "Seleccione un responsable para el evento");
        if (res == 0) {
            return;
        }
        events.setIdResponsible(res);  
        String image = Validations.ValidarCampo(rutaImagen, "Debes cargar una imagen para el evento");
        if (image.isEmpty()) {
            return;
        }
        events.setImage(saveImage(rutaImagen,nombre.getText()));
        double a = Validations.ValidarDouble(planta_a.getText(), "Debes Ingresar el valor de la planta a");
        System.out.println(a);
        if (a <= 0) {
            return;
        }
        events.setPlanta_a(a);
        double b = Validations.ValidarDouble(planta_b.getText(), "Debes Ingresar el valor de la planta b");
        if (b <= 0) {
            return;
        }
        events.setPlanta_b(b);
        double v = Validations.ValidarDouble(vip.getText(), "Debes Ingresar el valor de VIP");
        if (v <= 0) {
            return;
        }
        events.setVip(v);
        double m = Validations.ValidarDouble(vip_m.getText(), "Debes Ingresar el valor de VIP&M");
        if (m <= 0) {
            return;
        }
        String duration = Validations.ValidarCampo(duracion.getText(), "Debes Ingresar la duracion del evento");
        events.setVip_mg(m);
        if (duration.isEmpty()) {
            return;
        }
        events.setDuration(duration);        
        events.setVisible(true);         
            
        if (_evento.CreateEvent(events)) {
           Validations.AlertMessage("Operacion Exitosa!", Alert.AlertType.INFORMATION, "Evento Creado Con Exito!");
           App.view("Index");
        }else{
           Validations.AlertMessage("Error", Alert.AlertType.ERROR, "Error Al Crear Evento!");
        }
    }   
    
}
