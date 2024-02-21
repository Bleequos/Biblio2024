package Biblio.Métier;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Dvd extends Ouvrage{

    private long code;
    private LocalTime dureeTotale;
    private byte nbreBonus;
    private List<String> autresLangues;
    private List<String> sousTitres;

    public Dvd(String titre, int ageMin, LocalDate dateParution, Biblio.Métier.typeOuvrage typeOuvrage, double prixLocation, String langue, String genre) {
        super(titre, ageMin, dateParution, typeOuvrage, prixLocation, langue, genre);
    }

    @Override
    public double amandeRetard(int nbJours) {
        return 0;
    }
}
