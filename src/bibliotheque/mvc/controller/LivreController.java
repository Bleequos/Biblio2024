package bibliotheque.mvc.controller;

import bibliotheque.metier.Livre;
import bibliotheque.metier.TypeLivre;
import bibliotheque.mvc.model.DAOLivre;
import bibliotheque.mvc.view.AbstractViewLivre;

import java.util.List;

public class LivreController {

    protected DAOLivre model;
    protected AbstractViewLivre view;

    public LivreController(DAOLivre model, AbstractViewLivre view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public List<Livre> getAll(){
        return model.getAll();
    }

    public Livre add(Livre elt) {
        return model.add(elt);
    }

    public boolean remove(Livre elt) {
        return model.remove(elt);
    }

    public Livre update(Livre elt) {
        return model.update(elt);
    }

    public Livre search(Livre rech) {
        return model.read(rech);
    }

    public List<Livre> listerLivre(TypeLivre tl) {
        return model.listerLivre(tl);
    }
}