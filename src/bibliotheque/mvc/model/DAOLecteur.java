package bibliotheque.mvc.model;

import bibliotheque.metier.Lecteur;
import bibliotheque.metier.Ouvrage;
import bibliotheque.mvc.observer.Subject;
import java.util.List;
import java.util.Set;

public abstract class DAOLecteur extends Subject {

    public abstract Set<Ouvrage> listerOuvrages(Lecteur l);
    public abstract Lecteur add(Lecteur elt);

    public abstract boolean remove(Lecteur elt);

    public abstract Lecteur update(Lecteur elt);

    public abstract Lecteur read(Lecteur rech);

    public abstract List<Lecteur> getAll();

    public List<Lecteur> getNotification(){
        return getAll();
    }
}