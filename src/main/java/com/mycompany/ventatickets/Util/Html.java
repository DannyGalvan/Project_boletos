/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ventatickets.Util;

import java.io.File;
import java.util.ArrayList;
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
}
