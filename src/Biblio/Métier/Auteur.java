package Biblio.Métier;

import java.util.List;

public class Auteur {
    private String nom;
    private String prenom;
    private String Nationalite;
    private List<Ouvrage> ouvrages;
    public Auteur() {
    }

    public List<Ouvrage> getLouvrage() {
        return ouvrages;
    }

    public Auteur(String nom, String prenom, String nationalite) {
        this.nom = nom;
        this.prenom = prenom;
        Nationalite = nationalite;
    }

    public void listerOuvrages() {
    }
    public void listerOuvrages(typeOuvrage type,typeLivre livre) {
    }
    public void listerOuvrages(String genre) {
    }
}
