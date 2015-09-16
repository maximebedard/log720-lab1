package ca.etsmtl.log720.lab1;

import com.sun.javafx.css.ParsedValueImpl;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import java.util.Scanner;

public class ClientBureau{
    public static void main( String[] args ) {
        try {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

            NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

            NameComponent[] name = new NameComponent[] { new NameComponent("BanqueDossiers", "service") };

            BanqueDossiers banqueDossiers = BanqueDossiersHelper.narrow(nc.resolve(name));
            //banqueDossiers.ajouterDossier("Savoie", "Alexandre", "2", "124");
            //banqueDossiers.ajouterDossier("Bedard", "Maxime", "1", "123");
            //System.out.println(banqueDossiers.trouverDossierParId(2));
            // On créer un peu d'espace pour pouvoir mieu voir ...

            boolean quit = false;
            while(!quit) {
                for(int i = 0; i < 5; i++)
                    System.out.println();
                System.out.println("*********************************************************** ");
                System.out.println("Bonjour; Bienvenu dans le système de police, version bureau.");
                System.out.println("Voici vos options : ");
                System.out.println("1 - Ajouter un dossier dans la banque de donnée");
                System.out.println("2 - Ajouter une infraction dans la banque d'infractions");
                System.out.println("3 - Visualiser la liste des dossiers compris dans la banque de dossiers");
                System.out.println("4 - Visualiser une liste des infractions comprises dans la banque d'infractions");
                System.out.println("5 - Quitter");
                Scanner in = new Scanner(System.in);

                // on lit le choix
                String strInput = in.next();
                int choix = 0;
                try{
                    // on parse le choix
                    choix = Integer.parseInt(strInput);
                }catch (Exception ex){
                    // le choix est invalide
                    System.out.println("Votre choix est invalide ...");
                }

                // ici on gere le choix...
                switch (choix){
                    case 1 :
                        System.out.println("Ajout d'un dossier ...");
                        String nom = getNextString(in, "Veuillez entrer le nom à inscrire au dossier ...");
                        String prenom = getNextString(in, "Veuillez entrer le prénom à inscrire au dossier ...");
                        String noPermis = getNextString(in, "Veuillez entrer le numéro de permis à inscrire au dossier ...");
                        String noPlaque = getNextString(in,"Veuillez entrer le numéro de plaque à inscrire au dossier ...");

                        try {
                            banqueDossiers.ajouterDossier(nom, prenom, noPermis, noPlaque);
                            System.out.println("L'ajout du dossier à la base de donnée c'est bien effectué...");
                            System.out.println("Appuyez sur retour pour continuer ...");
                            System.in.read();
                        }
                        catch (NoPermisExisteDejaException ex)
                        {
                            System.out.println("Ce numéro de permis existe déjà, veuillez recommencer ...");
                        }
                        break;
                    case 2 :
                        System.out.println("Ajout d'une infraction ...");
                        break;
                    case 3 :
                        System.out.println("Visualiser une liste de dossiers ...");
                        break;
                    case 4 :
                        System.out.println("Visualiser une liste d'infractions ...");
                        break;
                    case 5 :
                        quit = true;
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getNextString(Scanner in, String question)
    {
        System.out.println(question);
        return readNextString(in);
    }

    private static int getNextInt(Scanner in, String question){
        boolean valid = false;
        int retour = -1;
        while(!valid)
        {
            System.out.println(question);
            retour = readNextInt(in);
            if(retour != -1)
            {
                valid = true;
            }
        }
        return retour;
    }



    private static String readNextString(Scanner in)
    {
        String strInput = in.next();
        return strInput;
    }



    private static int readNextInt(Scanner in)
    {
        String strInput = in.next();
        int choix = -1;
        try{
            // on parse le choix
            choix = Integer.parseInt(strInput);
        }catch (Exception ex){
            // le choix est invalide
            System.out.println("Votre choix est invalide ...");
        }
        return choix;
    }
}
