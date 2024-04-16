package bibliotheque.mvc.model;

import bibliotheque.metier.Auteur;
import bibliotheque.metier.Ouvrage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OuvrageModel extends DAOOuvrage {

    private List<Ouvrage> ldatas = new ArrayList<>();

    @Override
    public Ouvrage add(Ouvrage elt) {
        boolean present = ldatas.contains(elt);
        if (!present) {
            ldatas.add(elt);
            notifyObservers();
            return elt;
        } else return null;
    }

    @Override
    public boolean remove(Ouvrage elt) {
        boolean ok = ldatas.remove(elt);
        notifyObservers();
        return ok;
    }

    @Override
    public Ouvrage update(Ouvrage elt) {
        int p = ldatas.indexOf(elt);
        if (p < 0) return null;
        ldatas.set(p, elt);
        notifyObservers();
        return elt;
    }

    @Override
    public Ouvrage read(Ouvrage rech) {
        int p = ldatas.indexOf(rech);
        if (p < 0) return null;
        return ldatas.get(p);
    }

    @Override
    public List<Ouvrage> getAll() {
        return ldatas;
    }

    @Override
    public Set<Auteur> listerAuteurs(Ouvrage o) {
        return Set.of();
    }
}
/*
    @Override
    public Set<Auteur> listerAuteurs(Ouvrage o) {
        return o.listerAuteurs();
    }
}

 */