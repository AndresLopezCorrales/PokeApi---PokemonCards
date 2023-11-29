//Manejo de excepciones por bitacora logger

package com.mycompany.pokemonapiproyectofinal;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class LoggerConf {
    private static final String LOG_FILE_PATH = "ErrorLog.log";
    private static Logger logger;
    
    static{
        confLogger();
    }
    
    public static Logger getLogger(){
        return logger;
    }
    
    private static void confLogger(){
       logger = Logger.getLogger(LoggerConf.class.getName());
       
       try{
           FileHandler fileHandler = new FileHandler(Paths.get(LOG_FILE_PATH).toString(),true);
           
           fileHandler.setFormatter(new SimpleFormatter());
           fileHandler.setLevel(Level.SEVERE);
           
           logger.addHandler(fileHandler);
           logger.setLevel(Level.SEVERE);
           
           System.out.println("BITACORA HA SIDO CREADA EN " + LOG_FILE_PATH);
           
       } catch (IOException e){
           String errorMessage = ("Bitacora de errores no ha podido ser generada." + e.getMessage());
           System.err.println(errorMessage);
           throw new RuntimeException(errorMessage, e);
       }
      
    }
}
