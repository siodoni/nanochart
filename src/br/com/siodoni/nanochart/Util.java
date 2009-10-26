package br.com.siodoni.nanochart;

import javax.microedition.lcdui.*;

/**
 *
 * @author Flavio
 */
public class Util extends Canvas {

    protected void paint(Graphics g) {
    }

    public int[] doubleToInt(double[] valor) {
        int tamValor = valor.length;
        int[] valorInt = new int[tamValor];
        for (int i = 0; i < tamValor; i++) {
            valorInt[i] = (int) valor[i];
        }
        return valorInt;
    }

    public int[] floatToInt(float[] valor) {
        int tamValor = valor.length;
        int[] valorInt = new int[tamValor];
        for (int i = 0; i < tamValor; i++) {
            valorInt[i] = (int) valor[i];
        }
        return valorInt;
    }
}
