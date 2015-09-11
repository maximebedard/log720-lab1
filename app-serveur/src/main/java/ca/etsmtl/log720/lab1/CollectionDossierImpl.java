package ca.etsmtl.log720.lab1;

import java.util.ArrayList;
import java.util.List;
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
        try {
            DossierImpl dossier = _dossiers.get(index);
            org.omg.CORBA.Object obj = Serveur._poa.servant_to_reference(dossier);
            return DossierHelper.narrow(obj);
        } catch (Exception e) {
            System.err.println(String.format("Erreur lors du retour du dossier: %s", e));
            return null;
        }
    }

    public Stream<DossierImpl> stream() {
        return _dossiers.stream();
    }

    public int size() {
        return _dossiers.size();
    }
}
