/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.models;

import com.mycompany.ventatickets.Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * clase modelo de usuarios
 * @author cgalv
 */
public class DUser {

    /**
     * metodo que consulta lista de usuarios
     * 
     * @return a List User
     */
    public ArrayList<User> ListadoDeUsuarios(){
        
         ArrayList<User> listaUsuarios = new ArrayList<>();
         
         try (Statement sql = Conexion.getConection().createStatement()) {
             String query = "select * from usuarios";
             ResultSet resultado = sql.executeQuery(query);
             
             while(resultado.next()){
                 User usuario = new User();
                 usuario.setId(resultado.getInt("idcliente"));
                 usuario.setName(resultado.getString("nombre"));  
                 usuario.setEmail(resultado.getString("correo"));
                 usuario.setPassword(resultado.getString("clave"));
                 usuario.setActive(resultado.getBoolean("activo"));
                 usuario.setCreatedAt(resultado.getString("fecharegistro"));
                 usuario.setIdRol(resultado.getInt("idrol"));
                 usuario.setNumber(resultado.getString("numero"));
                 
                 listaUsuarios.add(usuario);
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return listaUsuarios;
    }
    
    /**
     * metodo para consultar usuario existente
     * para iniciar sesion
     * 
     * @param password contraseña del usuario
     * @param email correo del usuario
     * @return a User
     */
    @SuppressWarnings("empty-statement")
    public User UserByPasswordAndEmail(String password, String email){
        
         User model = null;
         
         try (Connection conn = Conexion.getConection()) {
             String query = "SELECT * FROM usuarios where correo = ? and clave = ? and activo = true LIMIT 1";
             PreparedStatement sql = conn.prepareStatement(query);
             sql.setString(1, email);
             sql.setString(2,password);
             ResultSet resultado = sql.executeQuery();
             
             while(resultado.next()){;
                 User usuario = new User();
                 usuario.setId(resultado.getInt("idcliente"));
                 usuario.setName(resultado.getString("nombre"));  
                 usuario.setEmail(resultado.getString("correo"));
                 usuario.setPassword(resultado.getString("clave"));
                 usuario.setActive(resultado.getBoolean("activo"));
                 usuario.setCreatedAt(resultado.getString("fecharegistro"));
                 usuario.setIdRol(resultado.getInt("idrol"));
                 usuario.setNumber(resultado.getString("numero"));
                 model = usuario;
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error "+ex.toString());
         }      
         
         return model;
    }
    
    public User UserById(int Id){
        
         User model = null;
         
         try (Connection conn = Conexion.getConection()) {
             String query = "SELECT * FROM usuarios where idcliente = ?";
             PreparedStatement sql = conn.prepareStatement(query);
             sql.setInt(1, Id);
             ResultSet resultado = sql.executeQuery();
             
             while(resultado.next()){;
                 User usuario = new User();
                 usuario.setId(resultado.getInt("idcliente"));
                 usuario.setName(resultado.getString("nombre"));  
                 usuario.setEmail(resultado.getString("correo"));
                 usuario.setPassword(resultado.getString("clave"));
                 usuario.setActive(resultado.getBoolean("activo"));
                 usuario.setCreatedAt(resultado.getString("fecharegistro"));
                 usuario.setIdRol(resultado.getInt("idrol"));
                 usuario.setNumber(resultado.getString("numero"));
                 model = usuario;
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error "+ex.toString());
         }      
         
         return model;
    }
    
    public boolean CreateUser(User model){
        boolean results = false;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("insert into usuarios(nombre,correo,clave,activo,fecharegistro,idrol,numero)");
             query.append("VALUES (?,?,?,?,?,?,?)");
             
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setString(1, model.getName());
             sql.setString(2, model.getEmail());
             sql.setString(3, model.getPassword());
             sql.setBoolean(4, model.isActive());
             sql.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
             sql.setInt(6,model.getIdRol());             
             sql.setString(7, model.getNumber());
             
             int rowAffected = sql.executeUpdate();
             
             results = rowAffected != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return results;        
    }
    
    public boolean ModifyUser(User model){
        boolean results = false;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("update usuarios set nombre = ?, correo = ?, clave = ?, activo = ?, idrol = ?, numero = ? ");
             query.append("where idcliente = ?");
             
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setString(1, model.getName());
             sql.setString(2, model.getEmail());
             sql.setString(3, model.getPassword());
             sql.setBoolean(4, model.isActive());
             sql.setInt(5,model.getIdRol());                       
             sql.setString(6, model.getNumber());
             sql.setInt(7, model.getId());
             
             int rowAffected = sql.executeUpdate();
             
             results = rowAffected != 0;
             
             System.out.println(rowAffected);
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return results;        
    }
}
