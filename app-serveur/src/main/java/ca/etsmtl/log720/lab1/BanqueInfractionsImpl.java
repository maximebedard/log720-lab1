package ca.etsmtl.log720.lab1;

import java.util.ArrayList;

public class BanqueInfractionsImpl extends BanqueInfractionsPOA {
    private static final int MIN_NIVEAU = 0;
    private static final int MAX_NIVEAU = 100;

    private CollectionInfractionImpl _infractions = new CollectionInfractionImpl();

    public CollectionInfraction infractions() {
        return RemoteObjectHelper.WithError(_infractions, CollectionInfractionHelper::narrow);
    }

    public CollectionInfraction trouverInfractionsParDossier(Dossier mydossier) {
        return RemoteObjectHelper.WithError(new CollectionInfractionImpl(_infractions.trouverInfractionsPar(i ->
                        i.dossier() != null && i.dossier().id() == mydossier.id())),
                CollectionInfractionHelper::narrow);
    }

    public Infraction trouverInfractionParId(int idInfraction) {
        return RemoteObjectHelper.WithError(trouverParId(idInfraction), InfractionHelper::narrow);
    }

    public void ajouterInfraction(String description, int niveau) throws NiveauHorsBornesException {
        if(niveau < MIN_NIVEAU || niveau > MAX_NIVEAU ){
            throw new NiveauHorsBornesException(String.format("Doit être en %d et %d", MIN_NIVEAU, MAX_NIVEAU));
        }

        _infractions.add(new InfractionImpl(description, niveau));
    }

    public InfractionImpl trouverParId(int idInfraction) {
        return _infractions.trouverInfractionPar(i -> i.id() == idInfraction);
    }
}
