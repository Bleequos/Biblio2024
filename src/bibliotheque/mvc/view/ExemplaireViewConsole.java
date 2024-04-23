package bibliotheque.mvc.view;

import bibliotheque.metier.*;
import bibliotheque.mvc.GestionMVC;
import bibliotheque.mvc.controller.ControllerSpecialExemplaire;
import java.util.*;
import static bibliotheque.utilitaires.Utilitaire.*;

public class ExemplaireViewConsole extends AbstractView<Exemplaire> {

    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        update(controller.getAll());
        List<String> options = Arrays.asList("ajouter", "retirer", "rechercher", "modifier", "fin");
        do {
            int ch = choixListe(options);
            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    retirer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    return;  // Exits the loop and returns to the main menu
            }
        } while (true);
    }

    private void retirer() {
        if (la.isEmpty()) {
            affMsg("Aucun exemplaire à retirer.");
            return;
        }
        int nl = choixElt(la) - 1;
        Exemplaire a = la.get(nl);
        boolean ok = controller.remove(a);
        affMsg(ok ? "Exemplaire effacé" : "Exemplaire non effacé");
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    public void rechercher() {
        try {
            System.out.println("Entrez le matricule de l'exemplaire :");
            String mat = sc.nextLine();
            Exemplaire rech = new Exemplaire(mat, "", null);
            Exemplaire a = controller.search(rech);
            affMsg(a == null ? "Exemplaire inconnu" : a.toString());
            if (a != null) {
                special(a);
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }

    public void modifier() {
        if (la.isEmpty()) {
            affMsg("Aucun exemplaire disponible pour modification.");
            return;
        }
        int choix = choixElt(la) - 1;
        Exemplaire a = la.get(choix);
        try {
            String description = modifyIfNotBlank("Entrez la nouvelle description de l'état (laisser vide pour ne pas changer) :", a.getDescriptionEtat());
            a.setDescriptionEtat(description);
            controller.update(a);
            affMsg("Exemplaire modifié.");
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }
    }

    public void ajouter() {
        try {
            System.out.println("Matricule :");
            String mat = sc.nextLine();
            System.out.println("Description :");
            String descr = sc.nextLine();
            System.out.println("Ouvrage :");
            List<Ouvrage> lo = GestionMVC.ov.getAll();
            lo.sort(Comparator.comparing(Ouvrage::getTitre));
            affListe(lo);
            int ch = choixListe(lo);
            Ouvrage ouvrageChoisi = lo.get(ch - 1);

            System.out.println("Rayon :");
            List<Rayon> lr = GestionMVC.rv.getAll();
            lr.sort(Comparator.comparing(Rayon::getCodeRayon));
            affListe(lr);
            ch = choixListe(lr);
            Rayon rayonChoisi = lr.get(ch - 1);

            Exemplaire a = new Exemplaire(mat, descr, ouvrageChoisi);
            a.setRayon(rayonChoisi);
            controller.add(a);
            affMsg("Exemplaire ajouté avec succès.");
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
        }
    }

    private void special(Exemplaire a) {
        List<String> options = Arrays.asList("modifier etat", "lecteur actuel", "envoi mail", "en location", "louer", "rendre", "fin");
        do {
            int ch = choixListe(options);
            switch (ch) {
                case 1:
                    Etat(a);
                    break;
                case 2:
                    lecteurActuel(a);
                    break;
                case 3:
                    envoiMail(a);
                    break;
                case 4:
                    enLocation(a);
                    break;
                case 5:
                    louer(a);
                    break;
                case 6:
                    rendre(a);
                    break;
                case 7:
                    return;  // Return to main menu
            }
        } while (true);
    }

    private void rendre(Exemplaire a) {
        if (GestionMVC.LOCATIONS.remove(a) != null) {
            affMsg("Exemplaire rendu.");
        } else {
            affMsg("Cet exemplaire n'était pas loué.");
        }
    }

    private void louer(Exemplaire a) {
        List<Object> lecteurs = GestionMVC.lecteur.getAll();
        if (lecteurs.isEmpty()) {
            System.out.println("Aucun lecteur disponible pour la location.");
            return;
        }

        System.out.println("Choisir un lecteur :");
        for (int i = 0; i < lecteurs.size(); i++) {
            System.out.println((i + 1) + ". " + lecteurs.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt();


        if (choix < 1 || choix > lecteurs.size()) {
            System.out.println("Choix invalide.");
            return;
        }

        Lecteur lecteurChoisi = (Lecteur) lecteurs.get(choix - 1);

        System.out.println("Exemplaire " + a + " loué à " + lecteurChoisi.getNom());
    }

        List<Lecteur> lecteurs = GestionMVC.lv.getAll();
        if (lecteurs.isEmpty()) {
            affMsg("Aucun lecteur disponible.");
            return;
        }

        affMsg("Choisissez un lecteur pour la location :");
        for (int i = 0; i < lecteurs.size(); i++) {
            System.out.println((i + 1) + ". " + lecteurs.get(i).getNom() + " " + lecteurs.get(i).getPrenom());
        }

        int choix = Integer.parseInt(sc.nextLine());
        if (choix < 1 || choix > lecteurs.size()) {
            affMsg("Choix invalide.");
            return;
        }

        Lecteur lecteurChoisi = lecteurs.get(choix - 1);
        GestionMVC.LOCATIONS.put(a, lecteurChoisi);
        affMsg("L'exemplaire est loué à : " + lecteurChoisi.getNom() + " " + lecteurChoisi.getPrenom());
    }

    public void enLocation(Exemplaire ex) {
        boolean loc = ((ControllerSpecialExemplaire)controller).enLocation(ex);
        affMsg(loc ? "En location" : "Pas en location");
    }

    public void envoiMail(Exemplaire ex) {
        Mail m = new Mail("demo@example.com", "Message de test", "01-01-2024");
        ((ControllerSpecialExemplaire)controller).envoiMailLecteurActuel(ex, m);
    }

    public void lecteurActuel(Exemplaire ex) {
        Lecteur currentLecteur = ((ControllerSpecialExemplaire




