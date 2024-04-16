package bibliotheque.mvc.model;

import bibliotheque.metier.Livre;
import bibliotheque.metier.TypeLivre;

import java.util.ArrayList;
import java.util.List;

public class LivreModel extends DAOLivre {

    private List<Livre> ldatas = new ArrayList<>();

    @Override
    public Livre add(Livre elt) {
        boolean present = ldatas.contains(elt);
        if (!present) {
            ldatas.add(elt);
            notifyObservers();
            return elt;
        } else return null;
    }

    @Override
    public boolean remove(Livre elt) {
        boolean ok = ldatas.remove(elt);
        notifyObservers();
        return ok;
    }

    @Override
    public Livre update(Livre elt) {
        int p = ldatas.indexOf(elt);
        if (p < 0) return null;
        ldatas.set(p, elt);
        notifyObservers();
        return elt;
    }

    @Override
    public Livre read(Livre rech) {
        int p = ldatas.indexOf(rech);
        if(p<0) return null;
        return ldatas.get(p);
    }

    @Override
    public List<Livre> getAll() {
        return ldatas;
    }

    @Override
    public List<Livre> listerLivre(TypeLivre tl) {
        List<Livre> result = new ArrayList<>();
        for (Livre livre : ldatas) {
            if (livre.getIsbn().equals(tl)) {
                result.add(livre);
            }
        }
        return result;
    }
}