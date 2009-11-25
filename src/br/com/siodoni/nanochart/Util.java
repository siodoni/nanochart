package br.com.siodoni.nanochart;

/**
 *
 * @version 1.0
 * @author Flavio Augusto Siodoni Ximenes
 */
public class Util {

    public static int[] doubleToInt(double[] valor) {
        int tamValor = valor.length;
        int[] valorInt = new int[tamValor];
        for (int i = 0; i < tamValor; i++) {
            valorInt[i] = (int) valor[i];
        }
        return valorInt;
    }

    public static int[] floatToInt(float[] valor) {
        int tamValor = valor.length;
        int[] valorInt = new int[tamValor];
        for (int i = 0; i < tamValor; i++) {
            valorInt[i] = (int) valor[i];
        }
        return valorInt;
    }
}
