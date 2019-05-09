
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SupportC {

    public Byte[] VolcarFichero(File FICHEROJPG) {
        int length = (int) FICHEROJPG.length();
        Byte[] ficheroVolcado = new Byte[length];
        try (
                DataInputStream dis = new DataInputStream(new FileInputStream(FICHEROJPG));) {
            for (int i = 0; i < ficheroVolcado.length; i++) {
                ficheroVolcado[i] = dis.readByte();
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
        return ficheroVolcado;
    }

    public void guardarFichero(File FICHEROJPGDEC, Byte[] ficheroDec) {
        try (
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(FICHEROJPGDEC));) {
            for (int i = 0; i < ficheroDec.length; i++) {
                dos.writeByte(ficheroDec[i]);
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
    }

}
