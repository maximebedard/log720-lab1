package ca.etsmtl.log720.lab1;

import org.omg.PortableServer.Servant;

interface RemoteCall<T> {
    T operation(org.omg.CORBA.Object obj);
}

public class RemoteObjectHelper{
    public static <T> T WithError(Servant servant, RemoteCall<T> call) {
        try {
            org.omg.CORBA.Object obj = Serveur._poa.servant_to_reference(servant);
            return call.operation(obj);
        } catch (Exception ex) {
            System.err.println(String.format("An error as occured: %s", ex.getMessage()));
            return null;
        }
    }
}
