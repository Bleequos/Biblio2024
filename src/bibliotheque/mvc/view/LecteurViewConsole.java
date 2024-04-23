package bibliotheque.mvc.view;

import bibliotheque.metier.Lecteur;

import java.time.LocalDate;
import java.util.*;

import static bibliotheque.utilitaires.Utilitaire.*;


public class LecteurViewConsole extends AbstractView<Lecteur> {
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

    @Override
    public void affList(List la) {
        affListe(la);
    }

    private void retirer() {
        int nl = choixElt(la)-1;
        Lecteur l = la.get(nl);
        boolean ok = controller.remove(l);
        if(ok) affMsg("lecteur effacé");
        else affMsg("lecteur non effacé");
    }

    private void affMsg(String msg) {
        System.out.println(msg);
    }


    public void rechercher() {
        try {
            System.out.println("numéro de lecteur :");
            int id = lireInt();
            Lecteur rech = new Lecteur(id,"","",null,"","","");
            Lecteur l = controller.search(rech);
            if(l==null) affMsg("lecteur inconnu");
            else {
                affMsg(l.toString());
             }
        }catch(Exception e){
            System.out.println("erreur : "+e);
        }

    }


    public void modifier() {
        if (la.isEmpty()) {
            System.out.println("No lecteurs available to modify.");
            return;
        }

        int choix = choixElt(la);
        if (choix <= 0 || choix > la.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Lecteur l = la.get(choix - 1);

        try {
            // Updating basic information
            String nom = modifyIfNotBlank("Enter new name (leave blank to keep current): ", l.getNom());
            String prenom = modifyIfNotBlank("Enter new first name (leave blank to keep current): ", l.getPrenom());
            String mail = modifyIfNotBlank("Enter new email (leave blank to keep current): ", l.getMail());

            l.setNom(nom);
            l.setPrenom(prenom);
            l.setMail(mail);


            // Updating the date of birth
            System.out.println("Current date of birth: " + l.getDateNaissance());
            String newDate = modifyIfNotBlank("Enter new date of birth (format: YYYY-MM-DD, leave blank to keep current): ", "");
            if (!newDate.isEmpty()) {
                LocalDate dateNaissance = LocalDate.parse(newDate);
                l.setDateNaissance(dateNaissance);
            }

            // Updating the address
            String adresse = modifyIfNotBlank("Enter new address (leave blank to keep current): ", l.getAdresse());
            l.setAdresse(adresse);

            // Updating the telephone number
            String tel = modifyIfNotBlank("Enter new telephone (leave blank to keep current): ", l.getTelephone());
            l.setTelephone(tel);

            // Committing the changes
            controller.update(l);
            System.out.println("Lecteur information updated successfully.");

        } catch (Exception e) {
            System.out.println("Error updating lecteur: " + e.getMessage());
        }
    }

    private String modifyIfNotBlank(String message, String currentValue) {
        System.out.println(message);
        String input = sc.nextLine();
        return input.isBlank() ? currentValue : input;
    }



    public void ajouter() {
       Lecteur l;
        do {
            try {
                System.out.println("nom ");
                String nom = sc.nextLine();
                System.out.println("prénom ");
                String prenom = sc.nextLine();
                System.out.println("date de naissance :");
                LocalDate dn = lecDate();
                System.out.println("mail :");
                String mail = sc.nextLine();
                System.out.println("adresse :");
                String adr = sc.nextLine();
                System.out.println("tel :");
                String tel = sc.nextLine();
                l = new Lecteur(nom,prenom,dn,adr,mail,tel);
                break;
            } catch (Exception e) {
                System.out.println("une erreur est survenue : "+e.getMessage());
            }
        }while(true);
        l=controller.add(l);
        affMsg("création du lecteur : "+l);
    }

}
