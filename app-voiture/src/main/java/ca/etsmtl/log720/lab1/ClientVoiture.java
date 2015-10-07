package ca.etsmtl.log720.lab1;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.*;

public class ClientVoiture {

    private final Scanner scanner;
    private ORB orb;
    private BanqueDossiers banqueDossiers;
    private BanqueInfractions banqueInfractions;
    private BanqueReactions banqueReactions;
    private Dossier current;

    public ClientVoiture(ORB orb) {
        this.orb = orb;
        this.scanner = new Scanner(System.in);
        initializeContext();
        //seedData();
    }

    private void initializeContext() {
        NamingContextExt nc = null;
        NameComponent[] name = null;
        try {
            nc                = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
            banqueDossiers    = BanqueDossiersHelper.narrow(nc.resolve(new NameComponent[]{ new NameComponent("BanqueDossiers", "service") }));
            banqueInfractions = BanqueInfractionsHelper.narrow(nc.resolve(new NameComponent[] { new NameComponent("BanqueInfractions", "service") }));
            banqueReactions   = BanqueReactionsHelper.narrow(nc.resolve(new NameComponent[] { new NameComponent("BanqueReactions", "service") }));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if(banqueDossiers == null || banqueInfractions == null || banqueReactions == null) {
            System.err.println("Les banques ne sont pas initialisé.");
            System.exit(1);
        }
    }

    private void seedData() {
        try {
            banqueDossiers.ajouterDossier("Bedard",     "Maxime", "123123", "784VKY");
            banqueDossiers.ajouterDossier("Lemieux",    "Henry",  "456123", "123WTF");
            banqueDossiers.ajouterDossier("Gagnon",     "Roger",  "789123", "456BED");
            banqueDossiers.ajouterDossier("Castonguay", "Benard", "000123", "678PUT");
            banqueReactions.ajouterReaction("Evite le regard d'autrui", 1);
            banqueReactions.ajouterReaction("Fait usage de la force", 3);
            banqueReactions.ajouterReaction("Lève le ton", 1);
            banqueInfractions.ajouterInfraction("Délit de fuite", 4);
            banqueInfractions.ajouterInfraction("Excès de vitesse", 2);
            banqueInfractions.ajouterInfraction("Refuse de s'immobiliser", 5);
            banqueInfractions.ajouterInfraction("Interdiction de stationner son vehicule", 5);
        }
        catch (NoPermisExisteDejaException ex) {
            ex.printStackTrace();
        }
        catch (NiveauHorsBornesException e) {
            e.printStackTrace();
        }
    }

    private void displayDossier(Dossier dossier) {
        System.out.println(formatSectionTitle("informations sur le dossier"));

        CollectionInfraction infractions = banqueInfractions.trouverInfractionsParDossier(dossier);
        CollectionReaction reactions = banqueReactions.trouverReactionsParDossier(dossier);

        System.out.println(String.format("Nom           : %s, %s", dossier.nom(), dossier.prenom()));
        System.out.println(String.format("Niveau        : %d", dossier.niveau()));
        System.out.println(String.format("# Dossier     : %d", dossier.id()));
        System.out.println(String.format("# Permis      : %s", dossier.noPermis()));
        System.out.println(String.format("# Plaque      : %s", dossier.noPlaque()));
        System.out.println(String.format("# Infractions : %d", infractions.size()));
        System.out.println(String.format("# Reactions   : %d", reactions.size()));

        System.out.println("\nRéactions : ");
        if (reactions.size() > 0) {
            for (int j = 0; j < reactions.size(); j++) {
                System.out.println(String.format(" - %s", reactions.getReaction(j)._toString()));
            }
        } else {
            System.out.println("Aucune réactions...");
        }

        System.out.println("\nInfractions : ");
        if (infractions.size() > 0) {
            for (int j = 0; j < infractions.size(); j++) {
                System.out.println(String.format(" - %s", infractions.getInfraction(j)._toString()));
            }
        } else {
            System.out.println("Aucune infractions...");
        }

        if ((current == null || current.id() != dossier.id())
                && promptYesNo("Selectionner ce dossier?")) {
            current = dossier;
            System.out.println("Le dossier courant a été selectionné avec succès.");
        }
    }

    private String formatSectionTitle(String title){
        String header = "";
        header += "=============================================================\n";
        header += title.toUpperCase() + "\n";
        if (current != null) {
            header += String.format("Document selectionné: %s\n", current.noPermis());
        }
        header += "=============================================================\n";
        return header;
    }

    private void display() {
        final boolean[] running = {true};

        Menu actions = new Menu();
        actions.put("0", "Liste des infractions", this::displayListeInfractions);
        actions.put("1", "Liste des reactions", this::displayListeReactions);
        actions.put("2", "Liste des dossiers", () -> displayListeDossiers(null));
        actions.put("quitter", "Quitter l'application", () -> running[0] = false);

        do {
            actions.prompt(formatSectionTitle("menu principal"));
        } while(running[0]);
    }

    private void quitter() {
        System.exit(0);
    }

    public void displayListeInfractions() {
        final boolean[] running = {true};

        do {
            Menu actions = new Menu();
            CollectionInfraction infractions = banqueInfractions.infractions();
            for(int i = 0; i < infractions.size(); i++){
                Infraction infraction = infractions.getInfraction(i);
                String id = String.valueOf(infraction.id());
                actions.put(id, infraction._toString(), () -> displayInfraction(infraction));
            }

            actions.put("retour", "Retour au menu précédent", () -> running[0] = false);
            actions.put("quitter", "Quitter l'application", this::quitter);

            actions.prompt(formatSectionTitle("liste des infractions"));
        } while(running[0]);
    }

    private void displayMenu(HashMap<String, MenuItem> menu) {
        for(Map.Entry<String, MenuItem> action:menu.entrySet()){
            System.out.println(String.format("[%s] %s", action.getKey(), action.getValue()));
        }
    }

    private void displayInfraction(Infraction infraction) {
        if(current == null) {
            System.err.println("Veuillez selectionner avant d'ajouter cette infraction.");
            return;
        }

        if(promptYesNo("Ajouter cette reaction au dossier courrant")){
            current.ajouterInfractionAListe(infraction.id());
            System.out.println("L'infraction a été ajouté au dossier avec succès.");
        }
    }

    public void displayListeReactions() {
        final boolean[] running = {true};

        do {
            Menu actions = new Menu();
            CollectionReaction reactions = banqueReactions.reactions();
            for(int i = 0; i < reactions.size(); i++){
                Reaction reaction = reactions.getReaction(i);
                String id = String.valueOf(reaction.id());
                actions.put(id, reaction._toString(), () -> displayReaction(reaction));
            }

            actions.put("ajouter", "Ajouter une nouvelle réaction", this::ajouterReaction);
            actions.put("retour", "Retour au menu précédent", () -> running[0] = false);
            actions.put("quitter", "Quitter l'application", this::quitter);

            actions.prompt(formatSectionTitle("liste des reactions"));
        } while(running[0]);
    }

    private void ajouterReaction() {
        System.out.print("Description de la réaction : ");
        String description = scanner.next();

        System.out.print("Gravité : ");
        int gravite = scanner.nextInt();

        banqueReactions.ajouterReaction(description, gravite);
        System.out.println("La réaction a été ajouté avec succès.");
    }

    private void displayReaction(Reaction reaction) {
        if(current == null) {
            System.err.print("Veuillez selectionner avant d'ajouter cette réaction.");
            return;
        }

        if(promptYesNo("Ajouter cette reaction au dossier courrant")){
            current.ajouterReactionAListe(reaction.id());
            System.out.println("La réaction a été ajouté au dossier avec succès.");
        }
    }

    private void displayListeDossiers(CollectionDossier dossiers) {
        final boolean[] running = {true};

        if(dossiers == null)
            dossiers = banqueDossiers.dossiers();

        do {
            Menu actions = new Menu();
            for(int i = 0; i < dossiers.size(); i++){
                Dossier dossier = dossiers.getDossier(i);
                String id = String.valueOf(dossier.id());
                actions.put(id, dossier._toString(), () -> displayDossier(dossier));
            }
            actions.put("rechercher", "Rechercher un dossier", this::displayRechercherDossier);
            actions.put("retour", "Retour au menu précédent", () -> running[0] = false);
            actions.put("quitter", "Quitter l'application", this::quitter);


            actions.prompt(formatSectionTitle("liste des dossiers"));
        } while(running[0]);
    }

    private void displayRechercherDossier() {
        Menu actions = new Menu();
        actions.put("par nom et prénom", () -> {
            System.out.print("Nom : ");
            String nom = scanner.next();

            System.out.print("Prenom : ");
            String prenom = scanner.next();

            displayListeDossiers(banqueDossiers.trouverDossiersParNom(nom, prenom));
        });
        actions.put("par numéro de plaque", () -> {
            System.out.print("Numéro de plaque : ");
            String plaque = scanner.next();

            displayListeDossiers(banqueDossiers.trouverDossiersParPlaque(plaque));
        });
        actions.put("par numéro de permis", () -> {
            System.out.print("Numéro de permis : ");
            String noPermis = scanner.next();

            Dossier dossier = banqueDossiers.trouverDossierParPermis(noPermis);
            if (dossier == null) {
                System.out.println(String.format("Le dossier avec le #%s n'existe pas...", noPermis));
            } else {
                displayDossier(dossier);
            }
        });

        actions.prompt(formatSectionTitle("rechercher un dossier"));
    }

    private boolean promptYesNo(String message){
        List<String> yesValues = Arrays.asList(new String[]{"oui", "o", "yes", "y"});
        List<String> noValues = Arrays.asList(new String[]{"non", "n", "no"});

        boolean ret = false;
        do {
            System.out.print(String.format("%s [%s|%s]",
                    message,
                    String.join(",", yesValues),
                    String.join(",", noValues)));
            String choice = scanner.next().toLowerCase();

            if (yesValues.contains(choice)) {
                ret = true;
                break;
            }
            else if(noValues.contains(choice)) {
                break;
            }
        } while(true);

        return ret;
    }

    public static void main( String[] args ) {
        ORB orb = ORB.init(args, null);

        ClientVoiture client = new ClientVoiture(orb);
        client.display();
    }
}
