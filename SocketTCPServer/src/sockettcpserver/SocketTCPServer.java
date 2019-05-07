package sockettcpserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTCPServer {

    public static void main(String[] args) {
        int counter = 1, size = 1000;
        ServerSocket server;
        Socket connection;
        DataOutputStream dos;
        DataInputStream dis;
        ConnectionS otro = new ConnectionS();
        System.out.println("Soy el Servidor");
        try {
            server = new ServerSocket(5000, size);
            while (true) {
                connection = server.accept();
                System.out.println("conexion: " + counter + " received from: " + connection.getInetAddress().getHostName());
                dos = new DataOutputStream(connection.getOutputStream());
                dis = new DataInputStream(connection.getInputStream());

                otro.connection(dis, dos);
                
                connection.close();
                dos.close();
                dis.close();
                counter++;
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }       
      
    }

}
