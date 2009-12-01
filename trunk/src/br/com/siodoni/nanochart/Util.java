package br.com.siodoni.nanochart;

/**
 * //TODO melhorar a documentação da classe Util
 * @version 1.0
 * @author Flavio Augusto Siodoni Ximenes
 */
public class Util {

    /**
     * //TODO documentar o metodo Util.doubleToInt
     * @param valor
     * @return int[]
     */
    public static int[] doubleToInt(double[] valor) {
        int tamValor = valor.length;
        int[] valorInt = new int[tamValor];
        for (int i = 0; i < tamValor; i++) {
            valorInt[i] = (int) valor[i];
        }
        return valorInt;
    }

    /**
     * //TODO documentar o metodo Util.doubleToInt
     * @param valor
     * @return int[]
     */
    public static int[] floatToInt(float[] valor) {
        int tamValor = valor.length;
        int[] valorInt = new int[tamValor];
        for (int i = 0; i < tamValor; i++) {
            valorInt[i] = (int) valor[i];
        }
        return valorInt;
    }
}
