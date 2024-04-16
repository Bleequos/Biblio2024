package bibliotheque.mvc.model;

import bibliotheque.metier.Exemplaire;

import java.util.ArrayList;
import java.util.List;

public class ExemplaireModel extends DAOExemplaire {

    private List<Exemplaire> ldatas = new ArrayList<>();

    @Override
    public Exemplaire add(Exemplaire elt) {
        boolean present = ldatas.contains(elt);
        if (!present) {
            ldatas.add(elt);
            notifyObservers();
            return elt;
        } else return null;
    }

    @Override
    public boolean remove(Exemplaire elt) {
        boolean ok = ldatas.remove(elt);
        notifyObservers();
        return ok;
    }

    @Override
    public Exemplaire update(Exemplaire elt) {
        int p = ldatas.indexOf(elt);
        if (p < 0) return null;
        ldatas.set(p, elt);
        notifyObservers();
        return elt;
    }

    @Override
    public Exemplaire read(Exemplaire rech) {
        int p = ldatas.indexOf(rech);
        if(p<0) return null;
        return ldatas.get(p);
    }

    @Override
    public List<Exemplaire> getAll() {
        return ldatas;
    }
}