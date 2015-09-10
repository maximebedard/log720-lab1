package ca.etsmtl.log720.lab1;

import org.omg.PortableServer.POA;

import java.util.ArrayList;

public class CollectionReactions extends CollectionReactionPOA {
    private ArrayList<Reaction> _reactions = new ArrayList<Reaction>();

    public Reaction getReaction(int index) {

        try {
            Reaction reaction = _reactions.get(index);
            POA rootpoa = Serveur._poa;
            org.omg.CORBA.Object obj = rootpoa.servant_to_reference(reaction);
            return ReactionHelper.narrow(obj);
        } catch (Exception e) {
            System.err.println(String.format("Erreur lors du retour du dossier: %s", e));
            return null;
        }
    }

    public int size() {
        return _reactions.size();
    }
}
