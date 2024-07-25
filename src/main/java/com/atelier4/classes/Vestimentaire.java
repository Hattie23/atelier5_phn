package com.atelier4.classes;

public class Vestimentaire extends Produit {
    private String taille;

    public Vestimentaire(String id, String nom, double prix, String taille) {
        super(id, nom, prix);
        this.taille = taille;
    }

    public String getTaille() { return taille; }
    public void setTaille(String taille) { this.taille = taille; }
}
