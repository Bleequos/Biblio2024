package bibliotheque.utilitaires;

import bibliotheque.metier.DVD;
import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Ouvrage;
import bibliotheque.mvc.GestionMVC;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static bibliotheque.utilitaires.Utilitaire.affListe;
import static bibliotheque.utilitaires.Utilitaire.choixListe;

public abstract class DVDFactory extends OuvrageFactory{
    protected long code;
    protected LocalTime dureeTotale;
    protected byte nbreBonus;

    public void ajouter() {
        Exemplaire a;
        do {
            try {
                System.out.println("Matricule :");
                String mat = sc.nextLine();
                if (mat.isEmpty()) {
                    System.out.println("Erreur : Le matricule ne peut être vide.");
                    continue;  // Recommence la boucle si le matricule est vide
                }

                System.out.println("Description :");
                String descr = sc.nextLine();
                if (descr.isEmpty()) {
                    System.out.println("Erreur : La description ne peut être vide.");
                    continue;  // Recommence la boucle si la description est vide
                }

                System.out.println("Ouvrage :");
                List<Ouvrage> lo = GestionMVC.ov.getAll();  // Assume getAll() récupère tous les ouvrages
                // Trie les ouvrages par titre
                lo.sort(Comparator.comparing(Ouvrage::getTitre));
                affListe(lo);

                int choixOuvrage = choixListe(lo);
                if (choixOuvrage <= 0 || choixOuvrage > lo.size()) {
                    System.out.println("Choix invalide.");
                    continue;  // Recommence la boucle si le choix est invalide
                }
                Ouvrage ouvrageChoisi = lo.get(choixOuvrage - 1);

                System.out.println("Rayon :");
                List<Rayon> lr = GestionMVC.rv.getAll();  // Assume getAll() récupère tous les rayons
                // Trie les rayons par code
                lr.sort(Comparator.comparing(Rayon::getCodeRayon));
                affListe(lr);

                int choixRayon = choixListe(lr);
                if (choixRayon <= 0 || choixRayon > lr.size()) {
                    System.out.println("Choix invalide.");
                    continue;  // Recommence la boucle si le choix est invalide
                }
                Rayon rayonChoisi = lr.get(choixRayon - 1);

                a = new Exemplaire(mat, descr, ouvrageChoisi);
                a.setRayon(rayonChoisi);
                controller.add(a);
                System.out.println("Exemplaire ajouté avec succès.");
                break;  // Sort de la boucle après un ajout réussi
            } catch (Exception e) {
                System.out.println("Une erreur est survenue : " + e.getMessage());
                continue;  // En cas d'erreur, permet de recommencer la saisie
            }
        } while (true);
    }

}
