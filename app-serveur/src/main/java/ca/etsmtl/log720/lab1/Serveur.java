package ca.etsmtl.log720.lab1;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.Servant;

import java.util.HashMap;
import java.util.Map;

public class Serveur {
    protected static POA _poa;

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
        orb.run();
    }


    private static HashMap<String, Servant> getServices(){
        HashMap<String, Servant> services = new HashMap<String, Servant>();
        services.put("BanqueDossiers", new BanqueDossiers());
        services.put("BanqueInfractions", new BanqueInfractions());
        services.put("BanqueReactions", new BanqueReactions());
        return services;
    }
}
