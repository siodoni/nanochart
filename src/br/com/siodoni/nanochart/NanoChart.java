package br.com.siodoni.nanochart;

import br.com.siodoni.nanochart.util.Util;
import br.com.siodoni.nanochart.util.Cor;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 * Classe responsável por montar o grafico de barra ou pizza de acordo com as
 * informações recebidas pelos 3 construtores disponíveis.<br/>
 * Exemplo de uma MIDlet utilizando o NanoChart.
 * <blockquote>
 * <pre>
 * import br.com.siodoni.nanochart.util.Cor;
 * import br.com.siodoni.nanochart.NanoChart;
 * import javax.microedition.midlet.MIDlet;
 * import java.util.Random;
 * import javax.microedition.lcdui.Display;
 * 
 * public class Midlet extends MIDlet implements Cor {
 *
 *     public void startApp() {
 *         //Atributo responsavel por gerar valores aleatórios para o exemplo.
 *         Random random = new Random();
 *
 *         //Array de cores que será utilizada para a montagem do gráfico.
 *         int cor[] = {VERMELHO2, VERDE2, AMARELO2, AZUL2, ROXO2, VERMELHO3, VERDE3, AMARELO3, AZUL3, ROXO3};
 *
 *         //Array de valores que será utilizado para a montagem do gráfico de exemplo.
 *         int valor[] = new int[10];
 *
 *         //Array de rotulos que será utilizado para a montagem das legendas do gráfico de exemplo.
 *         String rotulo[] = new String[valor.length];
 *
 *         //Título do gráfico de exemplo.
 *         String titulo = "Exemplo de gráfico aleatório";
 *
 *         //Tipo de gráfico utilizado. Podendo ser GRAFICO_BARRA ou GRAFICO_PIZZA.
 *         int tpGrafico = NanoChart.GRAFICO_BARRA;
 *
 *         //Montando de forma aleatória os valores e as legendas.
 *         for (int i = 0; i <bloquote><</bloquote> valor.length; i++) {
 *             valor[i] = random.nextInt(50) + 1;
 *             rotulo[i] = "valor aleatório";
 *         }
 *
 *         //Instanciando o atributo nanoChart de acordo com as informações fornecidas.
 *         NanoChart nanoChart = new NanoChart(cor, valor, rotulo, titulo, tpGrafico);
 *
 *         //Solicitando ao Display que mostre o grafico de acordo com as informações fornecidas.
 *         Display.getDisplay(this).setCurrent(nanoChart);
 *     }
 *
 *     public void pauseApp() {
 *     }
 *
 *     public void destroyApp(boolean unconditional) {
 *         notifyDestroyed();
 *     }
 * }
 * </pre>
 * </blockquote>
 *
 * @version 1.0
 * @author Flavio Augusto Siodoni Ximenes
 */
public class NanoChart extends Canvas {

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
     * Constante para a quantidade máxima de colunas do grafico - valor <code>10</code>
     */
    private static final int QTDE_MAX_VALOR = 10;

    /**
     * Construtor da classe NanoChart para valores int.
     *
     * @param cor informe o array de cores para a montagem do grafico.
     * @param valor informe o array de valores para a montagem do grafico.
     * @param rotulo informe o array de rotulos para a montagem do grafico.
     * @param titulo informe o titulo do grafico.
     * @param tipoGrafico informe o tipo de grafico, podendo ser <code>NanoChart.GRAFICO_BARRA</code> ou <code>NanoChart.GRAFICO_PIZZA</code>.
     * @see br.com.siodoni.nanochart.util.Cor
     */
    public NanoChart(int cor[], int valor[], String rotulo[], String titulo, int tipoGrafico) {
        this.cor = cor;
        this.valorInt = valor;
        this.rotulo = rotulo;
        this.titulo = titulo;
        this.tipoGrafico = tipoGrafico;
        construtor = 'i'; //int
        inicializa();
    }

    /**
     * Construtor da classe NanoChart para valores double.
     *
     * @param cor informe o array de cores para a montagem do grafico.
     * @param valor informe o array de valores para a montagem do grafico.
     * @param rotulo informe o array de rotulos para a montagem do grafico.
     * @param titulo informe o titulo do grafico.
     * @param tipoGrafico informe o tipo de grafico, podendo ser <code>NanoChart.GRAFICO_BARRA</code> ou <code>NanoChart.GRAFICO_PIZZA</code>.
     * @see br.com.siodoni.nanochart.util.Cor
     */
    public NanoChart(int cor[], double valor[], String rotulo[], String titulo, int tipoGrafico) {
        this.cor = cor;
        this.valorInt = Util.doubleParaInt(valor);
        this.valorDouble = valor;
        this.rotulo = rotulo;
        this.titulo = titulo;
        this.tipoGrafico = tipoGrafico;
        construtor = 'd'; //double
        inicializa();
    }

    /**
     * Construtor da classe NanoChart para valores float.
     *
     * @param cor informe o array de cores para a montagem do grafico.
     * @param valor informe o array de valores para a montagem do grafico.
     * @param rotulo informe o array de rotulos para a montagem do grafico.
     * @param titulo informe o titulo do grafico.
     * @param tipoGrafico informe o tipo de grafico, podendo ser <code>NanoChart.GRAFICO_BARRA</code> ou <code>NanoChart.GRAFICO_PIZZA</code>.
     * @see br.com.siodoni.nanochart.util.Cor
     */
    public NanoChart(int cor[], float valor[], String rotulo[], String titulo, int tipoGrafico) {
        this.cor = cor;
        this.valorInt = Util.floatParaInt(valor);
        this.valorFloat = valor;
        this.rotulo = rotulo;
        this.titulo = titulo;
        this.tipoGrafico = tipoGrafico;
        construtor = 'f'; //float
        inicializa();
    }

    /**
     * Método responsavel por inicializar todas os atributos que serão
     * utilizados para a montagem do gráfico.
     */
    private void inicializa() {
        setFullScreenMode(true);

        tamRotulo = rotulo.length;
        tamValor = valorInt.length;
        maiorValor = getMaiorValor();
        percLargura = 5;

        largura = getWidth();
        altura = getHeight();
        //TODO *** inicioAltura precisa ser melhorado, pois em alguns aparelhos o inicio do grafico está sobrepondo as tabs...
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
     * Método responsável por "desenhar" o gráfico na tela do aparelho móvel.
     *
     * @param g instancia para a classe Graphics.
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
            desenhaTab(g);

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
     * Método responsavel pela navegação das abas do NanoChart pelo teclado.
     *
     * @param keyCode codigo da tecla.
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
     * Método responsavel pela navegação das abas do NanoChart pelo tela touch screen.
     *
     * @param x localização no eixo x da tela.
     * @param y localização no eixo y da tela.
     */
    protected void pointerPressed(int x, int y) {
        if (hasPointerEvents()) {
            if ((x >= posTab[0] && x <= posTab[2]) && (y >= posTab[1] && y <= posTab[3])) {
                grafico = true;
                repaint();
            } else if ((x >= posTab[4] && x <= posTab[6]) && (y >= posTab[5] && y <= posTab[7])) {
                grafico = false;
                repaint();
            }
        }
    }

    /**
     * Método responsável por retornar o maior valor do array de valores.
     *
     * @return maior valor do array de valores.
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
     * Método responsavel por retornar o tamanho de uma coluna.
     *
     * @return valor maximo de uma coluna.
     */
    private int getTamMaxColuna() {
        return fimAltura - inicioAltura;
    }

    /**
     * Método responsavel por retornar a quantidade de pixel de uma coluna.
     *
     * @param valor
     * @return quantidade de pixels de uma coluna.
     */
    private int getQtdePixelColuna(int valor) {
        return (valor * getTamMaxColuna()) / maiorValor;
    }

    /**
     * Método responsavel por retornar o valor da legenda de acordo com o
     * construtor escolhido.
     *
     * @param linha numero da linha do valor da legenda.
     * @return valor da legenda.
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
     * Método responsavel por desenhar as tabs "Grafico" e "Dados.<br/>
     * No momento da elaboração das tabs são armazenados as posições das mesmas
     * para que o NanoChart tenha suporte a aparelhos com telas touch screen.
     *
     * @param g instancia para a classe Graphics.
     */
    private void desenhaTab(Graphics g) {
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

        //Guardando as posições para a utlização no método pointerPressed.
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
     * Método responsável por desenhar as linhas dos eixos X e Y do gráfico de barras.
     *
     * @param g instancia para a classe Graphics.
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
     * Método responsável por desenhar o grafico de barras.
     *
     * @param g instancia para a classe Graphics.
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
     * Método responsável por desenhar o grafico de pizza.
     *
     * @param g instancia para a classe Graphics.
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
     * Método responsavel por desenhar a legenda que aparecerá na aba "Dados".
     *
     * @param g instancia para a classe Graphics.
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
     * Método responsavel por validar as informações passadas no construtor da classe.<br/>
     * São feitas as seguintes validações:
     * <ul>
     *   <li>Verifica se os valores informados são positivos.</li>
     *   <li>Verifica se a quantidade de valores não ultrapassou 10 posições.</li>
     *   <li>Verifica se o tamanho do array do rótulo é igual ao do valor.</li>
     * </ul>
     *
     * @param g instancia para a classe Graphics.
     */
    private void validaInformacoes(Graphics g) {
        if (existeNegativo) {
            erro.append("\nOs valores para o gráfico \ndevem ser positivos\n");
        }
        if (tamRotulo > 10 || tamValor > QTDE_MAX_VALOR) {
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
