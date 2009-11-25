package br.com.siodoni.nanochart;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import java.util.Random;

/**
 *
 * @author Flavio Augusto Siodoni Ximenes
 */
public class Midlet extends MIDlet {

    private Canvas canvas;
    private static Random random = new Random();

    public void startApp() {

        int cor[] = {Cor.VERMELHO2, Cor.VERDE2, Cor.AMARELO2, Cor.AZUL2, Cor.ROXO2, Cor.VERMELHO3, Cor.VERDE3, Cor.AMARELO3, Cor.AZUL3, Cor.ROXO3};
        int valor[] = new int[10];
        String rotulo[] = new String[valor.length];
        String titulo = "Título";
        for (int i = 0; i < valor.length; i++) {
            valor[i] = random.nextInt(30) + 1;
            rotulo[i] = "valor " + (i + 1);
        }

        NanoChart nanoChart = new NanoChart(this, cor, valor, rotulo, titulo, NanoChart.GRAFICO_BARRA);

        canvas = nanoChart;
        Display.getDisplay(this).setCurrent(canvas);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
}
