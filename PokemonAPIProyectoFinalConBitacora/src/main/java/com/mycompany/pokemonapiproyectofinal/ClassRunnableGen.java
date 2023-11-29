package com.mycompany.pokemonapiproyectofinal;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

public class ClassRunnableGen implements Runnable {
    
        private static final Logger logger = LoggerConf.getLogger();

        Metodos met = new Metodos();
        String gen;
        String pokemonSeleccionado;

        //Get y set Pokemon Seleccionado
    public String getPokemonSeleccionado() {
        return pokemonSeleccionado;
    }

    public void setPokemonSeleccionado(String pokemonSeleccionado) {
        this.pokemonSeleccionado = pokemonSeleccionado;
    }
    
   
    
    public ClassRunnableGen (String gen){
        this.gen = gen;
        
    }
    
    
    @Override
    public void run() {
        
        Properties prop = new Properties();
        try (InputStream inputStr = new FileInputStream("src\\main\\java\\Recursos\\configArchivosProp.properties")) {
            prop.load(inputStr);
            
            String apiUrl = prop.getProperty("link.gen"); 
            String apiUrlNuevo = apiUrl.concat(gen);
        
            System.out.println(apiUrlNuevo);

        try {
            URL url = new URL(apiUrlNuevo);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {// Verificar el código de respuesta
                // Leer la respuesta
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder(); //JSON para agarrar los datos usando GSON

                while ((line = br.readLine()) != null) {
                    response.append(line); //Json en String
                }
                br.close(); //cerrar Br
        
                Gson gson = new Gson(); //GSON
                JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
            
                
                //Selecionar pokemon por generacion
                Object[] pokemonPorSelectedGen = met.pkmPorGen(response).toArray();
                Object selectedPokemon = JOptionPane.showInputDialog(null,
                "Elige un pokemon",
                "Pokemon", 
                JOptionPane.YES_NO_CANCEL_OPTION, 
                null, 
                pokemonPorSelectedGen, 
                pokemonPorSelectedGen[0]);
                
                if (selectedPokemon == null) {
                JOptionPane.showMessageDialog(null, "Bye");
                System.exit(0);
                
            }
                
                System.out.println(selectedPokemon);

                pokemonSeleccionado = selectedPokemon.toString();
                
                System.out.println(pokemonSeleccionado);
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.severe("Error en la conexion: " + ex.getMessage());

        }
        
        }catch (IOException ex) {
            ex.printStackTrace();
            logger.severe("El pokemon seleccionado no existe: " + ex.getMessage());

        }
    
}// end run
    
} // end runnable Gen
