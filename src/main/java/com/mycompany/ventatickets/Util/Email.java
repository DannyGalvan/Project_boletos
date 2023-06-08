/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.Util;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.Validations;
import com.mycompany.ventatickets.models.AsientosEventoBoletos;
import com.mycompany.ventatickets.models.DatesEvent;
import com.mycompany.ventatickets.models.Events;
import com.mycompany.ventatickets.models.UniversalDateTimeFormat;
import io.github.cdimascio.dotenv.Dotenv;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import javafx.scene.control.Alert;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 *
 * @author cgalv
 */
public class Email {
   
    private static final Dotenv dotenv = Dotenv.load();
    
    public static void sendTextEmail(String email, String asunto, String contenido){      
        
        Properties properties = new Properties();
        properties.put("mail.smtp.host", dotenv.get("HOST")); // Reemplaza con tu servidor SMTP
        properties.put("mail.smtp.port", dotenv.get("PORT")); // Reemplaza con el puerto SMTP adecuado
        properties.put("mail.smtp.auth", "true"); // Si se requiere autenticación
        properties.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication( dotenv.get("EMAIL"), dotenv.get("PASSWORD"));
            }
        });
        
         try {
            // Crear un objeto MimeMessage
            Message message = new MimeMessage(session);

            // Establecer el remitente
            message.setFrom(new InternetAddress(dotenv.get("EMAIL")));

            // Establecer el destinatario(s)
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            // Establecer el asunto
            message.setSubject(asunto);

            // Establecer el contenido del mensaje
            message.setText(contenido);

            // Enviar el correo electrónico
            Transport.send(message);
        } catch (MessagingException e) {
            Validations.AlertMessage(e.toString(), Alert.AlertType.ERROR, "Error");
             System.out.println(e.toString());
        }
    }
    
    public static void sendHTMLEmail(String asunto, Events evento, DatesEvent fecha, AsientosEventoBoletos asientos){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", dotenv.get("HOST"));
        properties.put("mail.smtp.port", dotenv.get("PORT")); 
        properties.put("mail.smtp.auth", "true"); 
        properties.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication( dotenv.get("EMAIL"), dotenv.get("PASSWORD"));
            }
        });
        
         try {
            // Crear un objeto MimeMessage
            Message message = new MimeMessage(session);

            // Establecer el remitente
            message.setFrom(new InternetAddress(dotenv.get("EMAIL")));

            // Establecer el destinatario(s)
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(asientos.getEmail()));

            // Establecer el asunto
            message.setSubject(asunto);
            
            // Crear una parte del cuerpo del mensaje en formato HTML
            MimeBodyPart bodyPart = new MimeBodyPart();
            Path path = Paths.get(App.class.getResource("/mailTemplate/Ticket.html").toURI());
            String filePath = path.toString();           
            ArrayList<String> reemplazar = new ArrayList<>(Arrays.asList("@fecha", "@codigo", "@monto", "@Evento"));
            ArrayList<String> nuevas = new ArrayList<>(Arrays.asList(fecha.getFecha_evento(UniversalDateTimeFormat.getDdMMYY_HHmmss12H()), asientos.getCodigoAsiento(), String.valueOf(asientos.getPrecio()), evento.getName()));
            String html = Html.replaceWith(filePath, reemplazar, nuevas);
            bodyPart.setContent(html, "text/html");

           // Crear el objeto MimeMultipart para combinar las partes del mensaje
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            
            // Establecer el contenido del mensaje
            message.setContent(multipart);

            // Enviar el correo electrónico
            Transport.send(message);
            
        } catch (MessagingException e) {
            Validations.AlertMessage(e.toString(), Alert.AlertType.ERROR, "Error");
        } catch (URISyntaxException ex) {
            Validations.AlertMessage(ex.toString(), Alert.AlertType.ERROR, "Error");
        }
    }
}
