package sockettcpclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

class ConnectionC {

    File FICHEROJPG = new File("FileA1.jpg");

    public void Connection(String[] nombreyP) {
        Byte[] fichero;
        try (
                Socket client = new Socket(nombreyP[0], Integer.parseInt(nombreyP[1]));
                DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                DataInputStream dis = new DataInputStream(client.getInputStream());) {

            System.out.println("fichero: " + FICHEROJPG.length());
            fichero = VolcarFichero(FICHEROJPG);

            enviarFichero(fichero, dos, dis);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Byte[] VolcarFichero(File FICHEROJPG) {
        int length = (int) FICHEROJPG.length();
        Byte[] fichero = new Byte[length];
        try (
                DataInputStream dis = new DataInputStream(new FileInputStream(FICHEROJPG));) {
            for (int i = 0; i < fichero.length; i++) {
                fichero[i] = dis.readByte();
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
        return fichero;
    }

    public void enviarFichero(Byte[] fichero, DataOutputStream dos, DataInputStream dis) throws IOException {
        int lengthFichero = fichero.length;
        int inicioBloque = 0;
        try {
            dos.writeInt(lengthFichero);
            while (true) {
                if (inicioBloque < lengthFichero) {
                    for (int i = inicioBloque; i < inicioBloque + 1000; i++) {
                        dos.writeByte(fichero[i]);
                    }
                    inicioBloque = inicioBloque + 1000;
                    System.out.println(dis.readUTF());
                } else {
                    for (int j = inicioBloque; j < lengthFichero; j++) {
                        dos.writeByte(fichero[j]);
                        System.out.println("Aqui");
                    }
                    break;
                }
            }
            System.out.println("Todo se ha enviado: " + inicioBloque);
            System.out.println(dis.readUTF());
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
    }
}
