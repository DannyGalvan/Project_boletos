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
import com.mycompany.ventatickets.models.DResponsible;
import com.mycompany.ventatickets.models.DatesEvent;
import com.mycompany.ventatickets.models.Events;
import com.mycompany.ventatickets.models.Params;
import com.mycompany.ventatickets.models.Responsible;
import com.mycompany.ventatickets.models.UniversalDateTimeFormat;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author cgalv
 */
public class Edit implements Initializable {
    
    @FXML
    public DatePicker fechaFin;

    @FXML
    public DatePicker fechaInicio;

    @FXML
    public DatePicker fechaOculto;

    @FXML
    public DatePicker fechaVisible;

    @FXML
    public TextField horaFin;

    @FXML
    public TextField horaInicio;

    @FXML
    public TextField horaOculto;

    @FXML
    public TextField horaVisible;

    @FXML
    public TextField id;

    @FXML
    public ImageView imageView;

    @FXML
    public TextField nombre;

    @FXML
    public Button regresar;

    @FXML
    public ComboBox<Responsible> responsable;

    @FXML
    public Button save;

    @FXML
    public TextArea synopsis;

    @FXML
    public Button uploadImage;
    
    @FXML
    public TextField planta_a;

    @FXML
    public TextField planta_b;
    
    @FXML
    public TextField vip;

    @FXML
    public TextField vip_m;
    
    @FXML
    public TextField durationEvent;
    
    //campos que utilizan las fechas del evento
    
    @FXML
    public TableView<DatesEvent> tablafechas;
    
    @FXML
    public TableColumn<DatesEvent, Button> acciones;

    @FXML
    public TableColumn<DatesEvent, String> duracion;
    
    @FXML
    public TableColumn<DatesEvent, String> fechaevento;
      
    @FXML
    public TableColumn<DatesEvent, Integer> idfecha;

    @FXML
    public Button saveDate;
    
    @FXML
    public DatePicker fecha;
     
    @FXML
    public TextField hora; 
    
    
    private String rutaImagen = "";
    private final DEvents _evento = new DEvents();
    private final DResponsible _responsable = new DResponsible();
    private final DDatesEvent _dates = new DDatesEvent();


    /**
     * Initializes the controller class.
     * @param url url
     * @param rb rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Responsible> list = _responsable.ListResponsible();
        Responsible res = new Responsible();
        res.setName("-- seleccione un responsable --");
        res.setId(0);
        
        regresar.setOnMouseClicked(event -> {
            App.view("Index");
        });
        
        save.setOnMouseClicked(event -> {
            this.saveEvent();
        });
        
        uploadImage.setOnMouseClicked(event -> {
            this.uploadImages();
        });
        
        saveDate.setOnMouseClicked(event -> {
            this.addDateEvent();
        });
        
        this.viewsDates();
        Params<Events> parametros = Location.getParams();
        id.setText(String.valueOf(parametros.getDato().getId()));
        id.setEditable(false);
        nombre.setText(parametros.getDato().getName());
        synopsis.setText(parametros.getDato().getSynopsis());
        String pathResource = System.getProperty("user.dir")+parametros.getDato().getImage();
        rutaImagen = pathResource;
        imageView.setImage(new Image("file:"+pathResource));
        fechaFin.setValue(parametros.getDato().getFinalDate().toLocalDate());
        horaFin.setText(parametros.getDato().getFinalDate("HH:ss"));
        fechaInicio.setValue(parametros.getDato().getInitialDate().toLocalDate());
        horaInicio.setText(parametros.getDato().getInitialDate("HH:ss"));
        fechaVisible.setValue(parametros.getDato().getVisibleDate().toLocalDate());
        horaVisible.setText(parametros.getDato().getVisibleDate("HH:ss"));
        fechaOculto.setValue(parametros.getDato().getHiddenDate().toLocalDate());
        horaOculto.setText(parametros.getDato().getHiddenDate("HH:ss"));
        planta_a.setText(String.valueOf(parametros.getDato().getPlanta_a()));
        planta_b.setText(String.valueOf(parametros.getDato().getPlanta_b()));
        vip.setText(String.valueOf(parametros.getDato().getVip()));
        vip_m.setText(String.valueOf(parametros.getDato().getVip_mg()));
        responsable.getItems().add(res);
        durationEvent.setText(parametros.getDato().getDuration());
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
        responsable.setValue(parametros.getDato().getResponsible());
        
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
        Params<Events> parametros = Location.getParams();
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
        String duration = Validations.ValidarCampo(durationEvent.getText(), "Debes Ingresar la duracion del evento");
        events.setVip_mg(m);
        if (duration.isEmpty()) {
            return;
        }
        events.setDuration(duration);
        events.setVisible(true);    
        events.setId(parametros.getDato().getId());
            
        if (_evento.ModifyEvent(events)) {
           Validations.AlertMessage("Operacion Exitosa!", Alert.AlertType.INFORMATION, "Evento Modificado Con Exito!");
           App.view("Index");
        }else{
           Validations.AlertMessage("Error", Alert.AlertType.ERROR, "Error Al Modificar Evento!");
        }
    }
    
    private void uploadImages(){
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
    }
    
    private void viewsDates(){
        Params<Events> parametros = Location.getParams();
        ArrayList<DatesEvent> dates = _dates.ListDateEvents(parametros.getDato().getId());
        
        ObservableList<DatesEvent> ob = FXCollections.observableArrayList(dates);
        tablafechas.setItems(ob);
        idfecha.setCellValueFactory(new PropertyValueFactory<>("idfecha"));
        idfecha.setStyle("-fx-alignment: CENTER;");
        fechaevento.setCellFactory((TableColumn<DatesEvent, String> param) -> new TableCell<DatesEvent, String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    // Obtener el objeto Usuario de la fila actual
                    DatesEvent evento = getTableView().getItems().get(getIndex());
                    // Llamar al método de tu modelo con el parámetro deseado
                    String texto = evento.getFecha_evento(UniversalDateTimeFormat.getDdMMyyy_HHmmss());
                    setText(texto);
                }
            }
        });        
        fechaevento.setStyle("-fx-alignment: CENTER;");
        duracion.setCellValueFactory(new PropertyValueFactory<>("duration"));
        acciones.setCellFactory(param -> {
            return new TableCell<DatesEvent, Button>() {
                private final Button modificar = new Button("Modificar");

                {
                    modificar.getStyleClass().add("button-success");
                    modificar.setOnAction(event -> {
                        // Lógica para manejar el evento del botón
                        DatesEvent evento = getTableRow().getItem();
                        // Realizar acciones con el usuario seleccionado
                        Params<DatesEvent> parametro = new Params();
                        parametro.setDato(evento);
                        App.view("Dates",400,400,parametro);
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
        fecha.setMaxWidth(Double.MAX_VALUE);
    }
    
    private void addDateEvent(){
        Params<Events> parametros = Location.getParams();
        DatesEvent datesevent = new DatesEvent();
        String f = Validations.ValidarFecha(fecha.getValue(),"La Fecha Del Evento que intenta agregar es invalida");
        if (f.isBlank()) {
            return;
        }    
        String h = Validations.ValidarHora(hora.getText(), "La hora del evento que intenta agregar es invalida");
        if (h.isBlank()) {
            return;
        }
        datesevent.setFecha_evento(UniversalDateTimeFormat.convertToDateTimeOfString(String.format("%s %s", f, h), "dd/MM/yyyy HH:mm"));
        datesevent.setIdevento(parametros.getDato().getId());
        datesevent.setDuration(parametros.getDato().getDuration());
        
        if (_dates.CreateDateEvent(datesevent)) {
            Validations.AlertMessage("Operacion Exitosa!", Alert.AlertType.INFORMATION, "Fecha Agregada el evento exitosamente!");
            fecha.setValue(null);
            hora.setText("");
            this.viewsDates();
        }else{
            Validations.AlertMessage("Error", Alert.AlertType.ERROR, "Error al agregar fecha al evento");
        }
        
    }
    
}
