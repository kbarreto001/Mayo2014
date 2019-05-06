
package sockettcpserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionS {

    public static void connection() {
        int counter = 1, size = 1000;
        ServerSocket server;
        Socket connection;
        DataOutputStream dos;
        DataInputStream dis;
        Byte[] fichero = new Byte[1000];
        try {
            server = new ServerSocket(5000, size);
            while (true) {
                connection = server.accept();
                System.out.println("concexion: " + counter + " received from: " + connection.getInetAddress().getHostName());
                dos = new DataOutputStream(connection.getOutputStream());
                dis = new DataInputStream(connection.getInputStream());
                
                fichero = RecibirFichero(dis);
                System.out.println(fichero[60]);
                
                connection.close();
                dos.close();
                dis.close();
                counter++;
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
    }
    
    public static Byte[] RecibirFichero(DataInputStream dis) throws IOException{
        Byte[] fichero = new Byte[1000];
        try{
            for (int i=0;i<fichero.length;i++){
                fichero[i] = dis.readByte();
            }
            
        } catch(IOException ex){
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
        return fichero;
    }
       
}
