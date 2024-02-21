package Biblio.Métier;

import java.util.List;

public class Rayon {
    private String codeRayon;
    private String genre;
    private List<Exemplaire> exemplaires;
    public void listerOuvrages() {
    }
    public void listerExemplaires() {
    }

    public List<Exemplaire> getLex() {
        return exemplaires;
    }

    public Rayon(String codeRayon, String genre) {
        this.codeRayon = codeRayon;
        this.genre = genre;
    }

    public String getCodeRayon() {
        return codeRayon;
    }

    public String getGenre() {
        return genre;
    }
}
