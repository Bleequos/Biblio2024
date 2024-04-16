package bibliotheque.mvc.view;

import bibliotheque.metier.Ouvrage;
import bibliotheque.mvc.controller.OuvrageController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static bibliotheque.utilitaires.Utilitaire.*;

public class OuvrageViewConsole extends AbstractViewOuvrage {
    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        update(ouvrageController.getAll());
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
        int nl = choixElt(lo)-1;
        Ouvrage o = lo.get(nl);
        boolean ok = ouvrageController.remove(o);
        if(ok) affMsg("ouvrage effacé");
        else affMsg("ouvrage non effacé");
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    public void rechercher() {
        try {
            System.out.println("titre ");
            String titre = sc.nextLine();
            Ouvrage rech = new Ouvrage(titre);
            Ouvrage o = ouvrageController.search(rech);
            if(o==null) affMsg("ouvrage inconnu");
            else {
                affMsg(o.toString());
            }
        }catch(Exception e){
            System.out.println("erreur : "+e);
        }
    }

    public void modifier() {
        int choix = choixElt(lo);
        Ouvrage o = lo.get(choix-1);
        do {
            try {
                String titre = modifyIfNotBlank("titre", o.getTitre());
                o.setTitre(titre);
                break;
            } catch (Exception e) {
                System.out.println("erreur :" + e);
            }
        }while(true);
        ouvrageController.update(o);
    }

    public void ajouter() {
        Ouvrage o;
        do {
            try {
                System.out.println("titre ");
                String titre = sc.nextLine();
                o = new Ouvrage(titre);
                break;
            } catch (Exception e) {
                System.out.println("une erreur est survenue : "+e.getMessage());
            }
        }while(true);
        ouvrageController.add(o);
    }

    @Override
    public void affList(List lo) {
        affListe(lo);
    }
}