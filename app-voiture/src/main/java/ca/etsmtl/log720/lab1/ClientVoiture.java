package ca.etsmtl.log720.lab1;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class ClientVoiture {

    private ORB orb;
    private BanqueDossiers banqueDossiers;
    private BanqueInfractions banqueInfractions;
    private BanqueReactions banqueReactions;
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
        }
        catch (NoPermisExisteDejaException ex) {
            ex.printStackTrace();
        }
    }

    private void buildListeInfractions() {
        root.add("Liste des infractions", (item) -> {
            Menu subMenu1 = new Menu(root);
            subMenu1.display();
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
            CollectionInfraction infractions = banqueInfractions.infractions();

            if (infractions.size() == 0) {
                System.out.println("Aucune infraction présente.");
                return;
            }

            buildMenuInfractions(root, infractions).display();
        });
    }

    private void buildRootMenu() {
        root = new Menu();
        root.setHeader(
            "\n" +
            "====================================================\n" +
            "                MENU PRINCIPAL                      \n" +
            "====================================================\n" +
            "\n"
        );
    }

    private Menu buildMenuDossiers(Menu parent, CollectionDossier dossiers) {
        Menu menu = new Menu(parent);
        menu.setHeader(
            "\n" +
            "====================================================\n" +
            "                LISTE DES DOSSIERS                  \n" +
            "====================================================\n" +
            "\n"
        );
        for (int i = 0; i < dossiers.size(); i++) {
            Dossier dossier = dossiers.getDossier(i);
            menu.add(dossier.noPermis(), String.format("%s %s", dossier.prenom(), dossier.nom()), (item) -> {
                System.out.println("Details du dossier...");
            });

            menu.addOption("recherche", "Effectuer une recheche", (item) -> {
            });
        }

        return menu;
    }

    private Menu buildMenuInfractions(Menu parent, CollectionInfraction infractions) {
        Menu menu = new Menu(parent);
        menu.setHeader(
            "\n" +
            "====================================================\n" +
            "                LISTE DES INFRACTIONS               \n" +
            "====================================================\n" +
            "\n"
        );
        for (int i = 0; i < infractions.size(); i++){
            Infraction infraction = infractions.getInfraction(i);
            menu.add(String.valueOf(infraction.id()), infraction.description(), (item) -> {
                System.out.println("Details de l'infraction...");
            });
        }

        return menu;
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
