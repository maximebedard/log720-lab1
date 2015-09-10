package ca.etsmtl.log720.lab1;

public class Dossier extends DossierPOA {

    private int _id;
    private String _nom;
    private String _noPermis;
    private String _noPlaque;
    private String _prenom;
    private int _niveau;
    private CollectionInfractions _infractions = new CollectionInfractions();
    private CollectionReactions _reactions = new CollectionReactions();

    public Dossier(int id, String nom, String noPermis, String noPlaque, String prenom, int niveau) {
        _id = id;
        _nom = nom;
        _noPermis = noPermis;
        _noPlaque = noPlaque;
        _prenom = prenom;
        _niveau = niveau;
    }

    public int id() {
        return _id;
    }

    public String nom() {
        return _nom;
    }

    public String noPermis() {
        return _noPermis;
    }

    public String noPlaque() {
        return _noPlaque;
    }

    public String prenom() {
        return _prenom;
    }

    public int niveau() {
        return _niveau;
    }

    public int[] getListeInfraction() {
        return null; // TODO
    }

    public int[] getListeReaction() {
        return null; // TODO
    }

    public void ajouterReactionAListe(int idReaction) {

    }

    public void ajouterInfractionAListe(int idInfraction) {

    }

    public String _toString() {
        return null;
    }
}
