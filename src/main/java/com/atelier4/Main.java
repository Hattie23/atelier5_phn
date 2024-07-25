package com.atelier4;
import java.util.Map;
import java.util.Scanner;

import com.atelier4.classes.Alimentaire;
import com.atelier4.classes.Electronique;
import com.atelier4.classes.Magasin;
import com.atelier4.classes.Produit;
import com.atelier4.classes.Vestimentaire;

public class Main {
    public static void main(String[] args) {
        Magasin magasin = new Magasin();
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("Menu:");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Supprimer un produit");
            System.out.println("3. Modifier un produit");
            System.out.println("4. Rechercher un produit par nom");
            System.out.println("5. Lister les produits par lettre");
            System.out.println("6. Afficher le nombre de produits en stock");
            System.out.println("7. Afficher la valeur totale du stock");
            System.out.println("8. Rechercher un produit par ID");
            System.out.println("0. Quitter");
            System.out.print("Entrez votre choix: ");
            choix = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choix) {
                case 1:
                    System.out.print("Type de produit (Electronique, Alimentaire, Vestimentaire): ");
                    String type = scanner.nextLine();
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nom: ");
                    String nom = scanner.nextLine();
                    System.out.print("Prix: ");
                    double prix = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline

                    if (type.equalsIgnoreCase("Electronique")) {
                        System.out.print("Garantie: ");
                        int garantie = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        Electronique electronique = new Electronique(id, nom, prix, garantie);
                        magasin.ajouterProduit(electronique);
                    } else if (type.equalsIgnoreCase("Alimentaire")) {
                        System.out.print("Date d'expiration: ");
                        String dateExpiration = scanner.nextLine();
                        Alimentaire alimentaire = new Alimentaire(id, nom, prix, dateExpiration);
                        magasin.ajouterProduit(alimentaire);
                    } else if (type.equalsIgnoreCase("Vestimentaire")) {
                        System.out.print("Taille: ");
                        String taille = scanner.nextLine();
                        Vestimentaire vestimentaire = new Vestimentaire(id, nom, prix, taille);
                        magasin.ajouterProduit(vestimentaire);
                    } else {
                        System.out.println("Type de produit inconnu.");
                    }
                    break;

                case 2:
                    System.out.print("ID du produit à supprimer: ");
                    id = scanner.nextLine();
                    magasin.supprimerProduit(id);
                    break;

                case 3:
                    System.out.print("ID du produit à modifier: ");
                    id = scanner.nextLine();
                    System.out.print("Nouveau nom: ");
                    nom = scanner.nextLine();
                    System.out.print("Nouveau prix: ");
                    prix = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    magasin.modifierProduit(id, nom, prix);
                    break;

                case 4:
                    System.out.print("Nom du produit à rechercher: ");
                    nom = scanner.nextLine();
                    Produit p = magasin.rechercherProduitParNom(nom);
                    if (p != null) {
                        System.out.println("Produit trouvé: " + p.getNom() + ", Prix: " + p.getPrix());
                    } else {
                        System.out.println("Produit non trouvé.");
                    }
                    break;

                case 5:
                    System.out.print("Lettre pour lister les produits: ");
                    char lettre = scanner.nextLine().charAt(0);
                    Map<String, Produit> produits = magasin.listerProduitsParLettre(lettre);
                    for (Produit produit : produits.values()) {
                        System.out.println(produit.getNom() + ", Prix: " + produit.getPrix());
                    }
                    break;

                case 6:
                    System.out.println("Nombre de produits en stock: " + magasin.nombreProduitsEnStock());
                    break;

                case 7:
                    System.out.println("Valeur totale du stock: " + magasin.calculerValeurTotaleStock());
                    break;

                case 8:
                    System.out.print("ID du produit à rechercher: ");
                    id = scanner.nextLine();
                    Produit produit = magasin.rechercherProduitParId(id);
                    if (produit != null) {
                        System.out.println("Produit trouvé: " + produit.getNom() + ", Prix: " + produit.getPrix());
                    } else {
                        System.out.println("Produit non trouvé.");
                    }
                    break;

                case 0:
                    System.out.println("Au revoir!");
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        } while (choix != 0);

        scanner.close();
    }
}
