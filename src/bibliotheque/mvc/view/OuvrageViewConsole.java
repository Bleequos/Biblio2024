package bibliotheque.mvc.view;

import bibliotheque.metier.Auteur;
import bibliotheque.metier.Exemplaire;
import bibliotheque.metier.Ouvrage;
import bibliotheque.metier.TypeOuvrage;
import bibliotheque.mvc.controller.ControllerSpecialOuvrage;
import bibliotheque.utilitaires.*;

import java.util.*;

import static bibliotheque.utilitaires.Utilitaire.*;


public class OuvrageViewConsole extends AbstractView<Ouvrage> {
    Scanner sc = new Scanner(System.in);


    @Override
    public void menu() {
        update(controller.getAll());
        List options = Arrays.asList("ajouter", "retirer", "rechercher","modifier","fin");
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
                    return;
            }
        } while (true);
    }

    private void retirer() {
        int nl = choixElt(la)-1;
        Ouvrage a = la.get(nl);
        boolean ok = controller.remove(a);
        if(ok) affMsg("ouvrage effacé");
        else affMsg("ouvrage non effacé");
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }


    public void rechercher() {
        System.out.println("Please choose the type of ouvrage to search for:");
        System.out.println("1. Book");
        System.out.println("2. DVD");
        System.out.println("3. CD");
        System.out.print("Enter your choice (1-3): ");
        int choice = sc.nextInt();
        sc.nextLine();  // consume the leftover newline

        String searchTerm;
        switch (choice) {
            case 1:  // Books
                System.out.print("Enter the ISBN of the book: ");
                searchTerm = sc.nextLine();
                searchBooksByISBN(searchTerm);
                break;
            case 2:  // DVDs
                System.out.print("Enter the director of the DVD: ");
                searchTerm = sc.nextLine();
                searchDVDsByDirector(searchTerm);
                break;
            case 3:  // CDs
                System.out.print("Enter the title of the CD: ");
                searchTerm = sc.nextLine();
                searchCDsByTitle(searchTerm);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void searchBooksByISBN(String isbn) {
        // Assume there's a method in the controller that can handle the search
        String isbn1 = isbn;
        List<Ouvrage> results =(isbn);
        if (results.isEmpty()) {
            System.out.println("No books found with ISBN: " + isbn);
        } else {
            for (Ouvrage ouvrage : results) {
                System.out.println(ouvrage);
            }
        }
    }

    private void searchDVDsByDirector(String director) {
        List<Ouvrage> results = controller.(director);
        if (results.isEmpty()) {
            System.out.println("No DVDs found by director: " + director);
        } else {
            for (Ouvrage ouvrage : results) {
                System.out.println(ouvrage);
            }
        }
    }

    private void searchCDsByTitle(String title) {
        List<Ouvrage> results = controller.search(title);
        if (results.isEmpty()) {
            System.out.println("No CDs found with title: " + title);
        } else {
            for (Ouvrage ouvrage : results) {
                System.out.println(ouvrage);
            }
        }
    }



    public void modifier() {
        int choix = choixElt(la);
        Ouvrage a = la.get(choix-1);
         do {
            try {
                double ploc =Double.parseDouble(modifyIfNotBlank("prix location",""+a.getPrixLocation()));
                a.setPrixLocation(ploc);
                break;
            } catch (Exception e) {
                System.out.println("erreur :" + e);
            }
        }while(true);
        controller.update(a);
   }


    public void ajouter() {
        TypeOuvrage[] tto = TypeOuvrage.values();
        List<TypeOuvrage> lto = new ArrayList<>(Arrays.asList(tto));
        int choix = Utilitaire.choixListe(lto);
        if (choix <= 0 || choix > lto.size()) {
            System.out.println("Invalid choice, returning to menu.");
            return;
        }

        List<OuvrageFactory> lof = Arrays.asList(new LivreFactory(), new CDFactory(), new DVDFactory());
        Ouvrage a = lof.get(choix - 1).create();


        List<Auteur> availableAuthors = new ArrayList<>(controller.getAllAuthors()); // Assume this method exists
        availableAuthors.removeIf(author -> a.getAuteurs().contains(author));

        availableAuthors.sort(Comparator.comparing(Auteur::getNom).thenComparing(Auteur::getPrenom));

        System.out.println("Choose one or more authors for the ouvrage. Enter indices separated by commas:");
        int index = 1;
        for (Auteur author : availableAuthors) {
            System.out.println(index++ + ". "


            protected void special() {
        int choix =  choixElt(la);
        Ouvrage o = la.get(choix-1);

        List options = new ArrayList<>(Arrays.asList("lister exemplaires", "lister exemplaires en location", "lister exemplaires libres","fin"));
        do {
            int ch = choixListe(options);

            switch (ch) {

                case 1:
                    exemplaires(o);
                    break;
                case 2:
                    enLocation(o, true);
                    break;
                case 3:
                    enLocation(o, false);
                    break;

                case 4 :return;
            }
        } while (true);

    }

    public void enLocation(Ouvrage o, boolean enLocation) {
        List<Exemplaire> l= ((ControllerSpecialOuvrage) controller).listerExemplaire(o, enLocation);
        affList(l);
    }

    public void exemplaires(Ouvrage o) {
        List<Exemplaire> l= ((ControllerSpecialOuvrage)controller).listerExemplaire(o);
        affList(l);
    }
    @Override
    public void affList(List la) {
        affListe(la);
    }
            private <__TMP__> __TMP__ exemplaires() {
            }
}

