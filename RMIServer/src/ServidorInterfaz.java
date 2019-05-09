
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServidorInterfaz extends Remote {

    public int sum(int a, int b) throws RemoteException;

    public Byte[] Fichero(Byte[] ficheroVolcado) throws RemoteException;

}
