package Biblio.Métier;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cd extends Ouvrage {
    private long code;
    private byte nbrePlages;
    private LocalTime dureeTotale;

    public Cd(String titre, int ageMin, LocalDate dateParution, Biblio.Métier.typeOuvrage typeOuvrage, double prixLocation, String langue, String genre) {
        super(titre, ageMin, dateParution, typeOuvrage, prixLocation, langue, genre);
    }


    @Override
    public double amandeRetard(int nbJours) {
        return 0;
    }
}
