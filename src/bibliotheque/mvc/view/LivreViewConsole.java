package bibliotheque.mvc.view;

import bibliotheque.metier.Livre;
import bibliotheque.metier.TypeLivre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static bibliotheque.utilitaires.Utilitaire.*;

public class LivreViewConsole extends AbstractViewLivre {
    Scanner sc = new Scanner(System.in);

    @Override
    public void menu() {
        update(livreController.getAll());
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
        Livre l = ll.get(nl);
        boolean ok = livreController.remove(l);
        if(ok) affMsg("livre effacé");
        else affMsg("livre non effacé");
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }

    public void rechercher() {
        try {
            System.out.println("ISBN ");
            String isbn = sc.nextLine();
            Livre rech = new Livre();
            rech.setIsbn(isbn);
            Livre l = livreController.search(rech);
            if(l==null) affMsg("livre inconnu");
            else affMsg(l.toString());
        }catch(Exception e){
            System.out.println("erreur : "+e);
        }
    }

    public void modifier() {
        int choix = choixElt(ll);
        Livre l = ll.get(choix-1);
        do {
            try {
                String isbn = modifyIfNotBlank("ISBN", l.getIsbn());
                l.setIsbn(isbn);
                break;
            } catch (Exception e) {
                System.out.println("erreur :" + e);
            }
        }while(true);
        livreController.update(l);
    }

    public void ajouter() {
        Livre l;
        do {
            try {
                System.out.println("ISBN ");
                String isbn = sc.nextLine();
                l = new Livre(isbn);
                l.setIsbn(isbn);
                break;
            } catch (Exception e) {
                System.out.println("une erreur est survenue : "+e.getMessage());
            }
        }while(true);
        livreController.add(l);
    }


    @Override
    public void affList(List ll) {
        affListe(ll);
    }
}