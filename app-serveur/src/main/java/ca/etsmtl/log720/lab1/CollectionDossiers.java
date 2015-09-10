package ca.etsmtl.log720.lab1;

import java.util.ArrayList;

public class CollectionDossiers extends CollectionDossierPOA {
    private ArrayList<Dossier> _dossiers = new ArrayList<Dossier>();

    public Dossier getDossier(int index) {
        return _dossiers.get(index);
    }

    public int size() {
        return _dossiers.size();
    }
}
