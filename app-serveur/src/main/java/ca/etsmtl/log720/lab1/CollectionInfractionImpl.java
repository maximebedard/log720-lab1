package ca.etsmtl.log720.lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionInfractionImpl extends CollectionInfractionPOA {
    private ArrayList<InfractionImpl> _infractions = new ArrayList<InfractionImpl>();

    public CollectionInfractionImpl(List<InfractionImpl> infractions) { _infractions = new ArrayList<>(infractions); }

    public CollectionInfractionImpl() {}

    public Infraction getInfraction(int index) {
        return RemoteObjectHelper.WithError(_infractions.get(index), InfractionHelper::narrow);
    }

    public int size() {
        return _infractions.size();
    }

    public List<InfractionImpl> trouverInfractionsPar(Predicate<InfractionImpl> predicate) {
        return _infractions.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public InfractionImpl trouverInfractionPar(Predicate<InfractionImpl> predicate) {
        return _infractions.stream()
                .filter(predicate)
                .findFirst().orElse(null);
    }

    public int[] ids() {
        return _infractions
                .stream()
                .mapToInt(InfractionImpl::id)
                .toArray();
    }

    public InfractionImpl plusSevere() {
        return _infractions
                .stream()
                .max((i1, i2) -> Integer.compare(i1.niveau(), i2.niveau()))
                .orElse(null);
    }

    public void add(InfractionImpl infraction) {
        _infractions.add(infraction);
    }
}
