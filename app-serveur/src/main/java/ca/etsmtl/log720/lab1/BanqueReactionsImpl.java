package ca.etsmtl.log720.lab1;

public class BanqueReactionsImpl extends BanqueReactionsPOA {
    private CollectionReactionImpl _reactions = new CollectionReactionImpl();

    public CollectionReaction reactions() {
        return null;
    }

    public void ajouterReaction(String reaction, int gravite) {

    }

    public CollectionReaction trouverReactionsParDossier(Dossier myDossier) {
        return null;
    }

    public Reaction trouverReactionParId(int idReaction) {
        return null;
    }

    public ReactionImpl trouverParId(int idReaction) {
        return _reactions.trouverInfractionPar(r -> r.id() == idReaction);
    }
}
