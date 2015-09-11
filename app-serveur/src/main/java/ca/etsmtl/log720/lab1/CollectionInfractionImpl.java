package ca.etsmtl.log720.lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionInfractionImpl extends CollectionInfractionPOA {
    private ArrayList<InfractionImpl> _infractions = new ArrayList<InfractionImpl>();

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
        return _infractions.stream().mapToInt(InfractionImpl::id).toArray();
    }

    public void add(InfractionImpl infraction) {
        _infractions.add(infraction);
    }
}
