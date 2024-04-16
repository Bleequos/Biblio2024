package bibliotheque.mvc.view;

import bibliotheque.metier.Rayon;
import bibliotheque.mvc.controller.RayonController;
import bibliotheque.mvc.observer.Observer;

import java.util.List;

public abstract class AbstractViewRayon implements Observer {

    protected RayonController rayonController;
    protected List<Rayon> lr;

    public void setController(RayonController rayonController) {
        this.rayonController = rayonController;
    }

    public abstract void menu();

    public abstract void affList(List lr);

    public List<Rayon> getAll(){
        return lr;
    }

    @Override
    public void update(List lr) {
        this.lr = lr;
        affList(lr);
    }
}