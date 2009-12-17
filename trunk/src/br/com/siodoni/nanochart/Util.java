package br.com.siodoni.nanochart;

/**
 * //TODO melhorar a documentação aqui...
 * @version 1.0
 * @author Flavio Augusto Siodoni Ximenes
 */
public class Util {

    /**
     * //TODO melhorar a documentação aqui...
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
     * //TODO melhorar a documentação aqui...
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
