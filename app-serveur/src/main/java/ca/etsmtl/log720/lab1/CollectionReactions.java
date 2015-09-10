package ca.etsmtl.log720.lab1;

import java.util.ArrayList;

public class CollectionReactions extends CollectionReactionPOA {
    private ArrayList<Reaction> _reactions = new ArrayList<Reaction>();

    public Reaction getReaction(int index) {
        return _reactions.get(index);
    }

    public int size() {
        return _reactions.size();
    }
}
