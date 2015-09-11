package ca.etsmtl.log720.lab1;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class ClientBureau{
    public static void main( String[] args ) {
        try {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

            NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

            NameComponent[] name = new NameComponent[] { new NameComponent("BanqueDossiers", "service") };

            BanqueDossiers banqueDossiers = BanqueDossiersHelper.narrow(nc.resolve(name));
            banqueDossiers.ajouterDossier("Bedard", "Maxime", "1", "123");
            banqueDossiers.ajouterDossier("Bedard", "Maxime", "1", "123");
            System.out.println(banqueDossiers.trouverDossierParId(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
