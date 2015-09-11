package ca.etsmtl.log720.lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionReactionImpl extends CollectionReactionPOA {
    private ArrayList<ReactionImpl> _reactions = new ArrayList<ReactionImpl>();

    public Reaction getReaction(int index) {
        return RemoteObjectHelper.WithError(_reactions.get(index), ReactionHelper::narrow);
    }

    public int size() {
        return _reactions.size();
    }

    public List<ReactionImpl> trouverInfractionsPar(Predicate<ReactionImpl> predicate) {
        return _reactions.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public ReactionImpl trouverInfractionPar(Predicate<ReactionImpl> predicate) {
        return _reactions.stream()
                .filter(predicate)
                .findFirst().orElse(null);
    }
}
