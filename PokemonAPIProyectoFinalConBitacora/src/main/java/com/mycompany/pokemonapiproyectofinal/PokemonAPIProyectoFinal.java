package com.mycompany.pokemonapiproyectofinal;

import javax.swing.JOptionPane;

public class PokemonAPIProyectoFinal { // clase main
    public static void main(String[] args) {
        SeleccionarPokemon sP = new SeleccionarPokemon();
        Metodos met = new Metodos();
        boolean keepgoing = true; 
        met.musicaPkm(); //llamado a la musica
       do {
    // Joption para comenzar
    
    Object[] comenzar = {"Comenzar"};
    int comenzarDecision = JOptionPane.showOptionDialog(null,
            "START",
            "Cartas Pokemon - Start",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.DEFAULT_OPTION,
            null,
            comenzar,
            comenzar[0]);

    if (comenzarDecision == 0) {
        sP.seleccionarPoke(); // llamado a metodo select poke

        int keepgoingint = JOptionPane.showConfirmDialog(null,
                "¿Quisiera seguir escogiendo?",
                "Seguir...",
                JOptionPane.YES_NO_OPTION);
        
        if (keepgoingint == JOptionPane.YES_OPTION) {
            System.out.println("Continuando:3");
            
        } else {
            keepgoing = false;
            System.out.println("Bye");
        }
    } else {
        keepgoing = false;
        JOptionPane.showMessageDialog(null, "Bye");
        System.out.println("Bye");
        System.exit(0);
    }
} while (keepgoing);
        
    }//end main
     
}//end class
