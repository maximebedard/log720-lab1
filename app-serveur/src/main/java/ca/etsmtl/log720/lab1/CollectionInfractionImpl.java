package ca.etsmtl.log720.lab1;

import java.util.ArrayList;

public class CollectionInfractionImpl extends CollectionInfractionPOA {
    private ArrayList<InfractionImpl> _infractions = new ArrayList<InfractionImpl>();

    public Infraction getInfraction(int index) {
        try {
            InfractionImpl infraction = _infractions.get(index);
            org.omg.CORBA.Object obj = Serveur._poa.servant_to_reference(infraction);
            return InfractionHelper.narrow(obj);
        } catch (Exception e) {
            System.err.println(String.format("Erreur lors du retour de l'infraction: %s", e));
            return null;
        }
    }

    public int size() {
        return _infractions.size();
    }
}
