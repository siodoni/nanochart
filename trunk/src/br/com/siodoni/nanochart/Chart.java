package br.com.siodoni.nanochart;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

/**
 * @author Flavio Augusto Siodoni Ximenes
 */
public class Chart extends MIDlet {

    private Canvas canvas;

    public void startApp() {
        int cor[] = {Cor.AZUL, Cor.VERDE, Cor.AMARELO, Cor.VERMELHO, Cor.ROXO, Cor.AZUL_ESCURO, Cor.VERDE_ESCURO, Cor.AMARELO_ESCURO, Cor.VERMELHO_ESCURO, Cor.ROXO_ESCURO};
        int valor[] = {3, 4, 8, 7, 5, 2, 1, 6, 10, 9};
        String rotulo[] = {"valor 01", "valor 02", "valor 03", "valor 04", "valor 05", "valor 06", "valor 07", "valor 08", "valor 09", "valor 10"};
        String titulo = "Título do gráfico de barras";

        BarChart chart = new BarChart(this, cor, valor, rotulo, titulo, BarChart.GRAFICO_BARRA);

        canvas = chart;
        Display.getDisplay(this).setCurrent(canvas);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
}
