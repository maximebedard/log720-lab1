package ca.etsmtl.log720.lab1;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Scanner;

public class ClientVoiture {

    private ORB orb;
    private BanqueDossiers banqueDossiers;
    private BanqueInfractions banqueInfractions;
    private BanqueReactions banqueReactions;
    private Dossier current;
    private Menu root;

    public ClientVoiture(ORB orb) {
        this.orb = orb;
        initializeContext();
        seedData();
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

    private void buildListeInfractions() {
        root.add("Liste des infractions", (item) -> {
            CollectionInfraction infractions = banqueInfractions.infractions();

            if (infractions.size() == 0) {
                System.out.println("Aucune infraction présente.");
                return;
            }

            buildMenuInfractions(root, infractions).display();
        });
    }

    private void buildListeDossiers() {
        root.add("Liste des dossiers", (item) -> {
            CollectionDossier dossiers = banqueDossiers.dossiers();

            if (dossiers.size() == 0) {
                System.out.println("Aucun dossier présent.");
                return;
            }

            buildMenuDossiers(root, dossiers).display();
        });
    }

    private void buildListeReactions() {
        root.add("Liste des reactions", (item) -> {
            CollectionReaction reactions = banqueReactions.reactions();

            if (reactions.size() == 0) {
                System.out.println("Aucune réaction présente.");
                return;
            }

            buildMenuReactions(root, reactions).display();
        });
    }

    private void buildRootMenu() {
        root = new Menu();
        root.setHeader(sectionTitle("menu principal"));
    }

    private Menu buildMenuDossiers(Menu parent, CollectionDossier dossiers) {
        Menu menu = new Menu(parent);
        menu.setHeader(sectionTitle("liste des dossiers"));
        for (int i = 0; i < dossiers.size(); i++) {
            Dossier dossier = dossiers.getDossier(i);
            menu.add(dossier.noPermis(), dossier._toString(), (item) -> {
                displayDossier(dossier);
            });
        }

        menu.addOption("recherche", "Effectuer une recheche", (item) -> {
            buildMenuRechercheDossier(menu).display();
        });

        return menu;
    }

    private void displayDossier(Dossier dossier) {
        System.out.print(sectionTitle("informations sur le dossier"));

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
    }

    private Menu buildMenuRechercheDossier(Menu parent) {
        Menu menu = new Menu(parent);
        menu.setHeader(sectionTitle("recherche de dossier"));
        Scanner scanner = new Scanner(System.in);
        menu.add("par nom et prenom", (item) -> {
            System.out.print("Nom : ");
            String nom = scanner.next();

            System.out.print("Prenom : ");
            String prenom = scanner.next();

            buildMenuDossiers(menu, banqueDossiers.trouverDossiersParNom(nom, prenom)).display();
        });

        menu.add("par numéro de plaque", (item) -> {
            System.out.print("Numéro de plaque : ");
            String plaque = scanner.next();

            buildMenuDossiers(menu, banqueDossiers.trouverDossiersParPlaque(plaque)).display();
        });

        menu.add("par numéro de permis", (item) -> {
            System.out.print("Numéro de permis: ");
            String noPermis = scanner.next();

            Dossier dossier = banqueDossiers.trouverDossierParPermis(noPermis);
            if (dossier == null) {
                System.out.println(String.format("Le dossier avec le #%s n'existe pas...", noPermis));
            } else {
                displayDossier(dossier);
                current = dossier;
            }
        });

        return menu;
    }

    private Menu buildMenuInfractions(Menu parent, CollectionInfraction infractions) {
        Menu menu = new Menu(parent);
        menu.setHeader(sectionTitle("liste des infractions"));
        for (int i = 0; i < infractions.size(); i++){
            Infraction infraction = infractions.getInfraction(i);
            menu.add(String.valueOf(infraction.id()), infraction.description(), (item) -> {
                System.out.println("Details de l'infraction...");
            });
        }

        return menu;
    }

    private Menu buildMenuReactions(Menu parent, CollectionReaction reactions) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(parent);
        menu.setHeader(sectionTitle("liste des reactions"));
        for (int i = 0; i < reactions.size(); i++){
            Reaction reaction = reactions.getReaction(i);
            menu.add(String.valueOf(reaction.id()), reaction.description(), (item) -> {
                System.out.print("Ajouter au dossier : ");
                try {
                    banqueDossiers.ajouterReactionAuDossier(scanner.nextInt(), reaction.id());
                    System.out.println("L'ajout de la réaction a été complété avec succès.");
                } catch (InvalidIdException e) {
                    System.err.println("L'ajout de la réaction au dossier à échoué.");
                }
            });
        }

        return menu;
    }

    private String sectionTitle(String title){
        String header = "";
        header += "=============================================================\n";
        header += title.toUpperCase() + "\n";
        if (current != null) {
            header += String.format("Document selectionné: %s\n", current.noPermis());
        }
        header += "=============================================================\n";
        return header;
    }

    public static void main( String[] args ) {
        ORB orb = ORB.init(args, null);

        ClientVoiture client = new ClientVoiture(orb);
        client.run();
    }

    private void run() {
        buildRootMenu();
        buildListeInfractions();
        buildListeDossiers();
        buildListeReactions();
        root.display();
    }



}
