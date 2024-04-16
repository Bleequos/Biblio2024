package bibliotheque.mvc.view;

import bibliotheque.metier.Rayon;
import bibliotheque.metier.Ouvrage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static bibliotheque.utilitaires.Utilitaire.*;

public class RayonViewConsole extends AbstractViewRayon {
    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        update(rayonController.getAll());
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
        int nl = choixElt(lr)-1;
        Rayon r = lr.get(nl);
        boolean ok = rayonController.remove(r);
        if(ok) affMsg("rayon effacé");
        else affMsg("rayon non effacé");
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    public void rechercher() {
        try {
            System.out.println("code rayon ");
            String coderayon = sc.nextLine();
            Rayon rech = new Rayon(coderayon);
            Rayon r = rayonController.search(rech);
            if(r==null) affMsg("rayon inconnu");
            else {
                affMsg(r.toString());
            }
        }catch(Exception e){
            System.out.println("erreur : "+e);
        }
    }

    public void modifier() {
        int choix = choixElt(lr);
        Rayon r = lr.get(choix-1);
        do {
            try {
                String CodeRayon = modifyIfNotBlank("Code rayon", r.getCodeRayon());
                r.setCodeRayon(CodeRayon);
                break;
            } catch (Exception e) {
                System.out.println("erreur :" + e);
            }
        }while(true);
        rayonController.update(r);
    }

    public void ajouter() {
        Rayon r;
        do {
            try {
                System.out.println("nom ");
                String nom = sc.nextLine();
                r = new Rayon(nom);
                break;
            } catch (Exception e) {
                System.out.println("une erreur est survenue : "+e.getMessage());
            }
        }while(true);
        rayonController.add(r);
    }

    @Override
    public void affList(List lr) {
        affListe(lr);
    }
}