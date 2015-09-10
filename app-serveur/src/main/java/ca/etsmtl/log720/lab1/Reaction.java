package ca.etsmtl.log720.lab1;

public class Reaction extends ReactionPOA {
    private int _id;
    private String _description;
    private int _niveau;

    public Reaction(int id, String description, int niveau) {
        _id = id;
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

    public String _toString() {
        return String.format("%d - %d - %s", id(), niveau(), description());
    }
}
