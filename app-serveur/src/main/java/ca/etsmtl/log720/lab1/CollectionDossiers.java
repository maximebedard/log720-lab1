package ca.etsmtl.log720.lab1;

import org.omg.PortableServer.POA;

import java.util.ArrayList;

public class CollectionDossiers extends CollectionDossierPOA {
    private ArrayList<Dossier> _dossiers = new ArrayList<Dossier>();

    public Dossier getDossier(int index) {
        try {
            Dossier dossier = _dossiers.get(index);
            POA rootpoa = Serveur._poa;
            org.omg.CORBA.Object obj = rootpoa.servant_to_reference(dossier);
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
