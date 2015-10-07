package ca.etsmtl.log720.lab1;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.Servant;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Serveur {
    protected static POA _poa;
    static BanqueDossiersImpl dossiers = null;
    static BanqueReactionsImpl reactions = null;
    static BanqueInfractionsImpl infractions = null;

    public static void main(String args[]) {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

        try {
            _poa = org.omg.PortableServer.POAHelper
                    .narrow(orb.resolve_initial_references("RootPOA"));
            _poa.the_POAManager().activate();

            for(Map.Entry<String,Servant> entry : getServices().entrySet()) {
                org.omg.CORBA.Object o = _poa.servant_to_reference(entry.getValue());
                NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
                NameComponent[] name = new NameComponent[]{
                    new NameComponent(entry.getKey(), "service")
                };
                nc.rebind(name, o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Runtime.getRuntime().addShutdownHook(new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Closing server system, serializing data...");
                        // closing dossiers
                        try{
                            FileOutputStream fileOut = new FileOutputStream("../tmp/dossiers.saved");
                            ObjectOutputStream out = new ObjectOutputStream(fileOut);
                            out.writeObject(dossiers);
                            out.close();
                            fileOut.close();
                            System.out.println("Serialized data saved in /tmp/dossiers.saved");
                        }catch (IOException ex){
                            System.out.println("An error occured while saving : Dossiers");
                            System.out.println(ex.toString());
                        }


                    }
                }
        ));


        orb.run();
    }


    private static HashMap<String, Servant> getServices(){
        HashMap<String, Servant> services = new HashMap<String, Servant>();

        File f = new File("../tmp/dossiers.saved");
        if(f.exists())
        {
            // deserialize the file
            System.out.println("========>Reading serialized dossiers.saved");
            try {
                FileInputStream fileIn = new FileInputStream("../tmp/dossiers.saved");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                dossiers = (BanqueDossiersImpl) in.readObject();
                infractions = dossiers.getInfractions();
                reactions = dossiers.getReactions();
                in.close();
                fileIn.close();
                dossiers.adjustIDs();
            }catch (Exception ex){
                System.out.println("An error occured while reading : Dossiers");
                System.out.println(ex.toString());
            }
        }else{
            // create a new one
            System.out.println("No record found, creating new dossier listing ...");
            infractions = new BanqueInfractionsImpl();
            reactions = new BanqueReactionsImpl();
            dossiers = new BanqueDossiersImpl(reactions, infractions);
        }



        services.put("BanqueDossiers", dossiers);
        services.put("BanqueInfractions", infractions);
        services.put("BanqueReactions", reactions);
        return services;
    }
}
