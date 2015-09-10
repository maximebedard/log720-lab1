package ca.etsmtl.log720.lab1;

import java.util.ArrayList;

public class CollectionDossiers extends CollectionDossierPOA {
    private ArrayList<Dossier> _dossiers = new ArrayList<Dossier>();

    public Dossier getDossier(int index) {
        try {
            Dossier dossier = _dossiers.get(index);
            org.omg.CORBA.Object obj = Serveur._poa.servant_to_reference(dossier);
            return DossierHelper.narrow(obj);
        } catch (Exception e) {
            System.err.println(String.format("Erreur lors du retour du dossier: %s", e));
            return null;
        }
    }

    public int size() {
        return _dossiers.size();
    }
}
