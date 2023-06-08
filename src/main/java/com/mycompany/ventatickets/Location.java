/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import com.mycompany.ventatickets.models.Params;

/**
 * Clase de locacion para la aplicación VentaTickets.
 * Esta clase representa el enrutado de la app
 * sirve para cambiar entre pantallas
 * 
 * @author cgalv
 */
public class Location {
    
    private static String controller;
    private static String action;
    private static Params params = new Params<String>();
    //private static final String pathRoot = "/src/main/resources/";
    
     /**
     * @return the controller
     */
    public static String getController() {
        return controller;
    }

    /**
     * @param controller the controller to set
     */
    private static void setController(String controller) {
        Location.controller = controller;
    }

    /**
     * @return the action
     */
    public static String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    private static void setAction(String action) {
        Location.action = action;
    }
    
    /**
     * @return the params
     */
    public static Params getParams() {
        return params;
    }

    /**
     * @param aParams the params to set
     */
    public static void setParams(Params aParams) {
        params = aParams;
    }
    
    /**
     * metodo se utiliza para cargar una ruta especifica de los recursos
     * 
     * @param path
     * @return
     */
    public static URL loadPath(String path){
         URL ProjectRoot = App.class.getResource(path);
         return ProjectRoot;
    }
    
    /**
     * metodo para cargar archivos css
     * 
     * @param name recibe el nombre del archivo sin la extencion css
     * @return
     */
    protected static URL loadCSS(String name) {
        //String projectDir = System.getProperty("user.dir");
        //URL ProjectRoot = Paths.get(projectDir+pathRoot+"Styles/"+name+".css").toUri().toURL();
        URL ProjectRoot = App.class.getResource("/styles/"+name+".css");
        return ProjectRoot;
    }
    
    /**
     * metodo para cambiar de controlador a la vista
     * principal del mismo
     * 
     * @param controller nombre del controlador
     * @return a Parent
     */
    protected static Parent loadFXML(String controller) {
        //String projectDir = System.getProperty("user.dir");
        try {      
            //URL ProjectRoot = Paths.get(projectDir+pathRoot+"views/"+controller+"/Index.fxml").toUri().toURL();
            URL ProjectRoot = App.class.getResource("/views/" +controller+  "/Index.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
            setAction("Index");
            setController(controller);
            return fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("No Existe el controllador o la vista " + ex.toString());
                URL ProjectRoot;
            try {
                //ProjectRoot = Paths.get(projectDir+pathRoot+"views/Home/Index.fxml").toUri().toURL();
                ProjectRoot = App.class.getResource("/views/Home/Index.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
                setAction("Index");
                setController("Home");
                return fxmlLoader.load();
            } catch (MalformedURLException ex1) {
                 System.out.print( ex1.toString());
            } catch (IOException ex1) {
               System.out.print( ex1.toString());
            }         
        }
        return null;
    }
     
    /**
     * metodo para cambiar de controlador pasando 
     * un parametro a la vista
     * 
     * @param controller nombre del controlador
     * @param info el parametro del tipo deseado
     * @return a Parent
     */
    protected static Parent loadFXML(String controller, Params info) {
        //String projectDir = System.getProperty("user.dir");
        try {            
            //URL ProjectRoot = Paths.get(projectDir+pathRoot+"views/"+controller+"/Index.fxml").toUri().toURL();
            URL ProjectRoot = App.class.getResource("/views/"+controller+"/Index.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
            setAction("Index");
            setController(controller);
            setParams(info);
            return fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("No Existe el controllador o la vista");
                URL ProjectRoot;
            try {
                //ProjectRoot = Paths.get(projectDir+pathRoot+"views/Home/Index.fxml").toUri().toURL();
                ProjectRoot = App.class.getResource("/views/Home/Index.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
                setAction("Index");
                setController("Home");
                return fxmlLoader.load();
            } catch (MalformedURLException ex1) {
                 System.out.print( ex1.toString());
            } catch (IOException ex1) {
               System.out.print( ex1.toString());
            }         
        }
        return null;
    }
    
    /**
     * metodo para cambiar de controlador a una 
     * vista especifica
     * 
     * @param controller nombre del controlador
     * @param action nombre de la vista a la cual quieres ir
     * @return a Parent
     */
    protected static Parent loadFXML(String controller, String action) {
        //String projectDir = System.getProperty("user.dir");        
        try {            
            //URL ProjectRoot = Paths.get(projectDir+pathRoot+"views/"+controller+"/"+action+".fxml").toUri().toURL();
            URL ProjectRoot = App.class.getResource("/views/"+controller+"/"+action+".fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
            setAction(action);
            setController(controller);
            return fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("No Existe el controllador o la vista");
                URL ProjectRoot;
            try {
                //ProjectRoot = Paths.get(projectDir+pathRoot+"views/Home/Index.fxml").toUri().toURL();
                ProjectRoot = App.class.getResource("/views/Home/Index.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
                setAction("Index");
                setController("Home");
                return fxmlLoader.load();
            } catch (MalformedURLException ex1) {
                 System.out.print( ex1.toString());
            } catch (IOException ex1) {
               System.out.print( ex1.toString());
            }         
        }
        return null;
    }
    
    /**
     * metodo para cambiar de controlador 
     * y vista incluyendo parametros a la vista
     * 
     * @param controller nombre del controlador
     * @param action nombre de la vista
     * @param info parametros deceados
     * @return a Parent
     */
    protected static Parent loadFXML(String controller, String action, Params info) {
        //String projectDir = System.getProperty("user.dir");        
        try {            
            //URL ProjectRoot = Paths.get(projectDir+pathRoot+"views/"+controller+"/"+action+".fxml").toUri().toURL();
            URL ProjectRoot = App.class.getResource("/views/"+controller+"/"+action+".fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
            setAction(action);
            setController(controller);
            setParams(info);
            return fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("No Existe el controllador o la vista");
                URL ProjectRoot;
            try {
                //ProjectRoot = Paths.get(projectDir+pathRoot+"views/Home/Index.fxml").toUri().toURL();
                ProjectRoot = App.class.getResource("/views/Home/Index.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
                setAction("Index");
                setController("Home");
                return fxmlLoader.load();
            } catch (MalformedURLException ex1) {
                 System.out.print( ex1.toString());
            } catch (IOException ex1) {
               System.out.print( ex1.toString());
            }         
        }
        return null;
    }
    
    /**
     * metodo para cargar una vista sin 
     * cambiar el controlador
     * 
     * @param view nombre de la vista, cambia en el mismo controlador
     * @return a Parent
     */
    protected static Parent loadView(String view) {
        //String projectDir = System.getProperty("user.dir");
        try {            
            //URL ProjectRoot = Paths.get(projectDir+pathRoot+"views/"+Location.controller+"/"+view+".fxml").toUri().toURL();
            URL ProjectRoot = App.class.getResource("/views/"+Location.controller+"/"+view+".fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
            setAction(view);
            setController(Location.controller);
            return fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("No Existe el controllador o la vista");
                URL ProjectRoot;
            try {
                //ProjectRoot = Paths.get(projectDir+pathRoot+"views/Home/Index.fxml").toUri().toURL();
                ProjectRoot = App.class.getResource("/views/Home/Index.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
                setAction("Index");
                setController("Home");
                return fxmlLoader.load();
            } catch (MalformedURLException ex1) {
                 System.out.print( ex1.toString());
            } catch (IOException ex1) {
               System.out.print( ex1.toString());
            }         
        }
        return null;
    }
    
    /**
     * metodo para cambiar de vista
     * dentro del mismo controlador incluyendo
     * un parametro
     * 
     * @param view nombre de la vista
     * @param inf parametros deseados
     * @return a Parent
     */
    protected static Parent loadView(String view, Params inf) {
       //String projectDir = System.getProperty("user.dir");
       try {            
           //URL ProjectRoot = Paths.get(projectDir+pathRoot+"views/"+Location.controller+"/"+view+".fxml").toUri().toURL();
           URL ProjectRoot = App.class.getResource("/views/"+Location.controller+"/"+view+".fxml");
           FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
           setAction(view);
           setController(Location.controller);
           setParams(inf);
           return fxmlLoader.load();
       } catch (IOException ex) {
           System.out.println("No Existe el controllador o la vista");
               URL ProjectRoot;
           try {
               //ProjectRoot = Paths.get(projectDir+pathRoot+"views/Home/Index.fxml").toUri().toURL();
               ProjectRoot = App.class.getResource("/views/Home/Index.fxml");
               FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
               setAction("Index");
               setController("Home");
               return fxmlLoader.load();
           } catch (MalformedURLException ex1) {
                System.out.print( ex1.toString());
           } catch (IOException ex1) {
              System.out.print( ex1.toString());
           }         
       }
       return null;
   }   
    
}
