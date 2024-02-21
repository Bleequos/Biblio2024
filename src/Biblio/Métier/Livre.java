package Biblio.Métier;

import java.time.LocalDate;

public class Livre extends Ouvrage {
    private String isbn;
    private int nbPages;
    private typeLivre typeLivre;
    private String resume;

    public Livre(String titre, int ageMin, LocalDate dateParution, Biblio.Métier.typeOuvrage typeOuvrage, double prixLocation, String langue, String genre, String isbn, int nbPages, Biblio.Métier.typeLivre typeLivre, String resume) {
        super(titre, ageMin, dateParution, typeOuvrage, prixLocation, langue, genre);
        this.isbn = isbn;
        this.nbPages = nbPages;
        this.typeLivre = typeLivre;
        this.resume = resume;
    }

    @Override
    public double amandeRetard(int nbJours) {
        return 0;
    }
}
