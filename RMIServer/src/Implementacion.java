
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Implementacion extends UnicastRemoteObject implements ServidorInterfaz {

    Implementacion() throws RemoteException {
        super();
    }

    @Override
    public int sum(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public Byte[] Fichero(Byte[] ficheroVolcado) throws RemoteException {
        Byte[] ficheroDec;
        SupportS otro = new SupportS();
        ficheroDec = otro.Decodificar(ficheroVolcado);        
        System.out.println(ficheroDec[ficheroDec.length-1]);        
        return ficheroDec;
    }

}
