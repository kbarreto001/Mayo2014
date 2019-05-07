package sockettcpclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

class ConnectionC {

    protected File FICHEROJPG = new File("FileA1.jpg");
    protected File FICHEROJPGDEC = new File("FileA2.jpg");

    public void Connection(DataOutputStream dosSocket, DataInputStream disSocket) {
        Byte[] fichero, ficheroDec;
        fichero = VolcarFichero(FICHEROJPG);
        try {
            enviarFichero(fichero, dosSocket, disSocket);
            ficheroDec = RecibirFichero(disSocket, dosSocket);
            guardarFichero(FICHEROJPGDEC,ficheroDec);

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
    }

    public String[] Inicio() {
        String[] nombreyP = new String[2];
        nombreyP[0] = "localhost";
        nombreyP[1] = "5000";
        System.out.println("Soy el cliente");
        return nombreyP;
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

    public void guardarFichero(File FICHEROJPGDEC, Byte[] ficheroDec){
        try(
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(FICHEROJPGDEC));
                ){
            for(int i=0;i<ficheroDec.length;i++){
                dos.writeByte(ficheroDec[i]);
            }            
        } catch(IOException ex){
            System.out.println("Error: "+ex.getLocalizedMessage());
        }
    }

    public void enviarFichero(Byte[] fichero, DataOutputStream dosSocket, DataInputStream disSocket) throws IOException {
        int lengthFichero = fichero.length;
        int inicioBloque = 0;
        Byte comprobacion;
        try {
            dosSocket.writeInt(lengthFichero);
            while (true) {
                if (inicioBloque < (lengthFichero - 1000)) {
                    for (int i = inicioBloque; i < inicioBloque + 1000; i++) {
                        dosSocket.writeByte(fichero[i]);
                    }
                    inicioBloque = inicioBloque + 1000;
                    System.out.println(disSocket.readUTF());
                } else {
                    for (int j = inicioBloque; j < lengthFichero; j++) {
                        dosSocket.writeByte(fichero[j]);
                    }
                    break;
                }
            }
            if (Objects.equals(comprobacion = disSocket.readByte(), fichero[lengthFichero - 1])) {
                System.out.println("Todo se ha enviado");
            } else {
                System.out.println("No se envio completamente");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
    }

    public Byte[] RecibirFichero(DataInputStream disSocket, DataOutputStream dosSocket) throws IOException {
        Byte[] fichero = null;
        int length, inicioBloque = 0, k = 0;
        try {
            length = disSocket.readInt();
            fichero = new Byte[length];
            while (true) {
                if (inicioBloque < (length - 1000)) {
                    for (int i = inicioBloque; i < inicioBloque + 1000; i++) {
                        fichero[i] = disSocket.readByte();
                    }
                    inicioBloque = inicioBloque + 1000;
                    dosSocket.writeUTF("Recibidos correctamente: " + inicioBloque);
                } else {
                    for (int j = inicioBloque; j < length; j++) {
                        fichero[j] = disSocket.readByte();
                        k++;
                    }
                    break;
                }
            }
            System.out.println("Todo se ha recibido: " + fichero.length);
            dosSocket.writeByte(fichero[length - 1]);
        } catch (IOException ex) {
            System.out.println("Error: 1" + ex.getLocalizedMessage());
        }
        return fichero;
    }
}
