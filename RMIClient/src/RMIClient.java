
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient {
    

    public static void main(String[] args) throws NotBoundException, MalformedURLException {
        File FICHEROJPG = new File("FileJ1.pdf");
        File FICHEROJPGDEC = new File("FileJ2.pdf");
        Byte[] ficheroDec;
        try{
            String url = "//localhost:1330/SERVIDOR";
            ServidorInterfaz objetoRemoto = (ServidorInterfaz)Naming.lookup(url);
            System.out.println("Soy el cliente");
            SupportC otro = new SupportC();
            
            ficheroDec=objetoRemoto.Fichero(otro.VolcarFichero(FICHEROJPG));
            System.out.println(ficheroDec[ficheroDec.length-1]);
            otro.guardarFichero(FICHEROJPGDEC, ficheroDec);            
            
        } catch (RemoteException ex){
            System.out.println("Error 1: "+ex.getLocalizedMessage());
        }
    }
    
}
