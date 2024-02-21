package Biblio.Métier;

import java.time.LocalDate;
import java.util.List;

public class Lecteur {
    private long numLecteur;
    private String nom;
    private String prenom;
    private LocalDate dateNaiss;
    private String mail;
    private String adresse;
    private String tel;
    private List<Location> locations;
    public Lecteur() {
    }

    public List<Location> getLloc() {
        return locations;
    }

    public Lecteur( String nom, String prenom, LocalDate dateNaiss, String adresse , String mail, String tel) {
        this.numLecteur = numLecteur;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.mail = mail;
        this.adresse = adresse;
        this.tel = tel;
        this.locations = locations;
    }

    public void listerExemplairesEnLocation() {
    }
    public void listerExemplairsLoues() {
    }
}
