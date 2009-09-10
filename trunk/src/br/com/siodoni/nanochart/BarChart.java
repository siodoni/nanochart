package br.com.siodoni.nanochart;

import javax.microedition.lcdui.*;

/**
 *
 * @author Flavio Augusto Siodoni Ximenes
 */
public class BarChart extends Canvas implements CommandListener {

    private Chart chart;
    private Command cmdSair;
    private int largura, altura, inicioAltura, fimAltura, inicioLargura, fimLargura, areaTotal, distCol, acumulado, larguraColuna, percLargura, tamTitEixoX, tamTitEixoY, tamRotulo, tamValor, maiorValor;
    private int cor[] = {0x000000CD, 0x0032CD32, 0x00FFD700, 0x00FF4500, 0x009A32CD, 0x00000080, 0x0000AA44, 0x00AA8800, 0x00800000, 0x00660080};
    private int valor[] = {3, 4, 8, 7, 5, 2, 1, 6, 10, 9};
    private String rotulo[] = {"valor 1", "valor 2", "valor 3", "valor 4", "valor 5", "valor 6", "valor 7", "valor 8", "valor 9", "valor 10"};
    private String titulo = "Título", tituloEixoX = "", tituloEixoY = "", erro = "";
    private boolean grafico = true, validado = false, existeNegativo = false;

    public BarChart(Chart midlet) {
        setFullScreenMode(true);
        this.chart = midlet;
        cmdSair = new Command("Sair", Command.EXIT, 0);
        addCommand(cmdSair);
        setCommandListener(this);

        tamTitEixoX = tituloEixoX.length();
        tamTitEixoY = tituloEixoY.length();
        tamRotulo = rotulo.length;
        tamValor = valor.length;
        maiorValor = getMaiorValor();

        if (tamTitEixoX > 0 || tamTitEixoY > 0) {
            percLargura = 10;
        } else {
            percLargura = 5;
        }

        largura = getWidth();
        altura = getHeight();
        inicioAltura = (altura * 23) / 100; // % no inicio do eixo Y
        inicioLargura = (largura * percLargura) / 100; // % no inicio do eixo X
        fimAltura = (altura - inicioLargura);
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

        // Validando as informações
        validaInformacoes(g);

        if (validado) {
            //Desenhando o titulo
            g.setColor(0, 0, 0);
            g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
            g.drawString(titulo, inicioLargura, distCol, Graphics.LEFT | Graphics.TOP);

            //Desenhando a tabpanel
            drawTabPanel(g);

            if (this.grafico) {
                //Desenhando os eixos
                drawAxis(g);
                //Desenhando as colunas
                drawColumn(g);
            } else {
                drawData(g);
            }
        } else {
        }
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdSair) {
            chart.destroyApp(false);
        }
    }

    protected void keyPressed(int keyCode) {
        int tecla = getGameAction(keyCode);

        if (tecla == Canvas.RIGHT) {
            this.grafico = false;
            repaint();
        } else if (tecla == Canvas.LEFT) {
            this.grafico = true;
            repaint();
        }
    }

    private int getMaiorValor() {
        int maior = 0, valorAtual = 0;
        for (int i = 0; i < valor.length; i++) {
            if (valor[i] < 0) {
                existeNegativo = true;
            }
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
        return (valor * getTamMaxColuna()) / maiorValor;
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
        g.drawString(" Gráfico", distCol, tamTab + distCol, Graphics.LEFT | Graphics.TOP);
        g.drawString(" Dados", largTab + distCol, tamTab + distCol, Graphics.LEFT | Graphics.TOP);
    }

    private void drawAxis(Graphics g) {
        g.setColor(0x00000000);
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL));

        //Eixo X
        g.setColor(0x00000000);
        g.drawLine(inicioLargura - distCol, fimAltura, fimLargura, fimAltura);
        g.drawLine(inicioLargura - distCol, fimAltura + 1, fimLargura, fimAltura + 1);
        g.drawString(tituloEixoX, inicioLargura, fimAltura + distCol, Graphics.LEFT | Graphics.TOP);

        //Eixo Y
        g.drawLine(inicioLargura, inicioAltura - distCol, inicioLargura, fimAltura + distCol);
        g.drawLine(inicioLargura + 1, inicioAltura - distCol, inicioLargura + 1, fimAltura + distCol);
        if (tamTitEixoY > 0) {
            int acumuladoEixoY = inicioAltura - distCol;
            for (int i = 0; i < tamTitEixoY; i++) {
                g.drawString(tituloEixoY.substring(i, i + 1), inicioLargura / 2, acumuladoEixoY, Graphics.LEFT | Graphics.TOP);
                acumuladoEixoY += Font.SIZE_SMALL;
            }
            acumuladoEixoY = inicioAltura - distCol;
        }
    }

    private void drawColumn(Graphics g) {
        acumulado = inicioLargura + distCol;
        for (int i = 0; i < tamValor; i++) {
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
        int tamLegenda = altura / 20;
        for (int i = 0; i < tamValor; i++) {
            g.setColor(cor[i]);
            g.fillRect(5, acumulado, tamLegenda, tamLegenda);
            g.setColor(0x00000000);
            g.drawRect(5, acumulado, tamLegenda, tamLegenda);
            g.drawString(rotulo[i] + " - " + valor[i], tamLegenda * 2, acumulado, Graphics.LEFT | Graphics.TOP);
            acumulado += tamLegenda + distCol;
        }
        acumulado = inicioAltura;
    }

    private void validaInformacoes(Graphics g) {
        if (existeNegativo) {
            erro += "\nOs valores para o gráfico" +
                    "\ndevem ser positivos\n";
            validado = false;

            System.out.println("1");

        } else if (tamRotulo > 10 || tamValor > 10) {
            erro += "\nO tamanho maximo de valores" +
                    "\ndeve ser 10.\n";
            validado = false;

            System.out.println("2");
        } else if (tamRotulo != tamValor) {
            erro += "\nDiferença de tamanho entre" +
                    "\no array do rótulo e do valor.\n";
            validado = false;

            System.out.println("3");
        } else {
            validado = true;

            System.out.println("4");
        }

        if (!validado) {
            erro = "Erro na montagem do gráfico!\n" + erro;
            g.setColor(0, 0, 0);
            g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
            g.drawString(erro, 0, 0, Graphics.LEFT | Graphics.TOP);
        }
    }
}
