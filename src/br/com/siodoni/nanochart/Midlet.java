package br.com.siodoni.nanochart;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import java.util.Random;

/**
 * @author Flavio Augusto Siodoni Ximenes
 */
public class Midlet extends MIDlet {

    private Canvas canvas;
    private static Random random = new Random();

    public void startApp() {

        int cor[] = {Cor.AZUL, Cor.VERDE, Cor.AMARELO, Cor.VERMELHO, Cor.ROXO, Cor.AZUL_ESCURO, Cor.VERDE_ESCURO, Cor.AMARELO_ESCURO, Cor.VERMELHO_ESCURO, Cor.ROXO_ESCURO};
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
