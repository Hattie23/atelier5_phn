package com.atelier4.classes;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import com.atelier4.database.DatabaseConnection;

public class Magasin {
    private Map<String, Produit> produits;
    private Connection conn;

    public Magasin() {
        produits = new HashMap<>();
        conn = DatabaseConnection.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Produits (id VARCHAR(50), nom VARCHAR(50), prix DOUBLE, type VARCHAR(50), specifice VARCHAR(50))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterProduit(Produit p) {
        produits.put(p.getId(), p);
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Produits VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, p.getId());
            ps.setString(2, p.getNom());
            ps.setDouble(3, p.getPrix());
            ps.setString(4, p.getClass().getSimpleName());
            if (p instanceof Electronique) {
                ps.setString(5, String.valueOf(((Electronique) p).getGarantie()));
            } else if (p instanceof Alimentaire) {
                ps.setString(5, ((Alimentaire) p).getDateExpiration());
            } else if (p instanceof Vestimentaire) {
                ps.setString(5, ((Vestimentaire) p).getTaille());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerProduit(String id) {
        produits.remove(id);
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Produits WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierProduit(String id, String nom, double prix) {
        Produit p = produits.get(id);
        if (p != null) {
            p.setNom(nom);
            p.setPrix(prix);
            try {
                PreparedStatement ps = conn.prepareStatement("UPDATE Produits SET nom = ?, prix = ? WHERE id = ?");
                ps.setString(1, nom);
                ps.setDouble(2, prix);
                ps.setString(3, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Produit rechercherProduitParNom(String nom) {
        for (Produit p : produits.values()) {
            if (p.getNom().equalsIgnoreCase(nom)) {
                return p;
            }
        }
        return null;
    }

    public Map<String, Produit> listerProduitsParLettre(char lettre) {
        Map<String, Produit> result = new HashMap<>();
        for (Produit p : produits.values()) {
            if (p.getNom().charAt(0) == lettre) {
                result.put(p.getId(), p);
            }
        }
        return result;
    }

    public int nombreProduitsEnStock() {
        return produits.size();
    }

    public Map<String, Produit> listerProduitsParType(String type) {
        Map<String, Produit> result = new HashMap<>();
        for (Produit p : produits.values()) {
            if (p.getClass().getSimpleName().equalsIgnoreCase(type)) {
                result.put(p.getId(), p);
            }
        }
        return result;
    }

    public double calculerValeurTotaleStock() {
        double total = 0;
        for (Produit p : produits.values()) {
            total += p.getPrix();
        }
        return total;
    }

    public Produit rechercherProduitParId(String id) {
        return produits.get(id);
    }
}
