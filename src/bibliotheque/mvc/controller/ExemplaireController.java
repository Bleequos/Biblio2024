package bibliotheque.mvc.controller;

import bibliotheque.metier.Exemplaire;
import bibliotheque.mvc.model.DAOExemplaire;
import bibliotheque.mvc.view.AbstractViewExemplaire;

import java.util.List;

public class ExemplaireController {

    protected DAOExemplaire model;
    protected AbstractViewExemplaire view;

    public ExemplaireController(DAOExemplaire model, AbstractViewExemplaire view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Exemplaire> getAll(){
        List<Exemplaire> l = model.getAll();
        return l;
    }

    public Exemplaire add(Exemplaire elt) {
        Exemplaire nelt = model.add(elt);
        return nelt;
    }

    public boolean remove(Exemplaire elt) {
        return model.remove(elt);
    }

    public Exemplaire update(Exemplaire elt) {
        return model.update(elt);
    }

    public Exemplaire search(Exemplaire rech) {
        return model.read(rech);
    }
}