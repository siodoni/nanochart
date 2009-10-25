package br.com.siodoni.nanochart;

import javax.microedition.lcdui.*;

/**
 *
 * @author Flavio Augusto Siodoni Ximenes
 */
public class BarChart extends Canvas implements CommandListener {

    private Chart chart;
    private Command cmdSair;
    private int largura, altura, inicioAltura, fimAltura, inicioLargura, fimLargura, areaTotal, distCol, acumulado, larguraColuna, percLargura, tamRotulo, tamValor, maiorValor;
    private int cor[], posTab[] = new int[6];
    private int valor[];
    private String rotulo[];
    private String titulo;
    private StringBuffer erro = new StringBuffer();
    private boolean grafico = true, validado = false, existeNegativo = false;
    private Util util = new Util();

    public BarChart(Chart midlet, int cor[], int valor[], String rotulo[], String titulo) {
        this.chart = midlet;
        this.cor = cor;
        this.valor = valor;
        this.rotulo = rotulo;
        this.titulo = titulo;

        setFullScreenMode(true);
        cmdSair = new Command("Sair", Command.EXIT, 0);
        addCommand(cmdSair);
        setCommandListener(this);

        tamRotulo = rotulo.length;
        tamValor = valor.length;
        maiorValor = getMaiorValor();
        percLargura = 5;

        largura = util.getWidth();
        altura = util.getHeight();
        inicioAltura = (altura * 23) / 100; // % no inicio do eixo Y
        inicioLargura = (largura * percLargura) / 100; // % no inicio do eixo X
        fimAltura = (altura - inicioLargura);
        fimLargura = largura - inicioLargura;
        distCol = (largura * 2) / 100; // % entre as colunas
        acumulado = inicioLargura + distCol;
        areaTotal = fimLargura - inicioLargura;
        larguraColuna = (areaTotal / valor.length) - distCol;
    }

    public void paint(Graphics g) {
        //Pinta o fundo de branco
        g.setColor(Cor.BRANCO);
        g.fillRect(0, 0, largura, altura);

        //Validando as informações
        validaInformacoes(g);

        if (validado) {
            //Desenhando o titulo
            g.setColor(Cor.PRETO);
            g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE));
            g.drawString(titulo, inicioLargura, distCol, Graphics.LEFT | Graphics.TOP);

            //Desenhando a tabpanel
            desenhaTabPanel(g);

            if (this.grafico) {
                //Desenhando os eixos
                desenhaEixo(g);
                //Desenhando as colunas
                desenhaColuna(g);
            } else {
                //Desenhando a tabela de legenda
                desenhaLegenda(g);
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
            serviceRepaints();
        } else if (tecla == Canvas.LEFT) {
            this.grafico = true;
            repaint();
            serviceRepaints();
        }
    }

    protected void pointerPressed(int x, int y) {
        if ((x >= posTab[0] && y <= posTab[1]) || (x >= posTab[0] && y <= posTab[2])) {
            this.grafico = true;
            repaint();
            serviceRepaints();
        }
        if ((x >= posTab[3] && y <= posTab[4]) || (x >= posTab[3] && y <= posTab[5])) {
            this.grafico = false;
            repaint();
            serviceRepaints();
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

    private void desenhaTabPanel(Graphics g) {
        int tamTab = largura / 7, altTab = largura / 8, largTab = largura / 2, angTab = largura / 20;

        if (grafico) {
            g.setColor(Cor.CINZA);
            g.fillRoundRect(largTab + 1, tamTab, largTab - 3, altTab, angTab, angTab);
        } else {
            g.setColor(Cor.CINZA);
            g.fillRoundRect(1, tamTab, largTab, altTab, angTab, angTab);
        }

        g.setColor(Cor.PRETO);
        g.drawRoundRect(1, tamTab, largTab, altTab, angTab, angTab);
        g.drawRoundRect(largTab + 1, tamTab, largTab - 3, altTab, angTab, angTab);

        //Guardando as posições.
        posTab[0] = tamTab;                  // X inicial
        posTab[1] = posTab[0] + largTab;     // X final largura
        posTab[2] = posTab[0] + altTab;      // X final altura

        posTab[3] = largTab + 1;             // Y inicial
        posTab[4] = posTab[3] + largTab - 3; // Y final largura
        posTab[5] = posTab[3] + altTab;      // Y final altura

        g.drawRect(0, altTab * 2, largura - 1, altura);
        g.setColor(Cor.BRANCO);

        if (this.grafico) {
            g.fillRect(1, altTab * 2, largTab, altura);
        } else {
            g.fillRect(largTab + 1, altTab * 2, largTab - 2, altura);
        }

        g.fillRect(1, (altTab * 2) + 1, largura - 2, altura);
        g.setColor(Cor.PRETO);
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        g.drawString(" Gráfico", distCol, tamTab + distCol, Graphics.LEFT | Graphics.TOP);
        g.drawString(" Dados", largTab + distCol, tamTab + distCol, Graphics.LEFT | Graphics.TOP);
    }

    private void desenhaEixo(Graphics g) {
        //Fundo
        g.setColor(Cor.CINZA);
        g.fillRect(inicioLargura, inicioAltura, fimLargura - inicioLargura - 2, fimAltura - inicioAltura);

        //Contornos
        g.setColor(Cor.CINZA_ESCURO);
        g.drawLine(inicioLargura, inicioAltura - 1, fimLargura - 3, inicioAltura - 1);
        g.drawLine(fimLargura - 3, inicioAltura, fimLargura - 3, fimAltura);

        //Eixo X
        g.setColor(Cor.PRETO);
        g.drawLine(inicioLargura - distCol + 1, fimAltura, fimLargura, fimAltura);
        g.drawLine(inicioLargura - distCol + 1, fimAltura + 1, fimLargura, fimAltura + 1);

        //Eixo Y
        g.drawLine(inicioLargura, inicioAltura - distCol, inicioLargura, fimAltura + distCol);
        g.drawLine(inicioLargura + 1, inicioAltura - distCol, inicioLargura + 1, fimAltura + distCol);
    }

    private void desenhaColuna(Graphics g) {
        acumulado = inicioLargura + distCol;

        for (int i = 0; i < tamValor; i++) {
            g.setColor(cor[i]);
            g.fillRect(acumulado, inicioAltura, larguraColuna, fimAltura - inicioAltura);
            g.setColor(Cor.PRETO);
            g.drawRect(acumulado, inicioAltura, larguraColuna, fimAltura - inicioAltura);
            g.setColor(Cor.CINZA);
            g.fillRect(acumulado, inicioAltura, larguraColuna + 1, getTamMaxColuna() - getQtdePixelColuna(valor[i]));
            g.setColor(Cor.PRETO);
            g.drawLine(acumulado, inicioAltura + (getTamMaxColuna() - getQtdePixelColuna(valor[i])), acumulado + larguraColuna, inicioAltura + (getTamMaxColuna() - getQtdePixelColuna(valor[i])));
            acumulado += distCol + larguraColuna;
        }
    }

    private void desenhaLegenda(Graphics g) {
        g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
        acumulado = inicioAltura;
        int tamLegenda = altura / 20;
        for (int i = 0; i < tamValor; i++) {
            g.setColor(cor[i]);
            g.fillRect(5, acumulado, tamLegenda, tamLegenda);
            g.setColor(Cor.PRETO);
            g.drawRect(5, acumulado, tamLegenda, tamLegenda);
            g.drawString(rotulo[i] + " - " + valor[i], tamLegenda * 2, acumulado, Graphics.LEFT | Graphics.TOP);
            acumulado += tamLegenda + distCol;
        }
        acumulado = inicioAltura;
    }

    private void validaInformacoes(Graphics g) {
        if (existeNegativo) {
            erro.append("\nOs valores para o gráfico \ndevem ser positivos\n");
        }
        if (tamRotulo > 10 || tamValor > 10) {
            erro.append("\nO quantidade máxima de valores \ndeve ser 10.\n");
        }
        if (tamRotulo != tamValor) {
            erro.append("\nDiferença de tamanho entre \no array do rótulo e do valor.\n");
        }
        if (erro.length() > 0) {
            validado = false;
        } else {
            validado = true;
        }
        if (!validado) {
            erro.insert(0, "Erro na montagem do gráfico!\n");
            g.setColor(Cor.PRETO);
            g.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
            g.drawString(erro.toString(), 0, 0, Graphics.LEFT | Graphics.TOP);
        }
    }
}
