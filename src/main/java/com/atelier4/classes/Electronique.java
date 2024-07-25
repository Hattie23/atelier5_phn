package com.atelier4.classes;
public class Electronique extends Produit {
    private int garantie;

    public Electronique(String id, String nom, double prix, int garantie) {
        super(id, nom, prix);
        this.garantie = garantie;
    }

    public int getGarantie() { return garantie; }
    public void setGarantie(int garantie) { this.garantie = garantie; }
}
