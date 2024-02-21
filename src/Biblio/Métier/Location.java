package Biblio.Métier;

import java.time.LocalDate;

public class Location {
    private LocalDate dateLoc;
    private LocalDate dateRestitution;
    private Double Amande;
    private Exemplaire exemplaire;
    private Lecteur lecteur;
    public Location() {
    }
    public void calculerAmande() {
    }
    public void enregistrerRetour() {
    }

    public Location(LocalDate dateLoc, LocalDate dateRestitution,Lecteur lecteur , Exemplaire exemplaire) {
        this.dateLoc = dateLoc;
        this.dateRestitution = dateRestitution;
        this.exemplaire = exemplaire;
        this.lecteur = lecteur;
    }
}
