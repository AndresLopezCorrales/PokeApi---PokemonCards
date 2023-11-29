package com.mycompany.pokemonapiproyectofinal;

import javax.swing.JOptionPane;

public class SeleccionarPokemon {
    
    Metodos met = new Metodos();
    
    public void seleccionarPoke(){
        
        //JOption como seleccionar pokemon
        Object[] categoriaDeSeleccion = {"Nombre" , "Tipo", "Generacion"};
        
        int categoriaDecision = JOptionPane.showOptionDialog(null,
                "¿Como quieres seleccionar a tu pokemon?",
                "Seleccion de pokemon", 
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.DEFAULT_OPTION,
                null, 
                categoriaDeSeleccion, 
                categoriaDeSeleccion[0]);
        
        switch(categoriaDecision){
            case 0:
                System.out.println("Nombre");
                met.selecPorNombre(); //Por Nombre
                break;
            case 1:
                System.out.println("Tipo");
                met.selecPorTipo(); //Por Tipo
                break;
            case 2:
                System.out.println("Generacion");
                met.selectPorGen(); //Por generacion
                break;
            default:
                JOptionPane.showMessageDialog(null, "Bye");
                System.exit(0);
        }
        
        
    }//end metodo seleccionarPoke
    
}
