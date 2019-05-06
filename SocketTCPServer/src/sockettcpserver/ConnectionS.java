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

                fichero = RecibirFichero(dis, dos);
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

    public static Byte[] RecibirFichero(DataInputStream dis, DataOutputStream dos) throws IOException {
        Byte[] fichero = null;
        int length, inicioBloque = 0;
        try {
            length = dis.readInt();
            fichero = new Byte[length];
            System.out.println("Tamano del fichero: " + fichero.length);
            while (true) {
                if (inicioBloque < length) {
                    for (int i = inicioBloque; i < inicioBloque + 1000; i++) {
                        fichero[i] = dis.readByte();
                    }
                    inicioBloque = inicioBloque + 1000;
                    dos.writeUTF("Recibidos correctamente: " + inicioBloque);
                } else {
                    for (int j = inicioBloque; j < length; j++) {
                        fichero[j] = dis.readByte();
                    }
                    break;
                }                
            }
            System.out.println("Todo se ha recibido: " + inicioBloque);
            dos.writeUTF("Recibidos correctamente: " + inicioBloque);

        } catch (IOException ex) {
            System.out.println("Error: 1" + ex.getLocalizedMessage());
        }
        return fichero;
    }

}
