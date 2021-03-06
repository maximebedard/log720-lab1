package ca.etsmtl.log720.lab1;

public class BanqueDossiersImpl extends BanqueDossiersPOA implements java.io.Serializable{
    private CollectionDossierImpl _dossiers = new CollectionDossierImpl();
    private BanqueReactionsImpl _reactions;
    private BanqueInfractionsImpl _infractions;

    public BanqueDossiersImpl(BanqueReactionsImpl reactions, BanqueInfractionsImpl infractions){
        _reactions = reactions;
        _infractions = infractions;
    }

    public void adjustIDs(){
        DossierImpl.setCounter(_dossiers.size());
        ReactionImpl.setCounter(_reactions.reactions().size());
        InfractionImpl.setCounter(_infractions.infractions().size());
    }

    public BanqueReactionsImpl getReactions() { return _reactions;}
    public BanqueInfractionsImpl getInfractions() { return _infractions;}

    public CollectionDossier dossiers() {
        return RemoteObjectHelper.WithError(_dossiers, CollectionDossierHelper::narrow);
    }

    public CollectionDossier trouverDossiersParPlaque(String plaque) {
        return RemoteObjectHelper.WithError(new CollectionDossierImpl(_dossiers
                        .trouverDossiersPar(d -> d.noPlaque().equals(plaque))),
                CollectionDossierHelper::narrow);
    }

    public CollectionDossier trouverDossiersParNom(String nom, String prenom) {
        return RemoteObjectHelper.WithError(new CollectionDossierImpl(_dossiers
                        .trouverDossiersPar(d -> d.nom().equals(nom) && d.prenom().equals(prenom))),
                CollectionDossierHelper::narrow);
    }

    public Dossier trouverDossierParPermis(String noPermis) {
        return RemoteObjectHelper.WithError(_dossiers
                .trouverDossierPar(d -> d.noPermis().equals(noPermis)), DossierHelper::narrow);
    }

    public Dossier trouverDossierParId(int idDossier) {
        return RemoteObjectHelper.WithError(_dossiers.trouverDossierPar(d -> d.id() == idDossier), DossierHelper::narrow);
    }

    public void ajouterDossier(String nom, String prenom, String noPermis, String noPlaque) throws NoPermisExisteDejaException {
        DossierImpl existsing = _dossiers.trouverDossierPar(d -> d.noPermis().equals(noPermis));
        if(existsing != null) {
            throw new NoPermisExisteDejaException();
        }

        _dossiers.add(new DossierImpl(_infractions, _reactions, nom, prenom, noPermis, noPlaque));
    }

    public void ajouterInfractionAuDossier(int idDossier, int idInfraction) throws InvalidIdException {
        DossierImpl dossier = _dossiers.trouverDossierPar(d -> d.id() == idDossier);
        if(dossier == null) {
            throw new InvalidIdException(String.format("idDossier:%d", idDossier));
        }

        dossier.ajouterInfractionAListe(idInfraction);
    }

    public void ajouterReactionAuDossier(int idDossier, int idReaction) throws InvalidIdException {
        DossierImpl dossier = _dossiers.trouverDossierPar(d -> d.id() == idDossier);

        if(dossier == null) {
            throw new InvalidIdException(String.format("idDossier:%d", idDossier));
        }

        dossier.ajouterReactionAListe(idReaction);
    }
}
