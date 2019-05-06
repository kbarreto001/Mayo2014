
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
            
                System.out.println("fichero: "+FICHEROJPG.length());
                fichero = VolcarFichero(FICHEROJPG);
                System.out.println(fichero[60]);
                
                enviarFichero(fichero,dos);

        } catch (IOException e) {
            System.out.println(e);
        }
    }
        
    public Byte[] VolcarFichero(File FICHEROJPG){ 
        int length = (int)FICHEROJPG.length();
        Byte[] fichero = new Byte[length];        
        try(
                DataInputStream dis = new DataInputStream(new FileInputStream(FICHEROJPG));
                ){
            for(int i=0;i<fichero.length;i++){
                fichero[i]=dis.readByte();                
            }
        } catch (IOException ex){
            System.out.println("Error: "+ex.getLocalizedMessage());
        }
        return fichero;
    }
    
    public void enviarFichero(Byte[] fichero,DataOutputStream dos) throws IOException{
        int bloque;
        try{
            for (int i=0;i<35;i++){
                bloque = (i+1)*1000;
                for(int j=0;j<bloque;j++){
                    
                }
                dos.writeByte(fichero[i]);
            }
        } catch (IOException ex){
            System.out.println("Error: "+ex.getLocalizedMessage());
        }
    }
}
