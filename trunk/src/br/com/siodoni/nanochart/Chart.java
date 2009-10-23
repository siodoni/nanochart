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
        String rotulo[] = {"teste de valor 1", "teste de valor 2", "teste de valor 3", "teste de valor 4", "teste de valor 5", "teste de valor 6", "teste de valor 7", "teste de valor 8", "teste de valor 9", "teste de valor 10"};
        String titulo = "Título do gráfico de barras";

        BarChart chart = new BarChart(this, cor, valor, rotulo, titulo);

        canvas = chart;
        Display.getDisplay(this).setCurrent(canvas);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
}
