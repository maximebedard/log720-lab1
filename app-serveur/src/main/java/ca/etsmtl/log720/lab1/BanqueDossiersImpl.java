package ca.etsmtl.log720.lab1;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BanqueDossiersImpl extends BanqueDossiersPOA {
    private CollectionDossierImpl _dossiers = new CollectionDossierImpl();

    public CollectionDossier dossiers() {
        try {
            org.omg.CORBA.Object obj = Serveur._poa.servant_to_reference(_dossiers);
            return CollectionDossierHelper.narrow(obj);
        } catch (Exception e) {
            System.err.println(String.format("Erreur lors du retour de la collection de dossiers: %s", e));
            return null;
        }
    }

    public CollectionDossier trouverDossiersParPlaque(String plaque) {
        return trouverDossiersPar(d -> d.noPlaque().equals(plaque));
        //return null;
    }

    public CollectionDossier trouverDossiersParNom(String nom, String prenom) {
        return trouverDossiersPar(d -> d.nom().equals(nom) && d.prenom().equals(prenom));
        //return null;
    }

    public Dossier trouverDossierParPermis(String noPermis) {
        return trouverDossierPar(d -> d.noPermis().equals(noPermis));
        //return null;
    }

    public Dossier trouverDossierParId(int idDossier) {
        return trouverDossierPar(d -> d.id() == idDossier);
        //return null;
    }

    public void ajouterDossier(String nom, String prenom, String noPermis, String noPlaque) throws NoPermisExisteDejaException {

    }

    public void ajouterInfractionAuDossier(int idDossier, int idInfraction) throws InvalidIdException {

    }

    public void ajouterReactionAuDossier(int idDossier, int idReaction) throws InvalidIdException {

    }

    private CollectionDossier trouverDossiersPar(Predicate<DossierImpl> predicate) {
        try {
            org.omg.CORBA.Object obj = Serveur._poa.servant_to_reference(filterDossiers(predicate));
            return CollectionDossierHelper.narrow(obj);
        } catch (Exception e) {
            System.err.println(String.format("Erreur lors du retour de la collection de dossiers: %s", e));
            return null;
        }
    }

    private Dossier trouverDossierPar(Predicate<DossierImpl> predicate) {
        /*
        try {
            CollectionDossierImpl results = filterDossiers(predicate);
            if(results.size() > 0) {
                org.omg.CORBA.Object obj = Serveur._poa.servant_to_reference(results.getDossier(0));
                return DossierHelper.narrow(obj);
            }
            return null;
        } catch (Exception e) {
            System.err.println(String.format("Erreur lors du retour du dossier: %s", e));
            return null;
        }
        */
        return null;
    }

    private CollectionDossierImpl filterDossiers(Predicate<DossierImpl> predicate) {
        return new CollectionDossierImpl(
            _dossiers.stream()
                .filter(predicate)
                .collect(Collectors.toList())
        );
    }
}
