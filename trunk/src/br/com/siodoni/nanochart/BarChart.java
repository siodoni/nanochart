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
    private int valor[] = {4, 10, 5, 3, 5, 7, 2, 1, 9, 6};
    private int cor[] = {0x00FF4500, 0x000000CD, 0x00FFFF00, 0x0000CD00, 0x007FFFD4, 0x006495ED, 0x00FFA500, 0x00FF3030, 0x001C86EE, 0x00000000};
    private String rotulo[] = {"valor 1", "valor 2", "valor 3", "valor 4", "valor 5", "valor 6", "valor 7", "valor 8", "valor 9", "valor 10"};
    private String titulo = "Titulo";
    private boolean grafico = true;

    public BarChart(Chart midlet) {
        setFullScreenMode(true);
        this.chart = midlet;
        cmdSair = new Command("Sair", Command.EXIT, 0);
        addCommand(cmdSair);
        setCommandListener(this);

        largura = getWidth();
        altura = getHeight();
        inicioAltura = (altura * 23) / 100; // 23% no inicio do eixo Y
        inicioLargura = (largura * 5) / 100; // 5% no inicio do eixo X
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
        g.setColor(0, 0, 0);
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_LARGE));
        g.drawString(titulo, inicioLargura, distCol, Graphics.LEFT | Graphics.TOP);

        //Desenhando a tabpanel
        drawTabPanel(g);

        //Desenhando os eixos
        if (this.grafico) {
            drawAxis(g);
        }

        //Desenhando as colunas.
        if (this.grafico) {
            drawColumn(g);
        } else {
            drawData(g);
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdSair) {
            chart.destroyApp(false);
        }
    }

    protected void keyPressed(int keyCode) {
        int tecla = getGameAction(keyCode);

        if (tecla == UP) {
            this.grafico = false;
            repaint();
        } else if (tecla == DOWN) {
            this.grafico = true;
            repaint();
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
        int tamTab = largura / 7, altTab = largura / 8, largTab = largura / 2, angTab = largura / 20;

        if (grafico) {
            g.setColor(0x00DCDCDC);
            g.fillRoundRect(largTab + 1, tamTab, largTab - 3, altTab, angTab, angTab);
        } else {
            g.setColor(0x00DCDCDC);
            g.fillRoundRect(1, tamTab, largTab, 30, 10, 10);
        }

        g.setColor(0x00000000);
        g.drawRoundRect(1, tamTab, largTab, 30, 10, 10);
        g.drawRoundRect(largTab + 1, tamTab, largTab - 3, altTab, angTab, angTab);
        g.drawRect(0, altTab * 2, largura, altura);
        g.setColor(0x00FFFFFF);

        if (this.grafico) {
            g.fillRect(1, altTab * 2, largTab, altura);
        } else {
            g.fillRect(largTab + 1, altTab * 2, largTab, altura);
        }
        g.fillRect(1, (altTab * 2) + 1, largura, altura);

        g.setColor(0x00000000);
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        g.drawString("Gráfico", distCol, tamTab + distCol, Graphics.LEFT | Graphics.TOP);
        g.drawString("Dados", largTab + distCol, tamTab + distCol, Graphics.LEFT | Graphics.TOP);
    }

    private void drawAxis(Graphics g) {
        g.setColor(0x00000000);
        g.drawLine(inicioLargura, inicioAltura - distCol, inicioLargura, fimAltura + distCol);
        g.drawLine(inicioLargura + 1, inicioAltura - distCol, inicioLargura + 1, fimAltura + distCol);
        g.drawLine(inicioLargura - distCol, fimAltura, fimLargura + distCol, fimAltura);
        g.drawLine(inicioLargura - distCol, fimAltura + 1, fimLargura + distCol, fimAltura + 1);
    }

    private void drawColumn(Graphics g) {
        acumulado = inicioLargura + distCol;
        for (int i = 0; i < valor.length; i++) {
            g.setColor(cor[i]);
            g.fillRect(acumulado, inicioAltura, larguraColuna, fimAltura - inicioAltura);
            g.setColor(0x00FFFFFF);
            g.fillRect(acumulado, inicioAltura, larguraColuna, getTamMaxColuna() - getQtdePixelColuna(valor[i]));
            acumulado += distCol + larguraColuna;
        }
    }

    private void drawData(Graphics g) {
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        acumulado = inicioAltura;
        for (int i = 0; i < valor.length; i++) {
            g.setColor(cor[i]);
            g.fillRect(5, acumulado, 15, 15);
            g.setColor(0x00000000);
            g.drawRect(5, acumulado, 15, 15);
            g.drawString(rotulo[i] + " - " + valor[i], 25, acumulado, Graphics.LEFT | Graphics.TOP);
            acumulado += 20;
        }
    }
}
