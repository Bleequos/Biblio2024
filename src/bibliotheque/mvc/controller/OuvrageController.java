package bibliotheque.mvc.controller;

import bibliotheque.metier.Auteur;
import bibliotheque.metier.Ouvrage;
import bibliotheque.mvc.model.DAOOuvrage;
import bibliotheque.mvc.view.AbstractViewOuvrage;

import java.util.List;
import java.util.Set;

public class OuvrageController {

    protected DAOOuvrage model;
    protected AbstractViewOuvrage view;

    public OuvrageController(DAOOuvrage model, AbstractViewOuvrage view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Ouvrage> getAll(){
        List<Ouvrage> l = model.getAll();
        return l;
    }

    public Ouvrage add(Ouvrage elt) {
        Ouvrage nelt = model.add(elt);
        return nelt;
    }

    public boolean remove(Ouvrage elt) {
        return model.remove(elt);
    }

    public Ouvrage update(Ouvrage elt) {
        return model.update(elt);
    }

    public Ouvrage search(Ouvrage rech) {
        return model.read(rech);
    }

    public Set<Auteur> listerAuteurs(Ouvrage o) {
        return model.listerAuteurs(o);
    }
}