package bibliotheque.mvc.view;

import bibliotheque.metier.Lecteur;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static bibliotheque.utilitaires.Utilitaire.*;

public class LecteurViewConsole extends AbstractViewLecteur {
    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        update(lecteurController.getAll());
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
        int nl = choixElt(ll)-1;
        Lecteur l = ll.get(nl);
        boolean ok = lecteurController.remove(l);
        if(ok) affMsg("lecteur effacé");
        else affMsg("lecteur non effacé");
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    public void rechercher() {
        try {
            System.out.println("nom ");
            String nom = sc.nextLine();
            Lecteur rech = lecteurController.search(new Lecteur(0, nom, null, null, null, null, null));
            Lecteur l = lecteurController.search(rech);
            if(l==null) affMsg("lecteur inconnu");
            else {
                affMsg(l.toString());
            }
        }catch(Exception e){
            System.out.println("erreur : "+e);
        }
    }

    public void modifier() {
        int choix = choixElt(ll);
        Lecteur l = ll.get(choix-1);
        do {
            try {
                String nom = modifyIfNotBlank("nom", l.getNom());
                l.setNom(nom);
                break;
            } catch (Exception e) {
                System.out.println("erreur :" + e);
            }
        }while(true);
        lecteurController.update(l);
    }

    public void ajouter() {
        Lecteur l;
        do {
            try {
                System.out.println("nom ");
                String nom = sc.nextLine();
                System.out.println("prenom ");
                String prenom = sc.nextLine();
                System.out.println("date de naissance (yyyy-mm-dd) ");
                LocalDate dn = LocalDate.parse(sc.nextLine());
                System.out.println("adresse ");
                String adresse = sc.nextLine();
                System.out.println("mail ");
                String mail = sc.nextLine();
                System.out.println("tel ");
                String tel = sc.nextLine();
                l = new Lecteur(0, nom, prenom, dn, adresse, mail, tel);
                break;
            } catch (Exception e) {
                System.out.println("une erreur est survenue : "+e.getMessage());
            }
        }while(true);
        lecteurController.add(l);
    }

    @Override
    public void affList(List ll) {
        affListe(ll);
    }
}