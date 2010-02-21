package br.com.siodoni.nanochart.midlet;

import br.com.siodoni.nanochart.util.Cor;
import br.com.siodoni.nanochart.NanoChart;
import javax.microedition.midlet.MIDlet;
import java.util.Random;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;

/**
 * TODO 11-melhorar a documentação aqui...
 * Exemplo de midlet que utiliza a biblioteca NanoChart
 * @author Flavio Augusto Siodoni Ximenes
 */
public class Midlet extends MIDlet implements Cor {

    /**
     * TODO 12-melhorar a documentação aqui...
     */
    public void startApp() {
        Canvas canvas;
        Random random = new Random();
        int cor[] = {VERMELHO2, VERDE2, AMARELO2, AZUL2, ROXO2, VERMELHO3, VERDE3, AMARELO3, AZUL3, ROXO3};
        int valor[] = new int[10];
        String rotulo[] = new String[valor.length];
        String titulo = "Exemplo de gráfico aleatório";
        int tpGrafico = NanoChart.GRAFICO_BARRA;

        for (int i = 0; i < valor.length; i++) {
            valor[i] = random.nextInt(50) + 1;
            rotulo[i] = "valor aleatório";
        }

        NanoChart nanoChart = new NanoChart(this, cor, valor, rotulo, titulo, tpGrafico);

        canvas = nanoChart;
        Display.getDisplay(this).setCurrent(canvas);
    }

    /**
     * Método responsável por pausar a aplicação.
     */
    public void pauseApp() {
    }

    /**
     * Método responsável por destruir a aplicação.
     * @param unconditional
     */
    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
}
