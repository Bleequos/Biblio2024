package bibliotheque.mvc.view;

import bibliotheque.metier.Ouvrage;
import bibliotheque.mvc.controller.OuvrageController;
import bibliotheque.mvc.observer.Observer;

import java.util.List;

public abstract class AbstractViewOuvrage implements Observer {

    protected OuvrageController ouvrageController;
    protected List<Ouvrage> lo;

    public void setController(OuvrageController ouvrageController) {
        this.ouvrageController = ouvrageController;
    }

    public abstract void menu();

    public abstract void affList(List lo);

    public List<Ouvrage> getAll(){
        return lo;
    }

    @Override
    public void update(List lo) {
        this.lo = lo;
        affList(lo);
    }
}