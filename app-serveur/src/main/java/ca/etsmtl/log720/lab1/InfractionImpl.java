package ca.etsmtl.log720.lab1;

public class InfractionImpl extends InfractionPOA {
    private int _id;
    private String _description;
    private int _niveau;
    private DossierImpl _dossier;
    private static int counter = 0;

    public InfractionImpl(String description, int niveau) {
        _id = counter++;
        _description = description;
        _niveau = niveau;
    }

    public int id() {
        return _id;
    }

    public String description() {
        return _description;
    }

    public int niveau() {
        return _niveau;
    }

    public DossierImpl dossier() { return _dossier; }

    public String _toString() {
        return String.format("%d - %d - %s", id(), niveau(), description());
    }
}
