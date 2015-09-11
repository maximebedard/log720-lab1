package ca.etsmtl.log720.lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionDossierImpl extends CollectionDossierPOA {
    private ArrayList<DossierImpl> _dossiers;

    public CollectionDossierImpl() {
        _dossiers = new ArrayList<DossierImpl>();
    }

    public CollectionDossierImpl(List<DossierImpl> dossiers) {
        _dossiers = new ArrayList<DossierImpl>(dossiers);
    }

    public Dossier getDossier(int index) {
        return RemoteObjectHelper.WithError(_dossiers.get(index), DossierHelper::narrow);
    }

    public int size() {
        return _dossiers.size();
    }

    public void add(DossierImpl dossier) {
        _dossiers.add(dossier);
    }

    public List<DossierImpl> trouverDossiersPar(Predicate<DossierImpl> predicate) {
        return _dossiers.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public DossierImpl trouverDossierPar(Predicate<DossierImpl> predicate) {
        return _dossiers.stream()
                .filter(predicate)
                .findFirst().orElse(null);
    }
}
