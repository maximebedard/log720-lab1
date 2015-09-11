package ca.etsmtl.log720.lab1;

public class BanqueDossiersImpl extends BanqueDossiersPOA {
    private CollectionDossierImpl _dossiers = new CollectionDossierImpl();

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
        if(existsing == null) {
            _dossiers.add(new DossierImpl(nom, prenom, noPermis, noPlaque));
        }
        else {
            throw new NoPermisExisteDejaException();
        }
    }

    public void ajouterInfractionAuDossier(int idDossier, int idInfraction) throws InvalidIdException {

    }

    public void ajouterReactionAuDossier(int idDossier, int idReaction) throws InvalidIdException {

    }
}
