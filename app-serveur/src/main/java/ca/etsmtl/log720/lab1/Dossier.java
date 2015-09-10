package ca.etsmtl.log720.lab1;

public interface Dossier {
    int id();
    String nom();
    String noPermis();
    String noPlaque();
    String prenom();
    int niveau();
    int[] getListeInfraction();
    int[] getListeReaction();
    void ajouterReactionAuDossier(int idReaction);
    void ajouterInfractionAuDossier(int idInfraction);
    String toString();
}
