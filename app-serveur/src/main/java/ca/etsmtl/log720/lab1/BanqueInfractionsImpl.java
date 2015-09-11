package ca.etsmtl.log720.lab1;

import java.util.ArrayList;

public class BanqueInfractionsImpl extends BanqueInfractionsPOA {

    private CollectionInfractionImpl _infractions = new CollectionInfractionImpl();

    public CollectionInfraction infractions() {
        return RemoteObjectHelper.WithError(_infractions, CollectionInfractionHelper::narrow);
    }

    public CollectionInfraction trouverInfractionsParDossier(Dossier mydossier) {
        return null;
    }

    public Infraction trouverInfractionParId(int idInfraction) {
        return null;
    }

    public void ajouterInfraction(String description, int niveau) throws NiveauHorsBornesException {

    }

    public InfractionImpl trouverParId(int idInfraction) {
        return _infractions.trouverInfractionPar(i -> i.id() == idInfraction);
    }
}
