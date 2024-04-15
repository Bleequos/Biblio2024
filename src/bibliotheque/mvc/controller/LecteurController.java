package bibliotheque.mvc.controller;

import bibliotheque.metier.Lecteur;
import bibliotheque.mvc.model.DAOLecteur;
import bibliotheque.mvc.view.AbstractViewLecteur;

import java.util.List;

public class LecteurController {

    protected DAOLecteur model;
    protected AbstractViewLecteur view;

    public LecteurController(DAOLecteur model, AbstractViewLecteur view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Lecteur> getAll(){
        return model.getAll();
    }

    public Lecteur add(Lecteur elt) {
        return model.add(elt);
    }

    public boolean remove(Lecteur elt) {
        return model.remove(elt);
    }

    public Lecteur update(Lecteur elt) {
        return model.update(elt);
    }

    public Lecteur search(Lecteur rech) {
        return model.read(rech);
    }
}