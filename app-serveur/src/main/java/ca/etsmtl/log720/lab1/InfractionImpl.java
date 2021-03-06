package ca.etsmtl.log720.lab1;

public class InfractionImpl extends InfractionPOA implements java.io.Serializable{
    private int _id;
    private String _description;
    private int _niveau;
    private DossierImpl _dossier;
    private static int counter = 0;

    public static void setCounter(int newCounter){
        counter = newCounter;
    }

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

    public void setDossier(DossierImpl dossier){
        _dossier = dossier;
    }

    public String _toString() {
        return String.format("(id:%d)(niveau:%d) %s", id(), niveau(), description());
    }
}
