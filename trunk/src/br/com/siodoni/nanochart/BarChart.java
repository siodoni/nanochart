package br.com.siodoni.nanochart;

import javax.microedition.lcdui.*;

/**
 *
 * @author Flavio Augusto Siodoni Ximenes
 */
public class BarChart extends Canvas implements CommandListener {

    private Chart chart;
    private Command cmdSair;
    private int largura, altura, inicioAltura, fimAltura, inicioLargura, fimLargura, areaTotal, distCol, acumulado, larguraColuna;
    private int valor[] = {4, 10, 5, 3, 2, 7};
    private int cor[] = {0x00FF4500, 0x000000CD, 0x00FFFF00, 0x0000CD00, 0x007FFFD4, 0x006495ED, 0x00FFA500, 0x00FF3030, 0x001C86EE, 0x00000000};
    private String rotulo[] = {"valor 1", "valor 2", "valor 3", "valor 4", "valor 5", "valor 6", "valor 7", "valor 8", "valor 9", "valor 10"};
    private String titulo = "Titulo";

    public BarChart(Chart midlet) {
        setFullScreenMode(true);
        this.chart = midlet;
        cmdSair = new Command("Sair", Command.EXIT, 0);
        addCommand(cmdSair);
        setCommandListener(this);

        largura = getWidth();
        altura = getHeight();
        inicioAltura = (altura * 30) / 100; // 30% no inicio do eixo Y
        inicioLargura = (largura * 10) / 100; // 10% no inicio do eixo X
        fimAltura = altura - inicioLargura;
        fimLargura = largura - inicioLargura;
        distCol = (largura * 2) / 100; // 2% entre as colunas
        acumulado = inicioLargura + distCol;
        areaTotal = fimLargura - inicioLargura;
        larguraColuna = (areaTotal / valor.length) - distCol;
    }

    public void paint(Graphics g) {
        //Pinta o fundo de branco
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, largura, altura);

        //Desenhando o titulo
        g.setColor(0x00000000);
        g.drawString(titulo, inicioLargura, distCol, Graphics.TOP | Graphics.LEFT);

        //Desenhando a tabpanel
        drawTabPanel(g);

        //Desenhando os eixos
        drawAxis(g);

        //Desenhando as colunas.
        drawColumn(g);
        //drawData(g);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdSair) {
            chart.destroyApp(false);
        }
    }

    private int getMaiorValor() {
        int maior = 0, valorAtual = 0;
        for (int i = 0; i < valor.length; i++) {
            valorAtual = valor[i];
            if (valorAtual > maior) {
                maior = valorAtual;
            }
        }
        return maior;
    }

    private int getTamMaxColuna() {
        return fimAltura - inicioAltura;
    }

    private int getQtdePixelColuna(int valor) {
        return (valor * getTamMaxColuna()) / getMaiorValor();
    }

    private void drawTabPanel(Graphics g) {
        int tamTab = largura / 6, altTab = largura / 8, largTab = largura / 2, angTab = largura / 20;
        g.setColor(0x00000000);
        g.drawRoundRect(1, tamTab, largTab, 30, 10, 10);
        g.setColor(0x00DCDCDC);
        g.fillRoundRect(largTab + 1, tamTab, largTab, altTab, angTab, angTab);
        g.setColor(0x00000000);
        g.drawRoundRect(largTab + 1, tamTab, largTab, altTab, angTab, angTab);
        g.drawRect(0, altTab * 2, largura, altura);
        g.setColor(0x00FFFFFF);
        g.fillRect(1, altTab * 2, largTab, altura);
        g.fillRect(1, (altTab * 2) + 1, largura, altura);
        g.setColor(0x00000000);
        g.drawString("Gráfico", distCol, tamTab + distCol, Graphics.TOP | Graphics.LEFT);
        g.drawString("Dados", largTab + distCol, tamTab + distCol, Graphics.TOP | Graphics.LEFT);
    }

    private void drawAxis(Graphics g) {
        g.setColor(0x00000000);
        g.drawLine(inicioLargura, inicioAltura - distCol, inicioLargura, fimAltura + distCol);
        g.drawLine(inicioLargura + 1, inicioAltura - distCol, inicioLargura + 1, fimAltura + distCol);
        g.drawLine(inicioLargura - distCol, fimAltura, fimLargura, fimAltura);
        g.drawLine(inicioLargura - distCol, fimAltura + 1, fimLargura, fimAltura + 1);
    }

    private void drawColumn(Graphics g) {
        for (int i = 0; i < valor.length; i++) {
            g.setColor(cor[i]);
            g.fillRect(acumulado, inicioAltura, larguraColuna, fimAltura - inicioAltura);
            g.setColor(0x00FFFFFF);
            g.fillRect(acumulado, inicioAltura, larguraColuna, getTamMaxColuna() - getQtdePixelColuna(valor[i]));
            acumulado += distCol + larguraColuna;
        }
        acumulado = inicioLargura + distCol;
    }

    private void drawData(Graphics g) {
        acumulado = inicioAltura;
        for (int i = 0; i < valor.length; i++) {
            g.setColor(cor[i]);
            g.fillRect(5, acumulado, 15, 15);
            g.setColor(0x00000000);
            g.drawRect(5, acumulado, 15, 15);
            g.drawString(rotulo[i], 25, acumulado, Graphics.TOP | Graphics.LEFT);
            acumulado += 20;
        }
    }
}
