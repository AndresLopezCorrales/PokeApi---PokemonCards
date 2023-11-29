package com.mycompany.pokemonapiproyectofinal;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class ClaseRunnable implements Runnable { //Thread principal

    private static final Logger logger = LoggerConf.getLogger();
    Metodos met = new Metodos();
    String pokemon;
    
    public ClaseRunnable (String pokemon){
        this.pokemon = pokemon;
        
    }
    
    @Override
    public void run() { //met run
        
        Properties prop = new Properties();
        
        //try catch principal
        try (InputStream inputStr = new FileInputStream("src\\main\\java\\Recursos\\configArchivosProp.properties")) {
        prop.load(inputStr);
        String apiUrl = prop.getProperty("link.poke"); 
        String apiUrlNuevo = apiUrl.concat(pokemon); //liga de pokemon
        met.vVpokeCard.setNombrePkm(met.mayuPrimLetra(pokemon)); //JLabel nombre pokemon
            System.out.println(apiUrlNuevo);
            
        //try catch secundario
        try {
            
            URL url = new URL(apiUrlNuevo);
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json"); // Configurar la solicitud

            // Verificar el código de respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
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

        met.datosFaltantesPoke(pokemon); //descripcion y especie de pokemon
        
        // height, weight, idPokedex, hp
        int height = jsonObject.get("height").getAsInt(); //height
        String heightStr = Integer.toString(height);
        int weight = jsonObject.get("weight").getAsInt(); //weight
        String weightStr = Integer.toString(weight);
        int id = jsonObject.get("id").getAsInt(); //id Pokedex internacional.
        String idStr = Integer.toString(id);
        
        JsonArray statsArray = jsonObject.getAsJsonArray("stats");
        JsonObject statsHp = statsArray.get(0).getAsJsonObject();
        int hp = statsHp.get("base_stat").getAsInt(); //hp 
        String hpStr = Integer.toString(hp);
        System.out.println(height + ", " +weight + ", " +id + ", " + hp);
        
        met.vVpokeCard.setIDHeightWeightPs(idStr, heightStr, weightStr, hpStr); //JLabel IDHeightWeightPs 
        
        //tipo de pokemon para cambiar tipo de carta
        JsonArray typesCardArray = jsonObject.getAsJsonArray("types");
        JsonObject typeCard = typesCardArray.get(0).getAsJsonObject().getAsJsonObject("type");
        String tipoPokeParaCard = typeCard.get("name").getAsString(); //tipo pokemon
        
        Color myColor = new Color(255,255,200); //Blanco
        //cambiar color de fuente de texto por carta
        if (tipoPokeParaCard.equals("ghost") ||
                tipoPokeParaCard.equals("dragon") ||
                tipoPokeParaCard.equals("poison") ||
                tipoPokeParaCard.equals("dark")) {
              met.vVpokeCard.getLbDescAtaque().setForeground(Color.WHITE);
              met.vVpokeCard.getLbNamePkm1().setForeground(Color.WHITE);
              met.vVpokeCard.getLbPs().setForeground(Color.WHITE);
              met.vVpokeCard.getLbNameAtaque().setForeground(Color.WHITE);
              met.vVpokeCard.getLbPower().setForeground(Color.WHITE);
              met.vVpokeCard.getLbCONSTANTEPS().setForeground(Color.WHITE);
              met.vVpokeCard.getLbDescPkm().setForeground(myColor);
              met.vVpokeCard.repaint();
          
        }
        
        //Moveset pokemon 
     
        Object[] moveset = met.moveSetCombo(response).toArray();
        Object selectedMove;
        if (moveset.length != 0) {
                
        selectedMove = JOptionPane.showInputDialog(null,
                "Elige un ataque",
                "Moveset", 
                JOptionPane.YES_NO_CANCEL_OPTION, 
                null, 
                moveset, 
                moveset[0]);
        
                if (selectedMove == null) {
                JOptionPane.showMessageDialog(null, "Bye");
                System.exit(0);
                }
        
                System.out.println(selectedMove);
                met.datosMoveset(selectedMove);
        
               met.vVpokeCard.setMove(met.mayuPrimLetra(selectedMove.toString())); //JLabel movimiento seleccionado
           
        }else{
            selectedMove = "sin Moves";
            met.vVpokeCard.setMove(met.mayuPrimLetra(selectedMove.toString()));
        }     
        
        //imagen del pokemon - Shiny o no shiny
        JsonObject sprites = jsonObject.getAsJsonObject("sprites").getAsJsonObject("other");
        JsonObject officialArtworkObject = sprites.getAsJsonObject("official-artwork");
        
        Object[] arrayShiny = {"Shiny" , "No-Shiny"};
        
        int shinyDecision = JOptionPane.showOptionDialog(null,
                "¿Quieres que sea Shiny (variocolor)?",
                "Shiny Selector", 
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.DEFAULT_OPTION,
                null, 
                arrayShiny, 
                arrayShiny[0]);
        
            if (shinyDecision == -1) {
                JOptionPane.showMessageDialog(null, "Bye");
                System.exit(0);
                
            }
        
        
        switch(shinyDecision){
            case 0: //Shiny
                if (officialArtworkObject.has("front_shiny") && !officialArtworkObject.get("front_shiny").isJsonNull()) {
                String frontDefaultUrlShiny = officialArtworkObject.get("front_shiny").getAsString();
                URL urlImgShiny = new URL(frontDefaultUrlShiny);
                System.out.println("Tiene Shiny");
                met.imagenPokemon(urlImgShiny, tipoPokeParaCard);
                //met.vVpokeCard.repaint();
                } else {
                System.out.println("No tiene Shiny");
                String frontDefaultUrlNoShiny = officialArtworkObject.get("front_default").getAsString();
                URL urlImgNOShiny = new URL(frontDefaultUrlNoShiny);
                met.imagenPokemon(urlImgNOShiny, tipoPokeParaCard);
                }
                break;

            case 1: //NO Shiny
                String frontDefaultUrlNoShiny = officialArtworkObject.get("front_default").getAsString();
                URL urlImgNOShiny = new URL(frontDefaultUrlNoShiny);
                met.imagenPokemon(urlImgNOShiny, tipoPokeParaCard); 
                break;
        }
        
        connection.disconnect(); //cerrar la conexión
        
    }else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) { //Fallas en conexion o busqueda de pkm
        System.out.println("El Pokémon no existe");
        JOptionPane.showMessageDialog(null, "El Pokémon no existe - Intenta con otro");
        System.exit(0);
    } else {
        System.out.println("Error en la conexión: " + responseCode);
        JOptionPane.showMessageDialog(null, "Error en la conexion...");
        System.exit(0);
    }
           
    //try catch secundario
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error en la conexion...");
        
        logger.severe("Error en la conexion: " + e.getMessage());

    }

     //try catch principal
    } catch (IOException ex) {
        ex.printStackTrace();
        
        logger.severe("El pokemon seleccionado no existe: " + ex.getMessage());
    }
        
        
} //end run
    
}// end runnable

