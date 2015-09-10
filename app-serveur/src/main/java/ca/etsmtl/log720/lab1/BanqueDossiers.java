package ca.etsmtl.log720.lab1;


public class BanqueDossiers extends BanqueDossiersPOA {
    public CollectionDossier dossiers() {
        return null;
    }

    public CollectionDossier trouverDossiersParPlaque(String plaque) {
        return null;
    }

    public CollectionDossier trouverDossiersParNom(String nom, String prenom) {
        return null;
    }

    public Dossier trouverDossierParPermis(String noPermis) {
        return null;
    }

    public Dossier trouverDossierParId(int idDossier) {
        return null;
    }

    public void ajouterDossier(String nom, String prenom, String noPermis, String noPlaque) throws NoPermisExisteDejaException {

    }

    public void ajouterInfractionAuDossier(int idDossier, int idInfraction) throws InvalidIdException {

    }

    public void ajouterReactionAuDossier(int idDossier, int idReaction) throws InvalidIdException {

    }
}
