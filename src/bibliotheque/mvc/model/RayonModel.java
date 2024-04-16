package bibliotheque.mvc.model;

import bibliotheque.metier.Rayon;
import bibliotheque.metier.Ouvrage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RayonModel extends DAORayon {

    private List<Rayon> rdatas = new ArrayList<>();

    @Override
    public Rayon add(Rayon elt) {
        boolean present = rdatas.contains(elt);
        if (!present) {
            rdatas.add(elt);
            notifyObservers();
            return elt;
        } else return null;
    }

    @Override
    public boolean remove(Rayon elt) {
        boolean ok = rdatas.remove(elt);
        notifyObservers();
        return ok;
    }

    @Override
    public Rayon update(Rayon elt) {
        int p = rdatas.indexOf(elt);
        if (p < 0) return null;
        rdatas.set(p, elt);
        notifyObservers();
        return elt;
    }

    @Override
    public Rayon read(Rayon rech) {
        int p = rdatas.indexOf(rech);
        if(p<0) return null;
        return rdatas.get(p);
    }

    @Override
    public List<Rayon> getAll() {
        return rdatas;
    }

    @Override
    public Set<Ouvrage> listerOuvrages(Rayon r) {
        return r.listerOuvrages();
    }
}