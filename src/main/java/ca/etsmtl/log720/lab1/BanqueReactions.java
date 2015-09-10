package ca.etsmtl.log720.lab1;

public interface BanqueReactions {
    void ajouterReaction(String description, int niveau);
    CollectionReactions trouverReactionsParDossier(Dossier dossier);
    Reaction trouverReactionParId(int id);
    CollectionReactions reactions();
}
