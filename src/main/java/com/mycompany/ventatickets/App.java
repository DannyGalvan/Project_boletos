package com.mycompany.ventatickets;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.mycompany.ventatickets.models.Params;


/**
 * Clase principal de la aplicación que extiende la clase Application de JavaFX.
 */
public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {      
        try {
            primaryStage = stage;
            scene = new Scene(Location.loadFXML("Home","Index"),1000, 800);
            scene.getStylesheets().add(Location.loadCSS("Global").toExternalForm());        
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    
    /**
    * Establece el controlador raíz de la aplicación.
    *
    * @param controller El nombre del controlador raíz.
    */
    public static void setRoot(String controller) {
        scene.setRoot(Location.loadFXML(controller));
    }
    
     /**
    * Establece el controlador raíz de la aplicación.
    *
    * @param controller El nombre del controlador raíz.
    * @param inf recibe parametros para otro controlador
    */
    public static void setRoot(String controller, Params inf) {
        scene.setRoot(Location.loadFXML(controller, inf));
    }

     /**
    * Establece el controlador raíz de la aplicación.
    *
    * @param controller El nombre del controlador raíz.
    * @param action Define una accion a donde queremos irnos
    */
    public static void setRoot(String controller, String action) {
        scene.setRoot(Location.loadFXML(controller,action));
    }    
    
      /**
    * Establece el controlador raíz de la aplicación.
    *
    * @param controller El nombre del controlador raíz.
    * @param action Define una accion a donde queremos irnos
    * @param inf recibe parametros para ir a otro controlador y vista
    */
    public static void setRoot(String controller, String action, Params inf) {
        scene.setRoot(Location.loadFXML(controller,action,inf));
    }
     
      /**
    * cambia de vista la aplicacion
    *
    * @param action El nombre de la vista
    */
    public static void view(String action){
        scene = new Scene(Location.loadView(action),1000, 800);
        scene.getStylesheets().add(Location.loadCSS("Global").toExternalForm());        
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
    
      /**
    * cambia de vista la aplicacion
    *
    * @param action El nombre de la vista
    */
    public static void view(String action, double width, double height, Params inf){
        scene = new Scene(Location.loadView(action,inf),width,height);
        scene.getStylesheets().add(Location.loadCSS("Global").toExternalForm());   
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();        
    }
    
      /**
    * cambia de vista la aplicacion
    *
    * @param action El nombre de la vista
    * @param inf recibe parametros a pasar a una vista
    */
    public static void view(String action, Params inf){
        scene = new Scene(Location.loadView(action, inf),1000, 800);
        scene.getStylesheets().add(Location.loadCSS("Global").toExternalForm());        
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
    
     /**
     * Punto de entrada de la aplicación.
     * Este método se ejecuta al iniciar la aplicación y es el punto de entrada
     * para la lógica principal de la aplicación.
     *
     * @param args Los argumentos de línea de comandos pasados ​​a la aplicación.
     */
    public static void main(String[] args) {
        try{
            launch();
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
    }

}