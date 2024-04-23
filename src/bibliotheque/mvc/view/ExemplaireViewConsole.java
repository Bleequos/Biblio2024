package bibliotheque.mvc.view;

import bibliotheque.metier.*;
import bibliotheque.mvc.GestionMVC;
import bibliotheque.mvc.controller.ControllerSpecialExemplaire;

import java.util.*;


import static bibliotheque.utilitaires.Utilitaire.*;
import static bibliotheque.utilitaires.Utilitaire.affListe;

public class ExemplaireViewConsole extends AbstractView<Exemplaire> {

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
        Exemplaire a = la.get(nl);
        boolean ok = controller.remove(a);
        if(ok) affMsg("exemplaire effacé");
        else affMsg("exemplaire non effacé");
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }


    public void rechercher() {
        try {
            System.out.println("matricule ");
            String mat = sc.nextLine();
            Exemplaire rech = new Exemplaire(mat,"",null);
            Exemplaire a = controller.search(rech);
            if(a==null) affMsg("exemplaire inconnu");
            else {
                affMsg(a.toString());
                special(a);
            }
        }catch(Exception e){
            System.out.println("erreur : "+e);
        }

    }


    public void modifier() {
        int choix = choixElt(la);
        Exemplaire a = la.get(choix-1);
        do {
            try {
                String description = modifyIfNotBlank("nom", a.getDescriptionEtat());
                a.setDescriptionEtat(description);
                break;
            } catch (Exception e) {
                System.out.println("erreur :" + e);
            }
        }while(true);
        controller.update(a);
    }


    public void ajouter() {
        Exemplaire a;
        do {
            try {
                System.out.println("matricule ");
                String mat = sc.nextLine();
                System.out.println("description ");
                String descr = sc.nextLine();
                System.out.println("ouvrage : ");
                List<Ouvrage> lo = GestionMVC.ov.getAll();

                //TODO présenter les ouvrages par ordre de titre ==> classe anonyme

               lo.sort(new Comparator<Ouvrage>() {
                   @Override
                   public int compare(Ouvrage o1, Ouvrage o2) {
                       return o1.getTitre().compareTo(o2.getTitre());
                   }
               });
                lo.sort(Comparator.comparing(Ouvrage::getTitre));
                affListe(lo);
                lo.sort(Comparator.comparing(Rayon::getCodeRayon));
                affListe(lo);



                int ch = choixListe(lo);
                a = new Exemplaire(mat, descr,lo.get(ch-1));
                System.out.println("rayon");
                List<Rayon> lr = GestionMVC.rv.getAll();

                lr.sort(new Comparator<Rayon>() {
                    @Override
                    public int compare(Rayon o1, Rayon o2) {
                        return o1.getCodeRayon().compareTo(o2.getCodeRayon());
                    }
                });
                //TODO présenter les rayons par ordre de code ==> classe anonyme


                lr.sort(Comparator.comparing(Rayon::getCodeRayon));
                affListe(lr);
                if (mat.isEmpty() || descr.isEmpty()) {
                    System.out.println("Erreur : Le matricule et la description ne peuvent être vides.");
                    return;
                }


                ch= choixListe(lr);
                a.setRayon(lr.get(ch-1));
                break;
            } catch (Exception e) {
                System.out.println("une erreur est survenue : "+e.getMessage());
            }
        }while(true);
        controller.add(a);
    }

    public void special(Exemplaire a) {

        List options = Arrays.asList("modifier etat", "lecteur actuel", "envoi mail","en location","louer","rendre","fin");
        do {
            int ch = choixListe(options);

            switch (ch) {

                case 1:
                    modifierEtat(a);
                    break;
                case 2:
                    lecteurActuel(a);
                    break;
                case 3:
                    envoiMail(a);
                    break;
                case 4 :
                    enLocation(a);
                    break;
                case 5 :
                    louer(a);
                    break;
                case 6 :
                    rendre(a);
                    break;
                case 7: return;
            }
        } while (true);

    }

    private void rendre(Exemplaire a) {
        GestionMVC.LOCATIONS.remove(a);
   }

    private void louer(Exemplaire a) {
        // Vérifier si l'exemplaire est déjà loué
        if (GestionMVC.LOCATIONS.containsKey(a)) {
            affMsg("Cet exemplaire est déjà loué.");
            return;
        }

        // Afficher la liste des lecteurs disponibles
        List<Lecteur> lecteurs = GestionMVC.lv.getAll();  // Supposons que getAll() récupère tous les lecteurs
        if (lecteurs.isEmpty()) {
            affMsg("Aucun lecteur disponible pour la location.");
            return;
        }

        affMsg("Choisissez un lecteur pour la location:");
        for (int i = 0; i < lecteurs.size(); i++) {
            System.out.println((i + 1) + ". " + lecteurs.get(i).getNom() + " " + lecteurs.get(i).getPrenom());
        }

        // Lire le choix de l'utilisateur
        int choix = sc.nextInt();
        sc.nextLine();  // Consume the remaining newline
        if (choix < 1 || choix > lecteurs.size()) {
            affMsg("Choix invalide.");
            return;
        }

        // Récupérer le lecteur choisi
        Lecteur lecteurChoisi = lecteurs.get(choix - 1);

        // Enregistrer la location
        GestionMVC.LOCATIONS.put(a, lecteurChoisi);
        affMsg("L'exemplaire " + a.getMatricule() + " est maintenant loué à " + lecteurChoisi.getNom() + " " + lecteurChoisi.getPrenom() + ".");
    }



    public void enLocation(Exemplaire ex) {
        boolean loc = ((ControllerSpecialExemplaire)controller).enLocation(ex);
        if(loc) System.out.println("en location");
        else System.out.println("pas en location");
    }


    public void envoiMail(Exemplaire ex) {
        Mail m = new Mail("demo","message de test","01-01-2024");
        ((ControllerSpecialExemplaire)controller).envoiMailLecteurActuel(ex,m);
    }


    public void lecteurActuel(Exemplaire ex) {
        ((ControllerSpecialExemplaire)controller).LecteurActuel(ex);
    }


    public void modifierEtat(Exemplaire ex) {
        System.out.println("nouvel état :");
        String etat = sc.nextLine();
        ((ControllerSpecialExemplaire)controller).modifierEtat(ex,etat) ;
    }

    @Override
    public void affList(List la) {
        affListe(la);
    }
}



