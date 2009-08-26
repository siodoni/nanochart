package br.com.siodoni.nanochart;

import javax.microedition.lcdui.*;

public class PieChart extends Canvas implements CommandListener {

    private Main chart;
    private Command cmdSair;
    private int valor[] = {10, 20, 30, 40, 50};//, 60, 70, 80, 90, 100};
    private int cor[] = {0x00FF4500, 0x000000CD, 0x00FFFF00, 0x0000CD00, 0x007FFFD4, 0x006495ED, 0x00FFA500, 0x00FF3030, 0x001C86EE, 0x00000000};
    private String titulo = "Titulo";

    public PieChart(Main midlet) {
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
    }
}
