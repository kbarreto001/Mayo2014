package sockettcpclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketTCPClient {

    public static void main(String[] args) throws IOException {
        ConnectionC otro = new ConnectionC();
        String[] nombreyP = new String[2];
        nombreyP = otro.Inicio();
        try (
                Socket client = new Socket(nombreyP[0], Integer.parseInt(nombreyP[1]));
                DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                DataInputStream dis = new DataInputStream(client.getInputStream());) {
            
            otro.Connection(dos, dis);
            
        }

    }

}
