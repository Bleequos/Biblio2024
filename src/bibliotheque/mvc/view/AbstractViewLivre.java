package bibliotheque.mvc.view;

import bibliotheque.metier.Livre;
import bibliotheque.mvc.controller.LivreController;
import bibliotheque.mvc.observer.Observer;

import java.util.List;

public abstract class AbstractViewLivre implements Observer {

    protected LivreController livreController;
    protected List<Livre> ll;

    public void setController(LivreController livreController) {
        this.livreController = livreController;
    }

    public abstract void menu();

    public abstract void affList(List ll);

    public List<Livre> getAll(){
        return ll;
    }

    @Override
    public void update(List ll) {
        this.ll = ll;
        affList(ll);
    }
}