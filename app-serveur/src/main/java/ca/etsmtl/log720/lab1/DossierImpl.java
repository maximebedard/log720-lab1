package ca.etsmtl.log720.lab1;

public class DossierImpl extends DossierPOA {
    private int _id;
    private String _nom;
    private String _noPermis;
    private String _noPlaque;
    private String _prenom;
    private int _niveau;
    private CollectionInfractionImpl _infractions = new CollectionInfractionImpl();
    private CollectionReactionImpl _reactions = new CollectionReactionImpl();

    public DossierImpl(int id, String nom, String noPermis, String noPlaque, String prenom, int niveau) {
        _id = id;
        _nom = nom;
        _noPermis = noPermis;
        _noPlaque = noPlaque;
        _prenom = prenom;
        _niveau = niveau;
    }

    public DossierImpl(String nom, String prenom, String noPermis, String noPlaque) {
        _nom = nom;
        _prenom = prenom;
        _noPermis = noPermis;
        _noPlaque = noPlaque;
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
        // TODO
    }

    public void ajouterInfractionAListe(int idInfraction) {
        // TODO
    }

    public String _toString() {
        return String.format("%d - %d - %s", id(), niveau(), nom()); // TODO: improve this
    }
}
