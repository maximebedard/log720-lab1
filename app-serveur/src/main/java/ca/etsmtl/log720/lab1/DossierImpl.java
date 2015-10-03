package ca.etsmtl.log720.lab1;

public class DossierImpl extends DossierPOA {
    private final BanqueInfractionsImpl banqueInfractions;
    private final BanqueReactionsImpl banqueReactions;
    private int _id;
    private String _nom;
    private String _noPermis;
    private String _noPlaque;
    private String _prenom;
    private CollectionInfractionImpl _infractions = new CollectionInfractionImpl();
    private CollectionReactionImpl _reactions = new CollectionReactionImpl();

    private static int counter = 0;

    public DossierImpl(BanqueInfractionsImpl _banqueInfractions, BanqueReactionsImpl _banqueReactions, String nom, String prenom, String noPermis, String noPlaque) {
        banqueInfractions = _banqueInfractions;
        banqueReactions = _banqueReactions;
        _id = counter++;
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
        InfractionImpl infraction = _infractions.plusSevere();
        if(infraction != null) {
            return infraction.niveau();
        }
        return 0;
    }

    public int[] getListeInfraction() {
        return _infractions.ids();
    }

    public int[] getListeReaction() {
        return _reactions.ids();
    }

    public void ajouterReactionAListe(int idReaction) {
        ReactionImpl reaction = banqueReactions.trouverParId(idReaction);
        if(reaction == null) { return; }

        _reactions.add(reaction);
    }

    public void ajouterInfractionAListe(int idInfraction) {
        InfractionImpl infraction = banqueInfractions.trouverParId(idInfraction);
        if(infraction == null) { return; }

        _infractions.add(infraction);
    }

    public String _toString() {
        return String.format("(id:%d)(permis:%s)(plaque:%s)(niveau:%s) %s, %s", id(), noPermis(), noPlaque(), niveau(), nom(), prenom());
    }
}
