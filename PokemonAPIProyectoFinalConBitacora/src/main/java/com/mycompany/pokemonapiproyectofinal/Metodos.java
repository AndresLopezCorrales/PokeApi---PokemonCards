package com.mycompany.pokemonapiproyectofinal;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Metodos {
    
    VentanaViewPokeCard vVpokeCard;

    public Metodos() {
        vVpokeCard = new VentanaViewPokeCard(); //inicializar Venatana para usar esta instancia por todas las clases
    }
            
    //Cargar Objetos a un comboBox
    public LinkedList cargarAlComboBox(String meterAlComboBox){
        LinkedList<Object> linkedList = new LinkedList<>();
       
        String apiUrl = meterAlComboBox; //url a acceder

        try {
            
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //abrir la conexion

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json"); // Configurar la solicitud

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // Verificar el código de respuesta
                
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream())); //Leer la respuesta
                String line;
                StringBuilder response = new StringBuilder(); //JSON para agarrar los datos usando GSON

                while ((line = br.readLine()) != null) {
                    response.append(line); //Json en String para su uso
                }
                br.close(); //cerrar br
               
                Gson gson = new Gson(); //GSON

        // JSON -> Objeto
        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);

        // JSONArray
        JsonArray resultsArray = jsonObject.getAsJsonArray("results");

                for (int i = 0; i < resultsArray.size(); i++) {
                    JsonObject allResults = resultsArray.get(i).getAsJsonObject(); //obtener todos los objetos
                    String tipoPokemon = allResults.get("name").getAsString(); //agarrar nombre de pokemon
                    linkedList.add(tipoPokemon); //guardarlo en el LinkedList 
                }
                
            } else {
                System.out.println("Error en la solicitud. Código de respuesta: " + responseCode);
            }
            
            connection.disconnect(); //cerrar la conexión

        } catch (IOException e) {
            e.printStackTrace();
        }
       
        return linkedList;
    }
    
    //meter al comboMoveSet
    public LinkedList moveSetCombo(StringBuilder response){
        LinkedList<Object> linkedList = new LinkedList<>();
        Gson gson = new Gson(); //GSON

        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
        
        if (jsonObject.has("moves")) {
            JsonArray movesArray = jsonObject.getAsJsonArray("moves");
        

                for (int i = 0; i < movesArray.size(); i++) {
                    JsonObject allMoves = movesArray.get(i).getAsJsonObject(); //obtener todos los objetos
                    JsonObject move = allMoves.getAsJsonObject("move");
                    String moveNombre = move.get("name").getAsString(); //agarrar moveset
                    linkedList.add(moveNombre); //guardarlo en el LinkedList
                    
                }
        }else{
            System.out.println("No tiene moves");
        }
        
    return linkedList;
    }
    
    //Sacar tipos de pokemon y meterlos a un LinkedList
    public LinkedList pkmPorTipo (StringBuilder response){
        LinkedList<Object> linkedList = new LinkedList<>();
        Gson gson = new Gson(); //GSON

        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
        JsonArray pokemon = jsonObject.getAsJsonArray("pokemon");
        String nombrePokemon;       
                
        for (int i = 0; i < pokemon.size(); i++) {
            JsonObject allPokemon = pokemon.get(i).getAsJsonObject();
            JsonObject pkm = allPokemon.getAsJsonObject("pokemon");
            nombrePokemon = pkm.get("name").getAsString();
            linkedList.add(nombrePokemon); 
        }
        return linkedList;
    }
    
    //Sacar generaciones de pokemon y mterlos a un LinkedList
    public LinkedList pkmPorGen(StringBuilder response){
        LinkedList<Object> linkedList = new LinkedList<>();
        Gson gson = new Gson(); //GSON

        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
        JsonArray pokemon = jsonObject.getAsJsonArray("pokemon_species");
        String nombrePokemon;       
                
        for (int i = 0; i < pokemon.size(); i++) {
            JsonObject allPokemon = pokemon.get(i).getAsJsonObject();
            nombrePokemon = allPokemon.get("name").getAsString();
            linkedList.add(nombrePokemon); 
        }
        return linkedList;    
        
    }
    
    //metodo sacar imagen pokemon
    
    JLabel labelFotoPoke;
    JLabel labelCardPoke;
    
    public void imagenPokemon(URL urlImg, String tipoCartaPokemon) {
    labelFotoPoke = new JLabel();
    
    try {
    Image image = ImageIO.read(urlImg);
    System.out.println("Imagen leída correctamente");
    

    if (image != null) {
        System.out.println("Creando ImageIcon");
        //Reajustar imagen
        int width = 340;
        int height = 230;
        image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image);
        labelFotoPoke = vVpokeCard.getLbFotoPokemon();
        labelFotoPoke.setIcon(imageIcon);
        vVpokeCard.setLbFotoPokemon(labelFotoPoke);
        
        //Tipo de carta que se le pondra al pokemon (es por tipos)
        String foto = "src\\main\\java\\Recursos\\CartasOutlinePokemon\\" + tipoCartaPokemon + ".png";
        ImageIcon ImI = new ImageIcon(foto);
        labelCardPoke = vVpokeCard.getLbColorCardPoke();
        labelCardPoke.setIcon(ImI);
        vVpokeCard.setLbColorCardPoke(labelCardPoke);  
        
        vVpokeCard.setSize(500,700);
        vVpokeCard.setResizable(false);
        
        vVpokeCard.setVisible(true);
    } else {
        System.out.println("La imagen es nula");
    }
    } catch (IOException e) {
        e.printStackTrace();
    }
}// end metodo imagenPokemon
    
    
    //sacar datos moveset
    
    String power;

    public void datosMoveset(Object move){
        
        power = "";
        
        Properties prop = new Properties();
        try (InputStream inputStr = new FileInputStream("src\\main\\java\\Recursos\\configArchivosProp.properties")) {
        prop.load(inputStr);
        String apiUrl = prop.getProperty("link.moves"); 
        String apiUrlNuevo = apiUrl.concat(move.toString());
        try {
            
            URL url = new URL(apiUrlNuevo);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                
                // Leer la respuesta
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder(); 

                while ((line = br.readLine()) != null) {
                    response.append(line); //Json en String
                }
                br.close(); //cerrar Br
        
                Gson gson = new Gson(); //GSON

        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
        int powerMove; 
        
                if (jsonObject.get("power").isJsonNull()) {
                    powerMove = 0;
                }else{
                    powerMove = jsonObject.get("power").getAsInt(); //power
                   
                   power = Integer.toString(powerMove);
                   
                   vVpokeCard.setPowerValue(power); //Set JLabel de power de ataque
                   
                }
                
        System.out.println(powerMove);
        
        //descripcion move
        
        // Obtener la lista de entradas de "flavor text"
        JsonArray flavorTextEntradas = jsonObject.getAsJsonArray("flavor_text_entries");

        for (int i = 0; i < flavorTextEntradas.size(); i++) {
            JsonObject entry = flavorTextEntradas.get(i).getAsJsonObject();

            JsonObject lang = entry.getAsJsonObject("language"); // Obtener el objeto "language"
            JsonObject verGroup = entry.getAsJsonObject("version_group"); //Obtener el objeto "Version Group"

            // Verificar si el idioma es "en"
            if (lang.get("name").getAsString().equals("en") &&
                    verGroup.get("url").getAsString().equals("https://pokeapi.co/api/v2/version-group/20/")) {
                
                // Obtener el "flavor text" 
                String descAttk = entry.get("flavor_text").getAsString(); //desc del movimiento
                
                //Decodificación
                String txtCo = new String(descAttk.getBytes("ISO-8859-1"), "UTF-8");
                String newTxt = txtCo.replace("?", "");
                
                String txtCo2 = new String(newTxt.getBytes("ISO-8859-1"), "UTF-8");
                String newTxt2 = txtCo2.replace("?", "'");
                
                String txtCo3 = new String(newTxt2.getBytes("ISO-8859-1"), "UTF-8");
                String newTxt3 = txtCo3.replace("?", "é");
                
                String finalTxt = insertarSaltosDeLineaHTML(newTxt3, 8);
                
                vVpokeCard.setDescAtaque(finalTxt); //set JLabel de desc attk
                System.out.println(finalTxt);
                break;
            }
        }
        
        connection.disconnect(); //cerrar la conexión
        
    }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        } //end datosMoveset
    
    
    //datosFaltantesPokemon
    public void datosFaltantesPoke(String nombrePokemon){
        Properties prop = new Properties();
        try (InputStream inputStr = new FileInputStream("src\\main\\java\\Recursos\\configArchivosProp.properties")) {
        prop.load(inputStr);
        String apiUrl = prop.getProperty("link.pokeSpecies"); 
        String apiUrlNuevo = apiUrl.concat(nombrePokemon);
        try {
            
            URL url = new URL(apiUrlNuevo);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // Verificar el código de respuesta
                // Leer la respuesta
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    response.append(line); //Json en String
                }
                br.close(); //cerrar br
        
                Gson gson = new Gson(); //GSON

        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
        
        //descripcion pokemon
      
        JsonArray flavorTextEntradas = jsonObject.getAsJsonArray("flavor_text_entries");

        for (int i = 0; i < flavorTextEntradas.size(); i++) {
            JsonObject entry = flavorTextEntradas.get(i).getAsJsonObject();

            
            JsonObject lang = entry.getAsJsonObject("language"); // Obtener el objeto "language"

            // Verificar si el idioma es "en"
            if (lang.get("name").getAsString().equals("en")) {
                
                // Obtener el "flavor text" e imprimirlo
                String descPkm = entry.get("flavor_text").getAsString(); //desc del pokemon
                
                //Decodificacion
                String txtCo = new String(descPkm.getBytes("ISO-8859-1"), "UTF-8");
                String newTxt = txtCo.replace("?", "");
                
                String txtCo2 = new String(newTxt.getBytes("ISO-8859-1"), "UTF-8");
                String newTxt2 = txtCo2.replace("?", "'");
                
                String txtCo3 = new String(newTxt2.getBytes("ISO-8859-1"), "UTF-8");
                String newTxt3 = txtCo3.replace("?", "é");
                
                String finalTxt = insertarSaltosDeLineaHTML(newTxt3, 12);
                
                vVpokeCard.setDescPkm(finalTxt.replaceAll("\u00AD", "")); //meter JLabel desc pokm
                
                break; 
            }
        }
        
         //especie del pokemon - genera
         
        JsonArray genera = jsonObject.getAsJsonArray("genera");
        for (int i = 0; i < genera.size(); i++) {
            JsonObject generaText = genera.get(i).getAsJsonObject();

            // Obtener el objeto "language"
            JsonObject lang = generaText.getAsJsonObject("language");

            // Verificar si idioma es "en"
            if (lang.get("name").getAsString().equals("en")) {
                
                // Obtener el genus
                String genus = generaText.get("genus").getAsString(); //especie del pokemon
                
                //Decodificacion
                String txtCo = new String(genus.getBytes("ISO-8859-1"), "UTF-8");
                String newTxt = txtCo.replace("?", "");
                
                String txtCo2 = new String(newTxt.getBytes("ISO-8859-1"), "UTF-8");
                String newTxt2 = txtCo2.replace("?", "'");
                String txtCo3 = new String(newTxt2.getBytes("ISO-8859-1"), "UTF-8");
                String newTxt3 = txtCo3.replace("?", "e");
                vVpokeCard.setGenus(newTxt3); //meter JLabel genera
                System.out.println(newTxt3);
                break; 
            }
        }
        
        connection.disconnect(); //cerrar la conexión
        
    }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
}// end datosFaltantesPokemon
    
    
    //Seleccionar Pokemon por Nombre 
    public void selecPorNombre(){
        
        String namePokemon = JOptionPane.showInputDialog(null, 
                "Escribe el nombre de un Pokemon");
        
        if (namePokemon == null) {
            JOptionPane.showMessageDialog(null, "Bye");
            System.exit(0);
        }
        
        String nPkmLow = namePokemon.toLowerCase();
        
        ClaseRunnable cR = new ClaseRunnable(nPkmLow);

        Thread thread1 = new Thread(cR); //Thread

        thread1.start();// Iniciar Thread

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Programa principal ha terminado.");
       
    }
    
    //Selecionar por tipo 
    public void selecPorTipo(){
        String namePokemon = "";
        
        Properties prop = new Properties(); //Archivo de propiedades
        try (InputStream inputStr = new FileInputStream("src\\main\\java\\Recursos\\configArchivosProp.properties")) {
        prop.load(inputStr);
        
        Object[] cBox = cargarAlComboBox(prop.getProperty("link.type")).toArray();
        cBox[18] = "";
        cBox[19] = "";
        Object selectedType = JOptionPane.showInputDialog(null,
                "Elige un tipo",
                "Tipos de Pokemon", 
                JOptionPane.YES_NO_CANCEL_OPTION, 
                null, 
                cBox, 
                cBox[0]);
        
            if (selectedType == null) {
                JOptionPane.showMessageDialog(null, "Bye");
                System.exit(0);
                
            }
        
        ClassRunnableTipo cRT = new ClassRunnableTipo(selectedType.toString());
        
        Thread thread1 = new Thread(cRT); //Thread Tipo
        
        thread1.start();// Iniciar Thread
        
        try {
            thread1.join(); //Esperar que termine el hilo de tipo
  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        namePokemon = cRT.getPokemonSeleccionado();
       
        ClaseRunnable cR = new ClaseRunnable(namePokemon); 
        Thread thread2 = new Thread (cR);//Thread principal
        thread2.start(); //Inicia
        
        
        try {
            thread2.join(); //Esperar a que termine
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Programa principal ha terminado.");
        
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
    }
    
    //Selecionar por generacion
    public void selectPorGen(){
        String namePokemon = "";
        
        Properties prop = new Properties();
        try (InputStream inputStr = new FileInputStream("src\\main\\java\\Recursos\\configArchivosProp.properties")) {
        prop.load(inputStr);
        
        Object[] cBox = cargarAlComboBox(prop.getProperty("link.gen")).toArray();
        Object selectedGen = JOptionPane.showInputDialog(null,
                "Elige una generacion de Pokemon",
                "Generacion", 
                JOptionPane.YES_NO_CANCEL_OPTION, 
                null, 
                cBox, 
                cBox[0]);
        
            if (selectedGen == null) {
                JOptionPane.showMessageDialog(null, "Bye");
                System.exit(0);
                
            }
        
            System.out.println(selectedGen);
        
            ClassRunnableGen cRG = new ClassRunnableGen(selectedGen.toString());
            Thread thread1 = new Thread(cRG); //Thread generacion
            thread1.start(); //se inicia
            try {
            thread1.join(); //esperar a que termine
  
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
            

        namePokemon = cRG.getPokemonSeleccionado();
        
        ClaseRunnable cR = new ClaseRunnable(namePokemon); 
        Thread thread2 = new Thread (cR);//Thread prinicpal
        thread2.start(); //Se inicia
        
        try {
            thread2.join(); //Esperar a que termine
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Programa principal ha terminado.");
        
        } catch (IOException ex) {
            ex.printStackTrace();
        }
}
    
//saltos de linea en JLabels
public String insertarSaltosDeLineaHTML(String input, int palabrasPorLinea) {
    String[] palabras = input.split("\\s+"); // Dividir la cadena en palabras
    StringBuilder resultado = new StringBuilder("<html>");

    for (int i = 0; i < palabras.length; i++) {
        resultado.append(palabras[i]).append(" ");

        // Insertar salto de línea después de cada conjunto de palabrasPorLinea palabras
        if ((i + 1) % palabrasPorLinea == 0 && i < palabras.length - 1) {
            resultado.append("<br>");
        }
    }

    resultado.append("</html>");
    return resultado.toString().trim();
}// end insertarSaltos

//mayu primera letra
public String mayuPrimLetra(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }//end met mayuscula


//Musica Pkm
public void musicaPkm(){
    
    Properties prop = new Properties();
        try (InputStream inputStr = new FileInputStream("src\\main\\java\\Recursos\\configArchivosProp.properties")) {
        prop.load(inputStr);
        String musica = prop.getProperty("musica.allIntros"); 
        
    try{
        File sonido = new File(musica);
        
        if (sonido.exists()) {
            AudioInputStream aIn = AudioSystem.getAudioInputStream(sonido);
            Clip clip = AudioSystem.getClip();
            clip.open(aIn);
            clip.start();
        }else{
            System.out.println("Archivo no encontrado");
        }
        
    }catch(IOException | LineUnavailableException | UnsupportedAudioFileException e){
        System.out.println(e.getMessage());
        
    }
    }catch(IOException ioe){
        System.out.println(ioe.getMessage());
    }
    
}//end met musicaPkm
    
    
}//end metodos
