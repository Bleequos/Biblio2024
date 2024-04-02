package bibliotheque.gestion;

import bibliotheque.metier.*;
import bibliotheque.utilitaires.CDFactoryBeta;
import bibliotheque.utilitaires.DVDFactoryBeta;
import bibliotheque.utilitaires.LivreFactoryBeta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static bibliotheque.utilitaires.Utilitaire.choixListe;

public class Gestion {
    Scanner sc = new Scanner(System.in);
//on a ôté static pour les listes qui n'est plus nécessaire
    private List<Auteur> laut = new ArrayList<>();
    private List<Lecteur> llect = new ArrayList<>();
    private List<Ouvrage> louv= new ArrayList<>();
    private List<Exemplaire> lex = new ArrayList<>();
    private List<Rayon> lrayon= new ArrayList<>();
    private List<Location> lloc = new ArrayList<>();


    public void populate(){
        Auteur a = new Auteur("Verne","Jules","France");
        laut.add(a);

        Livre l = new Livre("Vingt mille lieues sous les mers",10, LocalDate.of(1880,1,1),1.50,"français","aventure","a125",350,TypeLivre.ROMAN,"histoire de sous-marin");
        louv.add(l);

        a.addOuvrage(l);

        a = new Auteur("Spielberg","Steven","USA");
        laut.add(a);

        DVD d = new DVD("AI",12,LocalDate.of(2000,10,1),2.50,"anglais","SF",4578l,LocalTime.of(2,0,0),(byte)2);
        d.getAutresLangues().add("français");
        d.getAutresLangues().add("italien");
        d.getSousTitres().add("néerlandais");
        louv.add(d);

        a.addOuvrage(d);

         a = new Auteur("Kubrick","Stanley","GB");
        laut.add(a);

        a.addOuvrage(d);


        CD c = new CD("The Compil 2023",0,LocalDate.of(2023,1,1),2,"English","POP",1245,(byte)20,LocalTime.of(1,40,0));
        louv.add(c);

        Rayon r = new Rayon("r12","aventure");
        lrayon.add(r);

        Exemplaire e = new Exemplaire("m12","état neuf",l);
        lex.add(e);
        e.setRayon(r);


        r = new Rayon("r45","science fiction");
        lrayon.add(r);

        e = new Exemplaire("d12","griffé",d);
        lex.add(e);

        e.setRayon(r);


        Lecteur lec = new Lecteur(1,"Dupont","Jean",LocalDate.of(2000,1,4),"Mons","jean.dupont@mail.com","0458774411");
        llect.add(lec);

        Location loc = new Location(LocalDate.of(2023,2,1),LocalDate.of(2023,3,1),lec,e);
        lloc.add(loc);
        loc.setDateRestitution(LocalDate.of(2023,2,4));

        lec = new Lecteur(1,"Durant","Aline",LocalDate.of(1980,10,10),"Binche","aline.durant@mail.com","045874444");
        llect.add(lec);

        loc = new Location(LocalDate.of(2023,2,5),LocalDate.of(2023,3,5),lec,e);
        lloc.add(loc);
    }

    private void menu() {
        List options = new ArrayList<>(Arrays.asList("auteurs","ouvrages","exemplaires","rayons","lecteurs","locations","restitution","fin"));
      do{
        int choix = choixListe(options);

            switch (choix){
                case 1 :gestAuteurs(); break;
                case 2 : gestOuvrages();break;
                case 3 : gestExemplaires();break;
                case 4 : gestRayons();break;
                case 5 : gestLecteurs();break;
                case 6 : gestLocations();break;
                case 7 : gestRestitution();break;
                default:System.exit(0);
            }
        }  while (true);
    }

    private void gestRestitution() {
        List<Exemplaire> exemplairesEnLocation = new ArrayList<>();
        for (Location loc : lloc) {
            if (loc.getDateRestitution() == null) {
                exemplairesEnLocation.add(loc.getExemplaire());
            }
        }
        System.out.println("Exemplaires en location :");
        for (int i = 0; i < exemplairesEnLocation.size(); i++) {
            Exemplaire ex = exemplairesEnLocation.get(i);
            System.out.println((i + 1) + ". " + ex.getMatricule() + " - " + ex.getOuvrage().getTitre());
        }
        int choix = choixListe(exemplairesEnLocation);
        Exemplaire exemplaireARestituer = exemplairesEnLocation.get(choix - 1);
        exemplaireARestituer.setLloc(null);
        System.out.println("Voulez-vous changer l'état de cet exemplaire ? (o/n)");
        String reponse = sc.next();
        if (reponse.equalsIgnoreCase("o")) {
            System.out.println("Nouvel état : ");
            String nouvelEtat = sc.next();
            exemplaireARestituer.modifierEtat(nouvelEtat);
            System.out.println("État de l'exemplaire changé avec succès.");
        }

    }

    private void gestLocations() {
        List<Exemplaire> exemplairesLibres = new ArrayList<>();
        for (Exemplaire ex : lex) {
            if (!ex.enLocation()) {
                exemplairesLibres.add(ex);
            }
        }
        exemplairesLibres.sort(Comparator.comparing(Exemplaire::getMatricule));
        System.out.println("Exemplaires disponibles :");
        for (int i = 0; i < exemplairesLibres.size(); i++) {
            Exemplaire ex = exemplairesLibres.get(i);
            System.out.println((i + 1) + ". Matricule : " + ex.getMatricule() + " - Ouvrage : " + ex.getOuvrage().getTitre());
        }
        int choix = choixListe(exemplairesLibres);
        Exemplaire exemplaireChoisi = exemplairesLibres.get(choix - 1);
        choix = choixListe(llect);
        Lecteur lecteurChoisi = llect.get(choix - 1);
        lloc.add(new Location(lecteurChoisi, exemplaireChoisi));
    }

    private void gestLecteurs() {
        System.out.println("numéro");
        int num=sc.nextInt();
        sc.skip("\n");
        System.out.println("nom ");
        String nom=sc.nextLine();
        System.out.println("prénom ");
        String prenom=sc.nextLine();
        System.out.println("date de naissance");
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        LocalDate dn= LocalDate.of(a,m,j);
        System.out.println("adresse");
        String adr=sc.nextLine();
        System.out.println("mail");
        String mail=sc.nextLine();
        System.out.println("tel ");
        String tel=sc.nextLine();
        Lecteur lect = new Lecteur(num,nom,prenom,dn,adr,mail,tel);
        llect.add(lect);
        System.out.println("lecteur créé");

    }

    private void gestRayons() {
        System.out.println("Rayons disponibles :");
        for (int i = 0; i < lrayon.size(); i++) {
            Rayon r = lrayon.get(i);
            System.out.println((i + 1) + ". Code : " + r.getCodeRayon() + " - Genre : " + r.getGenre());
        }
        int choixRayon = choixListe(lrayon);
        Rayon rayonChoisi = lrayon.get(choixRayon - 1);
        List<Exemplaire> exemplairesDisponibles = new ArrayList<>();
        for (Exemplaire ex : lex) {
            if (!ex.enLocation() && !ex.enLocation()) {
                exemplairesDisponibles.add(ex);
            }
        }
        exemplairesDisponibles.sort(Comparator.comparing(ex -> ex.getOuvrage().getTitre()));
        System.out.println("Exemplaires disponibles pour l'attribution au rayon :");
        for (int i = 0; i < exemplairesDisponibles.size(); i++) {
            Exemplaire ex = exemplairesDisponibles.get(i);
            System.out.println((i + 1) + ". Matricule : " + ex.getMatricule() + " - Titre : " + ex.getOuvrage().getTitre());
        }
        System.out.println("Sélectionnez les exemplaires à attribuer (0 pour arrêter) :");
        List<Exemplaire> exemplairesAttribues = new ArrayList<>();
        while (true) {
            int choixExemplaire = choixListe(exemplairesDisponibles);
            if (choixExemplaire == 0) {
                break;
            }
            Exemplaire exemplaireChoisi = exemplairesDisponibles.get(choixExemplaire - 1);
            exemplairesAttribues.add(exemplaireChoisi);
            exemplaireChoisi.setRayon(rayonChoisi);
            System.out.println("Exemplaire attribué au rayon : " + rayonChoisi.getCodeRayon());
            exemplairesDisponibles.remove(exemplaireChoisi);
        }
        System.out.println("Exemplaires attribués au rayon :");
        for (Exemplaire ex : exemplairesAttribues) {
            System.out.println("- Matricule : " + ex.getMatricule() + " - Titre : " + ex.getOuvrage().getTitre());
        }

    }

    private void gestExemplaires() {
        TypeOuvrage[] types = TypeOuvrage.values();
        List<TypeOuvrage> typesList = Arrays.asList(types);
        int choixType = choixListe(typesList);
        Ouvrage ouvrage = null;
        switch (choixType) {
            case 1:
                ouvrage = new LivreFactoryBeta().create();
                break;
            case 2:
                ouvrage = new CDFactoryBeta().create();
                break;
            case 3:
                ouvrage = new DVDFactoryBeta().create();
                break;
        }
        louv.add(ouvrage);
        System.out.println("Ouvrage créé");
        System.out.println("Attribuer des auteurs à l'ouvrage :");
        for (Auteur auteur : laut) {
            System.out.println((laut.indexOf(auteur) + 1) + ". " + auteur.getNom() + " " + auteur.getPrenom());
        }
        List<Auteur> auteursDisponibles = new ArrayList<>(laut);
        auteursDisponibles.sort(Comparator.comparing(Auteur::getNom).thenComparing(Auteur::getPrenom));
        for (Auteur auteur : ouvrage.getLauteurs()) {
            auteursDisponibles.remove(auteur);
        }
        List<Auteur> auteursAttribues = new ArrayList<>();
        System.out.println("Sélectionnez les auteurs à attribuer à l'ouvrage (0 pour terminer) :");
        int choixAuteur;
        do {
            for (Auteur auteur : auteursDisponibles) {
                System.out.println((auteursDisponibles.indexOf(auteur) + 1) + ". " + auteur.getNom() + " " + auteur.getPrenom());
            }
            choixAuteur = sc.nextInt();
            if (choixAuteur > 0 && choixAuteur <= auteursDisponibles.size()) {
                Auteur auteurSelectionne = auteursDisponibles.get(choixAuteur - 1);
                ouvrage.addAuteur(auteurSelectionne);
                auteursAttribues.add(auteurSelectionne);
                System.out.println("Auteur ajouté à l'ouvrage : " + auteurSelectionne.getNom() + " " + auteurSelectionne.getPrenom());
            }
        } while (choixAuteur != 0);
    }

    private void gestOuvrages() {
        TypeOuvrage[] tto = TypeOuvrage.values();
        List<TypeOuvrage> lto = new ArrayList<>(Arrays.asList(tto));
        int choix = choixListe(lto);
        Ouvrage o = null;
        switch(choix) {
            case 1 : o = new LivreFactoryBeta().create();break;
            case 2 : o = new CDFactoryBeta().create();break;
            case 3 : o = new DVDFactoryBeta().create();break;
        }
        louv.add(o);
        System.out.println("ouvrage créé");
        System.out.println("Attribution des auteurs :");
        List<Auteur> auteursDisponibles = new ArrayList<>(laut);
        auteursDisponibles.sort(Comparator.comparing(Auteur::getNom).thenComparing(Auteur::getPrenom));
        for (int i = 0; i < auteursDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + auteursDisponibles.get(i).getNom() + " " + auteursDisponibles.get(i).getPrenom());
        }
        Scanner scanner = new Scanner(System.in);
        int choixAuteur;
        do {
            System.out.print("Sélectionnez un auteur (0 pour terminer) : ");
            choixAuteur = scanner.nextInt();
            if (choixAuteur > 0 && choixAuteur <= auteursDisponibles.size()) {
                Auteur auteurSelectionne = auteursDisponibles.get(choixAuteur - 1);
                if (!o.getLauteurs().contains(auteurSelectionne)) {
                    o.addAuteur(auteurSelectionne);
                    System.out.println("Auteur ajouté : " + auteurSelectionne.getNom() + " " + auteurSelectionne.getPrenom());
                } else {
                    System.out.println("Cet auteur est déjà attribué à l'ouvrage !");
                }
            } else if (choixAuteur != 0) {
                System.out.println("Choix invalide !");
            }
        } while (choixAuteur != 0);
    }


    private void gestAuteurs() {
        System.out.println("nom ");
        String nom=sc.nextLine();
        System.out.println("prénom ");
        String prenom=sc.nextLine();
        System.out.println("nationalité");
        String nat=sc.nextLine();
        Auteur a  = new Auteur(nom,prenom,nat);
        laut.add(a);
        System.out.println("écrivain créé");
        System.out.println("Attribution des ouvrages :");
        List<Ouvrage> ouvragesDisponibles = new ArrayList<>(louv);
        ouvragesDisponibles.sort(Comparator.comparing(Ouvrage::getTitre));
        for (int i = 0; i < ouvragesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + ouvragesDisponibles.get(i).getTitre());
        }
        Scanner scanner = new Scanner(System.in);
        int choixOuvrage;
        do {
            System.out.print("Sélectionnez un ouvrage (0 pour terminer) : ");
            choixOuvrage = scanner.nextInt();
            if (choixOuvrage > 0 && choixOuvrage <= ouvragesDisponibles.size()) {
                Ouvrage ouvrageSelectionne = ouvragesDisponibles.get(choixOuvrage - 1);
                if (!a.getLouvrage().contains(ouvrageSelectionne)) {
                    a.addOuvrage(ouvrageSelectionne);
                    System.out.println("Ouvrage ajouté : " + ouvrageSelectionne.getTitre());
                } else {
                    System.out.println("Cet ouvrage est déjà attribué à cet auteur !");
                }
            } else if (choixOuvrage != 0) {
                System.out.println("Choix invalide !");
            }
        } while (choixOuvrage != 0);
    }


    public static void main(String[] args) {
        Gestion g = new Gestion();
        g.populate();
        g.menu();
    }

  
}
