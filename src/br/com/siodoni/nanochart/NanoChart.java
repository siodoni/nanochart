package br.com.siodoni.nanochart;

import javax.microedition.lcdui.*;

/**
 * //TODO melhorar a documentação aqui...
 * @version 1.0
 * @author Flavio Augusto Siodoni Ximenes
 */
public class NanoChart extends Canvas implements CommandListener {

    private Midlet chart;
    private Command cmdSair;
    private int largura, altura, inicioAltura, fimAltura, inicioLargura, fimLargura, areaTotal, distCol, acumulado, larguraColuna, percLargura, tamRotulo, tamValor, maiorValor, tipoGrafico;
    private int valorInt[], cor[], posTab[] = new int[8];
    private int tamTab, altTab, largTab, angTab;
    private float valorFloat[];
    private double valorDouble[];
    private String rotulo[];
    private String titulo;
    private StringBuffer erro = new StringBuffer();
    private boolean grafico = true, validado = false, existeNegativo = false;
    private char construtor;
    private Font fontePeq = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL);
    private Font fonteMed = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
    private Font fonteGde = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_LARGE);
    /**
     * Constante para o tipo de grafico de barra - valor <code>0</code>
     */
    public static final int GRAFICO_BARRA = 0;
    /**
     * Constante para o tipo de grafico de pizza - valor <code>1</code>
     */
    public static final int GRAFICO_PIZZA = 1;

    /**
     * //TODO melhorar a documentação aqui...
     * @param midlet
     * @param cor
     * @param valor
     * @param rotulo
     * @param titulo
     * @param tipoGrafico
     */
    public NanoChart(Midlet midlet, int cor[], int valor[], String rotulo[], String titulo, int tipoGrafico) {
        this.chart = midlet;
        this.cor = cor;
        this.valorInt = valor;
        this.rotulo = rotulo;
        this.titulo = titulo;
        this.tipoGrafico = tipoGrafico;
        construtor = 'i'; //int
        inicializa();
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @param midlet
     * @param cor
     * @param valor
     * @param rotulo
     * @param titulo
     * @param tipoGrafico
     */
    public NanoChart(Midlet midlet, int cor[], double valor[], String rotulo[], String titulo, int tipoGrafico) {
        this.chart = midlet;
        this.cor = cor;
        this.valorInt = Util.doubleToInt(valor);
        this.valorDouble = valor;
        this.rotulo = rotulo;
        this.titulo = titulo;
        this.tipoGrafico = tipoGrafico;
        construtor = 'd'; //double
        inicializa();
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @param midlet
     * @param cor
     * @param valor
     * @param rotulo
     * @param titulo
     * @param tipoGrafico
     */
    public NanoChart(Midlet midlet, int cor[], float valor[], String rotulo[], String titulo, int tipoGrafico) {
        this.chart = midlet;
        this.cor = cor;
        this.valorInt = Util.floatToInt(valor);
        this.valorFloat = valor;
        this.rotulo = rotulo;
        this.titulo = titulo;
        this.tipoGrafico = tipoGrafico;
        construtor = 'f'; //float
        inicializa();
    }

    /**
     * //TODO melhorar a documentação aqui...
     */
    private void inicializa() {
        cmdSair = new Command("Sair", Command.EXIT, 0);
        addCommand(cmdSair);
        setCommandListener(this);
        setFullScreenMode(true);

        tamRotulo = rotulo.length;
        tamValor = valorInt.length;
        maiorValor = getMaiorValor();
        percLargura = 5;

        largura = getWidth();
        altura = getHeight();
        //TODO inicioAltura precisa ser melhorado, pois em alguns aparelhos o inicio do grafico está sobrepondo as tabs...
        inicioAltura = ((altura * 25) / 100) + (largura / 32); // % no inicio do eixo Y
        inicioLargura = (largura * percLargura) / 100; // % no inicio do eixo X
        fimAltura = (altura - inicioLargura);
        fimLargura = largura - inicioLargura;
        distCol = (largura * 2) / 100; // % entre as colunas
        acumulado = inicioLargura + distCol;
        areaTotal = fimLargura - inicioLargura;
        larguraColuna = (areaTotal / valorInt.length) - distCol;

        //Definindo os tamanhos da tab.
        tamTab = largura / 7;
        altTab = largura / 8;
        largTab = largura / 2;
        angTab = largura / 20;
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @param g
     */
    protected void paint(Graphics g) {
        //Pinta o fundo de branco
        g.setColor(Cor.BRANCO);
        g.fillRect(0, 0, largura, altura);

        //Validando as informações
        validaInformacoes(g);

        if (validado) {
            //Desenhando o titulo
            g.setColor(Cor.PRETO);
            g.setFont(fonteGde);
            g.drawString(titulo, largura / 2, distCol, Graphics.HCENTER | Graphics.TOP);

            //Desenhando a tabpanel
            desenhaTabPanel(g);

            if (this.grafico) {

                if (this.tipoGrafico == NanoChart.GRAFICO_BARRA) {
                    //Desenhando os eixos
                    desenhaEixo(g);
                    //Desenhando as colunas
                    desenhaColuna(g);
                } else if (this.tipoGrafico == NanoChart.GRAFICO_PIZZA) {
                    //Desenhando o grafico de pizza
                    desenhaPizza(g);
                }
            } else {
                //Desenhando a tabela de legenda
                desenhaLegenda(g);
            }
        } else {
        }
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @param c
     * @param d
     */
    public void commandAction(Command c, Displayable d) {
        if (c == cmdSair) {
            chart.destroyApp(false);
        }
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @param keyCode
     */
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

    /**
     * //TODO melhorar a documentação aqui...
     * @param x
     * @param y
     */
    protected void pointerPressed(int x, int y) {
        if ((x >= posTab[0] && x <= posTab[2]) && (y >= posTab[1] && y <= posTab[3])) {
            grafico = true;
            repaint();
            serviceRepaints();
        } else if ((x >= posTab[4] && x <= posTab[6]) && (y >= posTab[5] && y <= posTab[7])) {
            grafico = false;
            repaint();
            serviceRepaints();
        }
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @return
     */
    private int getMaiorValor() {
        int maior = 0, valorAtual = 0;
        for (int i = 0; i < valorInt.length; i++) {
            if (valorInt[i] < 0) {
                existeNegativo = true;
            }
            valorAtual = valorInt[i];
            if (valorAtual > maior) {
                maior = valorAtual;
            }
        }
        return maior;
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @return
     */
    private int getTamMaxColuna() {
        return fimAltura - inicioAltura;
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @param valor
     * @return
     */
    private int getQtdePixelColuna(int valor) {
        return (valor * getTamMaxColuna()) / maiorValor;
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @param linha
     * @return
     */
    private String getValorLegenda(int linha) {
        String valor = "";
        if (construtor == 'i') {
            valor = (String.valueOf(valorInt[linha]));
        } else if (construtor == 'd') {
            valor = (String.valueOf(valorDouble[linha]));
        } else if (construtor == 'f') {
            valor = (String.valueOf(valorFloat[linha]));
        }
        return valor;
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @param g
     */
    private void desenhaTabPanel(Graphics g) {
        if (grafico) {
            g.setColor(Cor.CINZA1);
            g.fillRoundRect(largTab + 1, tamTab, largTab - 3, altTab, angTab, angTab);
        } else {
            g.setColor(Cor.CINZA1);
            g.fillRoundRect(1, tamTab, largTab, altTab, angTab, angTab);
        }

        g.setColor(Cor.PRETO);
        g.drawRoundRect(1, tamTab, largTab, altTab, angTab, angTab);
        g.drawRoundRect(largTab + 1, tamTab, largTab - 3, altTab, angTab, angTab);

        //Guardando as posições.
        //Tab Gráfico
        posTab[0] = 1;                       // X
        posTab[1] = tamTab;                  // Y
        posTab[2] = posTab[0] + largTab;     // largura
        posTab[3] = posTab[1] + altTab;      // altura
        //Tab Dados
        posTab[4] = largTab + 1;             // X
        posTab[5] = tamTab;                  // Y
        posTab[6] = posTab[2] + largTab - 3; // largura
        posTab[7] = posTab[5] + altTab;      // altura

        g.drawRect(0, altTab * 2, largura - 1, altura);
        g.setColor(Cor.BRANCO);

        if (this.grafico) {
            g.fillRect(1, altTab * 2, largTab, altura);
        } else {
            g.fillRect(largTab + 1, altTab * 2, largTab - 2, altura);
        }

        g.fillRect(1, (altTab * 2) + 1, largura - 2, altura);
        g.setColor(Cor.PRETO);
        g.setFont(fontePeq);
        g.drawString(" Gráfico", distCol, tamTab + distCol, Graphics.LEFT | Graphics.TOP);
        g.drawString(" Dados", largTab + distCol, tamTab + distCol, Graphics.LEFT | Graphics.TOP);
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @param g
     */
    private void desenhaEixo(Graphics g) {
        //Fundo
        g.setColor(Cor.CINZA1);
        g.fillRect(inicioLargura, inicioAltura, fimLargura - inicioLargura - 2, fimAltura - inicioAltura);

        //Contornos
        g.setColor(Cor.CINZA3);
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

    /**
     * //TODO melhorar a documentação aqui...
     * @param g
     */
    private void desenhaColuna(Graphics g) {
        acumulado = inicioLargura + distCol;

        for (int i = 0; i < tamValor; i++) {
            g.setColor(cor[i]);
            g.fillRect(acumulado, inicioAltura, larguraColuna, fimAltura - inicioAltura);
            g.setColor(Cor.PRETO);
            g.drawRect(acumulado, inicioAltura, larguraColuna, fimAltura - inicioAltura);
            g.setColor(Cor.CINZA1);
            g.fillRect(acumulado, inicioAltura, larguraColuna + 1, getTamMaxColuna() - getQtdePixelColuna(valorInt[i]));
            g.setColor(Cor.PRETO);
            g.drawLine(acumulado, inicioAltura + (getTamMaxColuna() - getQtdePixelColuna(valorInt[i])), acumulado + larguraColuna, inicioAltura + (getTamMaxColuna() - getQtdePixelColuna(valorInt[i])));
            acumulado += distCol + larguraColuna;
        }
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @param g
     */
    private void desenhaPizza(Graphics g) {
        int anguloAcumulado = 0, soma = 0, angulo = 0, posicaoMaiorVlr = 0;
        int valorAngulo[] = new int[tamValor];

        for (int i = 0; i < tamValor; i++) {
            soma += valorInt[i];
        }

        for (int i = 0; i < tamValor; i++) {
            angulo = (soma / valorInt[i]);
            valorAngulo[i] = 360 / angulo;
            if (valorInt[i] == getMaiorValor()) {
                posicaoMaiorVlr = i;
            }
        }

        soma = 0;

        //TODO melhorar essa parte pois o maior valor está sendo alterado arbitrariamente, deverá ser feito um rateio.
        for (int i = 0; i < tamValor; i++) {
            soma += valorAngulo[i];
        }

        if (soma > 360) {
            valorAngulo[posicaoMaiorVlr] -= (soma - 360);
        } else if (soma < 360) {
            valorAngulo[posicaoMaiorVlr] += Math.abs(soma - 360);
        }

        for (int i = 0; i < tamValor; i++) {
            g.setColor(cor[i]);
            g.fillArc(inicioLargura, inicioAltura, fimLargura - inicioLargura, fimLargura - inicioLargura, anguloAcumulado, valorAngulo[i]);
            g.setColor(Cor.PRETO);
            anguloAcumulado += valorAngulo[i];
        }
        g.setColor(Cor.PRETO);
        g.drawArc(inicioLargura, inicioAltura, fimLargura - inicioLargura, fimLargura - inicioLargura, 0, 360);
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @param g
     */
    private void desenhaLegenda(Graphics g) {
        g.setFont(fontePeq);
        acumulado = inicioAltura;
        int tamLegenda = fontePeq.getHeight();//altura / 20;

        for (int i = 0; i < tamValor; i++) {
            g.setColor(cor[i]);
            g.fillRect(5, acumulado, tamLegenda, tamLegenda);
            g.setColor(Cor.PRETO);
            g.drawRect(5, acumulado, tamLegenda, tamLegenda);
            g.drawString(rotulo[i] + " - " + getValorLegenda(i), tamLegenda * 2, acumulado, Graphics.LEFT | Graphics.TOP);
            acumulado += tamLegenda + distCol;
        }
        acumulado = inicioAltura;
    }

    /**
     * //TODO melhorar a documentação aqui...
     * @param g
     */
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
            g.setFont(fonteMed);
            g.drawString(erro.toString(), 0, 0, Graphics.LEFT | Graphics.TOP);
        }
    }
}