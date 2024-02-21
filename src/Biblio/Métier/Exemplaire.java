package Biblio.Métier;

import java.util.List;

public class Exemplaire {
    private String Matricule;
    private String descriptionEtat;
    private Ouvrage ouvrage;
    private List<Location> locations;

    private Rayon rayon;

    public List<Location> getLloc() {
        return locations;
    }

    public void setRayon(Rayon rayon) {
        this.rayon = rayon;
    }

    public Rayon getRayon() {
        return rayon;
    }

    public Exemplaire() {
    }

    public Exemplaire(String matricule, String descriptionEtat, Ouvrage ouvrage) {
        Matricule = matricule;
        this.descriptionEtat = descriptionEtat;
        this.ouvrage = ouvrage;
    }

    public void modifierEtat(String etat) {
    }
    public void lecteurActuel() {
    }
    public void lecteur() {
    }
    public void envoiMailLecteurActuel(Mail mail) {
    }
    public void envoiMailLecteur(Mail mail) {
    }
    public Boolean enRetard() {
        return null;
    }
    public int joursRetard() {
        return 0;
    }
    public Boolean enLocation() {
        return null;
    }
}
