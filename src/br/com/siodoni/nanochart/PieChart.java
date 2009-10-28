package br.com.siodoni.nanochart;

import javax.microedition.lcdui.*;

/**
 * @author Flavio Augusto Siodoni Ximenes
 */
public class PieChart extends Canvas implements CommandListener {

    private Chart chart;
    private Command cmdSair;
    private int valor[] = {3, 4, 8, 7, 5, 2, 1, 6, 10, 9};//{10, 20, 30, 40, 50};//, 60, 70, 80, 90, 100};
    private int cor[] = {Cor.AZUL, Cor.VERDE, Cor.AMARELO, Cor.VERMELHO, Cor.ROXO, Cor.AZUL_ESCURO, Cor.VERDE_ESCURO, Cor.AMARELO_ESCURO, Cor.VERMELHO_ESCURO, Cor.ROXO_ESCURO};

    public PieChart(Chart midlet) {
        setFullScreenMode(true);
        this.chart = midlet;
        cmdSair = new Command("Sair", Command.EXIT, 0);
        addCommand(cmdSair);
    }

    public void paint(Graphics g) {
        int largura = this.getWidth();
        int altura = this.getHeight();

        g.setColor(255, 255, 255);
        g.fillRect(0, 0, largura, altura);

        int soma = 0;
        for (int i = 0; i < valor.length; i++) {
            soma += valor[i];
        }
        int anguloDelta = 360 * 100 / soma / 100;
        int x = valor.length - 1;//4;
        int y = valor.length - 1;//4;
        int diametro;

        if (largura > altura) {
            diametro = altura - y * 2;
        } else {
            diametro = largura - x * 2;
        }

        int anguloInicio = 0;

        for (int i = 0; i < valor.length; i++) {
            g.setColor(cor[i]);
            g.fillArc(x, y, diametro, diametro, anguloInicio, anguloDelta * valor[i]);
            anguloInicio += anguloDelta * valor[i];
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdSair) {
            chart.destroyApp(false);
        }
    }
}
