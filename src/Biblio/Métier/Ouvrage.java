package Biblio.Métier;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public abstract class Ouvrage {
    public List<Auteur> getLauteurs() {
        return auteurs;
    }

    public Ouvrage(String titre, int ageMin, LocalDate dateParution, Biblio.Métier.typeOuvrage typeOuvrage, double prixLocation, String langue, String genre) {
        this.titre = titre;
        this.ageMin = ageMin;
        this.dateParution = dateParution;
        this.typeOuvrage = typeOuvrage;
        this.prixLocation = prixLocation;
        this.langue = langue;
        this.genre = genre;
    }

    protected String titre;
    protected int ageMin;
    protected LocalDate dateParution;
    protected typeOuvrage typeOuvrage;
    protected double prixLocation;
    protected String langue;
    protected String genre;
    protected List<Auteur> auteurs;
    protected List<Exemplaire> exemplaires;
    public void listerExemplaires() {
    }
    public void listerExemplaires(boolean enLocation) {
    }
    public abstract double amandeRetard(int nbJours);
}
