
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    public static void main(String[] args) throws MalformedURLException {
        try{
            Registry createRegistry = LocateRegistry.createRegistry(1330);
            ServidorInterfaz servidor = new Implementacion();
            Naming.rebind("//localhost:1330/SERVIDOR",servidor);
            System.out.println("Servidor a la espera");
            
        } catch (RemoteException ex){
            System.out.println("Error 1: "+ex.getLocalizedMessage());
        }
    }
    
}
