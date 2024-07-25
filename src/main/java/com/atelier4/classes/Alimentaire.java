package com.atelier4.classes;

public class Alimentaire extends Produit {
    private String dateExpiration;

    public Alimentaire(String id, String nom, double prix, String dateExpiration) {
        super(id, nom, prix);
        this.dateExpiration = dateExpiration;
    }

    public String getDateExpiration() { return dateExpiration; }
    public void setDateExpiration(String dateExpiration) { this.dateExpiration = dateExpiration; }
}
