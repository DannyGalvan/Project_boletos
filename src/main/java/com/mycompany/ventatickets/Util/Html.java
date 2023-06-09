/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.Util;

import com.mycompany.ventatickets.App;
import com.mycompany.ventatickets.models.Asientos;
import com.mycompany.ventatickets.models.AsientosEventoBoletos;
import com.mycompany.ventatickets.models.DatesEvent;
import com.mycompany.ventatickets.models.Events;
import com.mycompany.ventatickets.models.PagosBoleto;
import com.mycompany.ventatickets.models.UniversalDateTimeFormat;
import java.util.UUID;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author cgalv
 */
public class Html {
    
    public static String replaceWith(String path, ArrayList<String> remplazar, ArrayList<String> nuevaPalabra){
        String document = "";
        try {
             // Leer el archivo HTML
            File input = new File(path);
            Document doc = Jsoup.parse(input, "UTF-8");
            // Realizar el reemplazo de palabras en el contenido HTML
            Element body = doc.body();
            for (int i = 0; i < remplazar.size(); i++) {
                body.html(body.html().replace(remplazar.get(i), nuevaPalabra.get(i)));                
            }

            // Imprimir el HTML resultante
            document = doc.html();
        } catch (Exception e) {
            document = "";
        }
        
        return document;
    }
    
    public static String generarteHTMl(Events evento, DatesEvent fecha, ArrayList<AsientosEventoBoletos> boletos, ArrayList<Asientos> asientos, PagosBoleto pago){
        String path = "";
        try {
            Path pathRoot = Paths.get(App.class.getResource("/mailTemplate/Index.html").toURI());
            String filePath = pathRoot.toString();
            String date = fecha.getFecha_evento(UniversalDateTimeFormat.getDdMMYY_HHmmss12H());
            ArrayList<String> reemplazar = new ArrayList<>(Arrays.asList("@FechaPago", "@Fecha", "@Cliente", "@NoTarjeta", "@Referencia","@evento", "@TotalPago", "@NoPago"));
            ArrayList<String> nuevas = new ArrayList<>(Arrays.asList(date, date, pago.getName(), pago.getNumber(), pago.getAdress(), evento.getName(), String.valueOf(pago.getTotal()),String.valueOf(pago.getId())));
            String html = Html.replaceWith(filePath, reemplazar, nuevas);
            Document document = Jsoup.parse(html);
            Element table = document.select("#Eventos").first();
            
            for (int i = 0; i < asientos.size(); i++) {
                Element row = new Element("tr");
                row.appendChild(new Element("td").attr("colspan", "1").text(String.valueOf(boletos.get(i).getId())));
                row.appendChild(new Element("td").attr("colspan", "2").text(asientos.get(i).getSection().getDescription()));
                row.appendChild(new Element("td").attr("colspan", "1").text(boletos.get(i).getCodigoAsiento()));
                row.appendChild(new Element("td").attr("colspan", "1").text(String.valueOf(boletos.get(i).getPrecio())));
                row.appendChild(new Element("td").attr("colspan", "2").text(date));  
                table.appendChild(row);
            }
            
            String dirHtml = System.getProperty("user.dir")+"/src/main/resources/html";
            UUID uuid = UUID.randomUUID();
            String name = "reporte_"+pago.getId()+"_"+uuid.toString();
            String relativePath = "/src/main/resources/html/"+name+".html";
            String pathResource = System.getProperty("user.dir")+relativePath;
            File root = new File(dirHtml);
            if (!root.exists()) {
               root.mkdirs();
            }
            // Guardar el documento HTML modificado en un archivo
            File file = new File(pathResource);
            try (FileWriter flwriter = new FileWriter(pathResource)) {
                BufferedWriter bfwriter = new BufferedWriter(flwriter);
                bfwriter.write(document.html());
                bfwriter.flush();
            }

            path = file.toURI().toURL().toString();
            
        } catch (IOException | URISyntaxException e) {
            path = "";
            System.out.println(e.toString());
        }
        return path;
    }
}
