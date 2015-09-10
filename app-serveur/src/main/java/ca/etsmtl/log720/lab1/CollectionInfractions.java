package ca.etsmtl.log720.lab1;

import java.util.ArrayList;

public class CollectionInfractions extends CollectionInfractionPOA {
    private ArrayList<Infraction> _infractions = new ArrayList<Infraction>();

    public Infraction getInfraction(int index) {
        return _infractions.get(index);
    }

    public int size() {
        return _infractions.size();
    }
}
