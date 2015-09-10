package ca.etsmtl.log720.lab1;

public interface BanqueInfractions {
    void ajouterInfraction(String description, int niveau);
    CollectionInfractions trouverInfractionsParDossier(Dossier dossier);
    Infraction trouverInfractionsParId(int id);
    CollectionInfractions infractions();
}
