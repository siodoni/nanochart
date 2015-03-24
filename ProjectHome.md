A principal finalidade do projeto nanochart é entregar ao desenvolvedor de aplicações J2ME um gráfico de coluna ou pizza.
Para o desenvolvimento do projeto está sendo utilizado a classe Canvas.

**Código de exemplo da utilização do NanoChart**

```
import br.com.siodoni.nanochart.util.Cor;
import br.com.siodoni.nanochart.NanoChart;
import javax.microedition.midlet.MIDlet;
import java.util.Random;
import javax.microedition.lcdui.Display;

/**
 * Exemplo de midlet que utiliza a biblioteca NanoChart.<br/>
 * No exemplo foi utilizado o método random.nextInt para a geração de valores aleatórios.
 *
 * @author Flavio Augusto Siodoni Ximenes
 */
public class Midlet extends MIDlet implements Cor {

    /**
     * Método responsável por iniciar a aplicação.
     */
    public void startApp() {
        //Atributo responsavel por gerar valores aleatórios para o exemplo.
        Random random = new Random();

        //Array de cores que será utilizada para a montagem do gráfico.
        int cor[] = {VERMELHO2, VERDE2, AMARELO2, AZUL2, ROXO2,
                     VERMELHO3, VERDE3, AMARELO3, AZUL3, ROXO1};

        //Array de valores que será utilizado para a montagem do gráfico de exemplo.
        int valor[] = new int[10];

        //Array de rotulos que será utilizado para a montagem das legendas do gráfico de exemplo.
        String rotulo[] = new String[valor.length];

        //Título do gráfico de exemplo.
        String titulo = "Exemplo de gráfico aleatório";

        //Tipo de gráfico utilizado. Podendo ser GRAFICO_BARRA ou GRAFICO_PIZZA.
        int tpGrafico = NanoChart.GRAFICO_BARRA;

        //Montando de forma aleatória os valores e as legendas.
        for (int i = 0; i < valor.length; i++) {
            valor[i] = random.nextInt(50) + 1;
            rotulo[i] = "valor aleatório";
        }

        //Instanciando o atributo nanoChart de acordo com as informações fornecidas.
        NanoChart nanoChart = new NanoChart(cor, valor, rotulo, titulo, tpGrafico);

        //Solicitando ao Display que mostre o grafico de acordo com as informações fornecidas.
        Display.getDisplay(this).setCurrent(nanoChart);
    }

    /**
     * Método responsável por pausar a aplicação.
     */
    public void pauseApp() {
    }

    /**
     * Método responsável por destruir a aplicação.
     *
     * @param unconditional
     */
    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
}
```


**Screenshots**

|Gráfico de barras|Gráfico de pizza|Aba Dados|
|:-----------------|:----------------|:--------|
|<img src='http://img511.imageshack.us/img511/2023/graficobarras.png' />|<img src='http://img716.imageshack.us/img716/4180/graficopizza.png' />|<img src='http://img101.imageshack.us/img101/7090/abadados.png' />|