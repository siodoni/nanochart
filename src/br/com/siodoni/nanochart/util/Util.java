package br.com.siodoni.nanochart.util;

/**
 * Classe responsavel por fornecer métodos para auxilio ao desenvolvimento
 * do NanoChart.
 * @version 1.0
 * @author Flavio Augusto Siodoni Ximenes
 */
public final class Util {

    /**
     * Método responsável por converter um array de valores double
     * para um vetor de valores inteiros.<br/>
     * Essa funcionalidade se torna necessária para a montagem do grafico,
     * pois os métodos de desenho da classe Graphics utilizam como padrão valores
     * inteiros, pois só existem pixels com valores inteiros.<br/>
     * @param valor array de doubles que será convertido.
     * @return int[] array de inteiros que será utilizado para a elaboração do grafico.
     * @see br.com.siodoni.nanochart.NanoChart
     */
    public static int[] doubleParaInt(double[] valor) {
        int tamValor = valor.length;
        int[] valorInt = new int[tamValor];
        for (int i = 0; i < tamValor; i++) {
            valorInt[i] = (int) valor[i];
        }
        return valorInt;
    }

    /**
     * Método responsável por converter um array de valores float
     * para um vetor de valores inteiros.<br/>
     * Essa funcionalidade se torna necessária para a montagem do grafico,
     * pois os métodos de desenho da classe Graphics utilizam como padrão valores
     * inteiros, pois só existem pixels com valores inteiros.<br/>
     * @param valor array de floats que será convertido.
     * @return int[] array de inteiros que será utilizado para a elaboração do grafico.
     * @see br.com.siodoni.nanochart.NanoChart
     */
    public static int[] floatParaInt(float[] valor) {
        int tamValor = valor.length;
        int[] valorInt = new int[tamValor];
        for (int i = 0; i < tamValor; i++) {
            valorInt[i] = (int) valor[i];
        }
        return valorInt;
    }
}
