
public class SupportS {

    public Byte[] Decodificar(Byte[] fichero) {
        Byte[] ficheroDec = new Byte[fichero.length];
        for (int i = 0; i < fichero.length; i++) {
            ficheroDec[i] = (byte) (255 - (int) fichero[i]);
        }
        return ficheroDec;
    }

}
