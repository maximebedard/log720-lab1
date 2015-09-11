package ca.etsmtl.log720.lab1;

import java.util.ArrayList;

public class CollectionReactionImpl extends CollectionReactionPOA {
    private ArrayList<ReactionImpl> _reactions = new ArrayList<ReactionImpl>();

    public Reaction getReaction(int index) {

        try {
            ReactionImpl reaction = _reactions.get(index);
            org.omg.CORBA.Object obj = Serveur._poa.servant_to_reference(reaction);
            return ReactionHelper.narrow(obj);
        } catch (Exception e) {
            System.err.println(String.format("Erreur lors du retour de la reaction: %s", e));
            return null;
        }
    }

    public int size() {
        return _reactions.size();
    }
}
