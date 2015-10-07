package ca.etsmtl.log720.lab1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BanqueReactionsImpl extends BanqueReactionsPOA {
    private CollectionReactionImpl _reactions = new CollectionReactionImpl();

    public CollectionReaction reactions() {
        return RemoteObjectHelper.WithError(_reactions, CollectionReactionHelper::narrow);
    }

    public void ajouterReaction(String reaction, int gravite) {
        _reactions.add(new ReactionImpl(reaction, gravite));
    }

    public CollectionReaction trouverReactionsParDossier(Dossier myDossier) {
        List<Integer> reactionIds = IntStream.of(myDossier.getListeReaction()).boxed().collect(Collectors.toList());

        return RemoteObjectHelper.WithError(new CollectionReactionImpl(
                        _reactions.trouverReactionsPar((r) -> reactionIds.contains(r.id()))),
                CollectionReactionHelper::narrow);
    }

    public Reaction trouverReactionParId(int idReaction) {
        return RemoteObjectHelper.WithError(trouverParId(idReaction), ReactionHelper::narrow);
    }

    public ReactionImpl trouverParId(int idReaction) {
        return _reactions.trouverReactionPar(r -> r.id() == idReaction);
    }
}
