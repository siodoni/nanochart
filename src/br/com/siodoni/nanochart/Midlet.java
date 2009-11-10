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
        String rotulo[] = {"valor 01", "valor 02", "valor 03", "valor 04", "valor 05", "valor 06", "valor 07", "valor 08", "valor 09", "valor 10"};
        String titulo = "Título do gráfico de barras";

        for (int i = 0; i < 9; i++) {
            valor[i] = random.nextInt(15) + 1;
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
