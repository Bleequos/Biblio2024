package bibliotheque.mvc.view;

import bibliotheque.metier.Exemplaire;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static bibliotheque.utilitaires.Utilitaire.*;

public class ExemplaireViewConsole extends AbstractViewExemplaire {
    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        update(exemplaireController.getAll());
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
        int nl = choixElt(le)-1;
        Exemplaire e = le.get(nl);
        boolean ok = exemplaireController.remove(e);
        if(ok) affMsg("exemplaire effacé");
        else affMsg("exemplaire non effacé");
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    public void rechercher() {
        try {
            System.out.println("matricule ");
            String matricule = sc.nextLine();
            Exemplaire rech = new Exemplaire(matricule, null, null);
            Exemplaire e = exemplaireController.search(rech);
            if(e==null) affMsg("exemplaire inconnu");
            else {
                affMsg(e.toString());
            }
        }catch(Exception e){
            System.out.println("erreur : "+e);
        }
    }

    public void modifier() {
        int choix = choixElt(le);
        Exemplaire e = le.get(choix-1);
        do {
            try {
                String matricule = modifyIfNotBlank("matricule", e.getMatricule());
                String descriptionEtat = modifyIfNotBlank("descriptionEtat", e.getDescriptionEtat());
                e.setMatricule(matricule);
                e.setDescriptionEtat(descriptionEtat);
                break;
            } catch (Exception ex) {
                System.out.println("erreur :" + ex);
            }
        }while(true);
        exemplaireController.update(e);
    }

    public void ajouter() {
        Exemplaire e;
        do {
            try {
                System.out.println("matricule ");
                String matricule = sc.nextLine();
                System.out.println("descriptionEtat ");
                String descriptionEtat = sc.nextLine();
                e = new Exemplaire(matricule, descriptionEtat, null);
                break;
            } catch (Exception ex) {
                System.out.println("une erreur est survenue : "+ex.getMessage());
            }
        }while(true);
        exemplaireController.add(e);
    }

    @Override
    public void affList(List le) {
        affListe(le);
    }
}