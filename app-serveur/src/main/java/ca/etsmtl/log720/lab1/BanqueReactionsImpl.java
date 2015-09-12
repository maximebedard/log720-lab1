package ca.etsmtl.log720.lab1;

public class BanqueReactionsImpl extends BanqueReactionsPOA {
    private CollectionReactionImpl _reactions = new CollectionReactionImpl();

    public CollectionReaction reactions() {
        return RemoteObjectHelper.WithError(_reactions, CollectionReactionHelper::narrow);
    }

    public void ajouterReaction(String reaction, int gravite) {
        _reactions.add(new ReactionImpl(reaction, gravite));
    }

    public CollectionReaction trouverReactionsParDossier(Dossier myDossier) {
        return RemoteObjectHelper.WithError(_reactions.trouverInfractionPar(r -> r.dossier() != null && r.dossier().id() == myDossier.id()),
                CollectionReactionHelper::narrow);
    }

    public Reaction trouverReactionParId(int idReaction) {
        return RemoteObjectHelper.WithError(trouverParId(idReaction), ReactionHelper::narrow);
    }

    public ReactionImpl trouverParId(int idReaction) {
        return _reactions.trouverInfractionPar(r -> r.id() == idReaction);
    }
}
