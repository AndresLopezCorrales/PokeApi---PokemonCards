package com.mycompany.pokemonapiproyectofinal;

import javax.swing.JLabel;

public class VentanaViewPokeCard extends javax.swing.JFrame {
   
    public VentanaViewPokeCard() {
        initComponents();
        
    }
    
    //met Poder de attaque
    public void setPowerValue(String power) {
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            lbPower.setText(power);
            repaint();
        });
        
    }
    
    //met descripcion pkm
    public void setDescPkm(String descPkm) {
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            lbDescPkm.setText(descPkm);
            repaint();
        });
        
    }
    
    //met descripcion de ataque
    public void setDescAtaque(String descAttk) {
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            lbDescAtaque.setText(descAttk);
            repaint();
        });
        
    }
    
    //met genera - especie pkm
    public void setGenus(String genus) {
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            lbGenera.setText(genus);
            repaint();
        });
        
    }
    
    //met nombre pkm
    public void setNombrePkm(String namePkm) {
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            lbNamePkm1.setText("");
            lbNamePkm1.setText(namePkm);
            repaint();
            
        });
        
    }
    
    //met IDHeightWeightPs pkm
    public void setIDHeightWeightPs(String id, String height, String weight, String ps) {
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            lbIdPokedex.setText(id);
            lbHeight.setText(height);
            lbWeight.setText(weight);
            lbPs.setText(ps);
            System.out.println(id +", "+height +", "+weight +", "+ps);
            repaint();
        });
        
    }
    
    //Met nombre de ataque pkm
    public void setMove(String move) {
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            lbNameAtaque.setText(move);
            repaint();
        });
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        lbFotoPokemon = new javax.swing.JLabel();
        lbColorCardPoke = new javax.swing.JLabel();
        lbCONSTANTEPS = new javax.swing.JLabel();
        CONSTANTEWeight = new javax.swing.JLabel();
        lbPs = new javax.swing.JLabel();
        lbNamePkm1 = new javax.swing.JLabel();
        CONSTANTENumeroPokedex1 = new javax.swing.JLabel();
        lbIdPokedex = new javax.swing.JLabel();
        lbGenera = new javax.swing.JLabel();
        lbWeight = new javax.swing.JLabel();
        CONSTANTEHeight = new javax.swing.JLabel();
        lbHeight = new javax.swing.JLabel();
        lbPower = new javax.swing.JLabel();
        lbDescPkm = new javax.swing.JLabel();
        lbNameAtaque = new javax.swing.JLabel();
        lbDescAtaque = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(500, 500));

        lbFotoPokemon.setText("foto");
        jLayeredPane1.setLayer(lbFotoPokemon, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(lbFotoPokemon);
        lbFotoPokemon.setBounds(40, 60, 340, 230);
        jLayeredPane1.add(lbColorCardPoke);
        lbColorCardPoke.setBounds(0, 0, 420, 590);

        lbCONSTANTEPS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCONSTANTEPS.setForeground(new java.awt.Color(0, 0, 0));
        lbCONSTANTEPS.setText("PS");
        jLayeredPane1.setLayer(lbCONSTANTEPS, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(lbCONSTANTEPS);
        lbCONSTANTEPS.setBounds(310, 40, 20, 16);

        CONSTANTEWeight.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        CONSTANTEWeight.setForeground(new java.awt.Color(0, 0, 0));
        CONSTANTEWeight.setText("Peso:");
        jLayeredPane1.setLayer(CONSTANTEWeight, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(CONSTANTEWeight);
        CONSTANTEWeight.setBounds(290, 290, 30, 20);

        lbPs.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbPs.setForeground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.setLayer(lbPs, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(lbPs);
        lbPs.setBounds(330, 20, 50, 40);

        lbNamePkm1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbNamePkm1.setForeground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.setLayer(lbNamePkm1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(lbNamePkm1);
        lbNamePkm1.setBounds(100, 20, 180, 30);

        CONSTANTENumeroPokedex1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        CONSTANTENumeroPokedex1.setForeground(new java.awt.Color(0, 0, 0));
        CONSTANTENumeroPokedex1.setText("N.°");
        jLayeredPane1.setLayer(CONSTANTENumeroPokedex1, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(CONSTANTENumeroPokedex1);
        CONSTANTENumeroPokedex1.setBounds(60, 290, 13, 20);

        lbIdPokedex.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbIdPokedex.setForeground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.setLayer(lbIdPokedex, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(lbIdPokedex);
        lbIdPokedex.setBounds(80, 290, 30, 20);

        lbGenera.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbGenera.setForeground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.setLayer(lbGenera, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(lbGenera);
        lbGenera.setBounds(110, 290, 110, 20);

        lbWeight.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbWeight.setForeground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.setLayer(lbWeight, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(lbWeight);
        lbWeight.setBounds(320, 290, 40, 20);

        CONSTANTEHeight.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        CONSTANTEHeight.setForeground(new java.awt.Color(0, 0, 0));
        CONSTANTEHeight.setText("Altura:");
        jLayeredPane1.setLayer(CONSTANTEHeight, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(CONSTANTEHeight);
        CONSTANTEHeight.setBounds(220, 290, 30, 20);

        lbHeight.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbHeight.setForeground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.setLayer(lbHeight, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(lbHeight);
        lbHeight.setBounds(250, 290, 40, 20);

        lbPower.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbPower.setForeground(new java.awt.Color(0, 0, 0));
        lbPower.setText("0");
        jLayeredPane1.setLayer(lbPower, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(lbPower);
        lbPower.setBounds(340, 340, 60, 40);

        lbDescPkm.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        lbDescPkm.setForeground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.setLayer(lbDescPkm, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(lbDescPkm);
        lbDescPkm.setBounds(220, 480, 160, 80);

        lbNameAtaque.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbNameAtaque.setForeground(new java.awt.Color(0, 0, 0));
        lbNameAtaque.setText(" ");
        jLayeredPane1.setLayer(lbNameAtaque, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(lbNameAtaque);
        lbNameAtaque.setBounds(30, 340, 310, 40);

        lbDescAtaque.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDescAtaque.setForeground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.setLayer(lbDescAtaque, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(lbDescAtaque);
        lbDescAtaque.setBounds(30, 360, 360, 110);

        getContentPane().add(jLayeredPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CONSTANTEHeight;
    private javax.swing.JLabel CONSTANTENumeroPokedex1;
    private javax.swing.JLabel CONSTANTEWeight;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel lbCONSTANTEPS;
    private javax.swing.JLabel lbColorCardPoke;
    private javax.swing.JLabel lbDescAtaque;
    private javax.swing.JLabel lbDescPkm;
    private javax.swing.JLabel lbFotoPokemon;
    private javax.swing.JLabel lbGenera;
    private javax.swing.JLabel lbHeight;
    private javax.swing.JLabel lbIdPokedex;
    private javax.swing.JLabel lbNameAtaque;
    private javax.swing.JLabel lbNamePkm1;
    private javax.swing.JLabel lbPower;
    private javax.swing.JLabel lbPs;
    private javax.swing.JLabel lbWeight;
    // End of variables declaration//GEN-END:variables

    //Setters y Getters de los JLabels
    public JLabel getLbFotoPokemon() {
        return lbFotoPokemon;
    }

    public void setLbFotoPokemon(JLabel lbFotoPokemon) {
        this.lbFotoPokemon = lbFotoPokemon;
    }

    public JLabel getLbColorCardPoke() {
        return lbColorCardPoke;
    }

    public void setLbColorCardPoke(JLabel lbColorCardPoke) {
        this.lbColorCardPoke = lbColorCardPoke;
    }

    public JLabel getLbDescAtaque() {
        return lbDescAtaque;
    }

    public void setLbDescAtaque(JLabel lbDescAtaque) {
        this.lbDescAtaque = lbDescAtaque;
    }

    public JLabel getLbDescPkm() {
        return lbDescPkm;
    }

    public void setLbDescPkm(JLabel lbDescPkm) {
        this.lbDescPkm = lbDescPkm;
    }

    public JLabel getLbGenera() {
        return lbGenera;
    }

    public void setLbGenera(JLabel lbGenera) {
        this.lbGenera = lbGenera;
    }

    public JLabel getLbHeight() {
        return lbHeight;
    }

    public void setLbHeight(JLabel lbHeight) {
        this.lbHeight = lbHeight;
    }

    public JLabel getLbIdPokedex() {
        return lbIdPokedex;
    }

    public void setLbIdPokedex(JLabel lbIdPokedex) {
        this.lbIdPokedex = lbIdPokedex;
    }

    public JLabel getLbNameAtaque() {
        return lbNameAtaque;
    }

    public void setLbNameAtaque(JLabel lbNameAtaque) {
        this.lbNameAtaque = lbNameAtaque;
    }

    public JLabel getLbNamePkm1() {
        return lbNamePkm1;
    }

    public void setLbNamePkm1(JLabel lbNamePkm1) {
        this.lbNamePkm1 = lbNamePkm1;
    }

    public JLabel getLbPower() {
        return lbPower;
    }

    public void setLbPower(JLabel lbPower) {
        this.lbPower = lbPower;
    }

    public JLabel getLbPs() {
        return lbPs;
    }

    public void setLbPs(JLabel lbPs) {
        this.lbPs = lbPs;
    }

    public JLabel getLbWeight() {
        return lbWeight;
    }

    public void setLbWeight(JLabel lbWeight) {
        this.lbWeight = lbWeight;
    }

    public JLabel getLbCONSTANTEPS() {
        return lbCONSTANTEPS;
    }

    public void setLbCONSTANTEPS(JLabel lbCONSTANTEPS) {
        this.lbCONSTANTEPS = lbCONSTANTEPS;
    }

}
