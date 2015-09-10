package ca.etsmtl.log720.lab1;

public interface BanqueDossiers {
    void ajouterDossier(String nom, String prenom, String noPermis, String noPlaque);
    void ajouterInfractionAuDossier(int idDossier, int idInfraction);
    void ajouterReactionAuDossier(int idDossier, int idReaction);
    CollectionDossiers dossiers();
    CollectionDossiers trouverDossiersParPlaque(String noPlaque);
    CollectionDossiers trouverDossiersParNom(String nom, String prenom);
    Dossier trouverDossierParPermis(String noPermis);
    Dossier trouverDossierParId(int id);
}
