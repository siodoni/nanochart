package br.com.siodoni.nanochart;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author Flavio Augusto Siodoni Ximenes
 */
public class Chart extends MIDlet {

    private Canvas canvas;

    public void startApp() {
        int cor[] = {Cor.AZUL, Cor.VERDE, Cor.AMARELO, Cor.VERMELHO, Cor.ROXO, Cor.AZUL_ESCURO, Cor.VERDE_ESCURO, Cor.AMARELO_ESCURO, Cor.VERMELHO_ESCURO, Cor.ROXO_ESCURO};
        int valor[] = {3, 4, 8, 7, 5, 2, 1, 6, 10, 9};
        String rotulo[] = {"valor 1", "valor 2", "valor 3", "valor 4", "valor 5", "valor 6", "valor 7", "valor 8", "valor 9", "valor 10"};
        BarChart chart = new BarChart(this, cor, valor, rotulo, "Título do gráfico de barras");

        canvas = chart;
        Display.getDisplay(this).setCurrent(canvas);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
}
