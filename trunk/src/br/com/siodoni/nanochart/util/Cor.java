package br.com.siodoni.nanochart.util;

/**
 * Interface responsavel por fornecer os codigos hexadecimais das cores.<br/>
 * A intensidade da cor segue o seguinte padrão:
 * <ul>
 *   <li>1 - claro</li>
 *   <li>2 - médio</li>
 *   <li>3 - escuro</li>
 * </ul>
 * Caso a cor desejada não esteja presente na interface, é possivel utilizar
 * o codigo RGB (Red, Green, Blue) hexadecimal, o mesmo padrão utilizado para paginas em html.<br/>
 * Exemplo:<br/>
 * Cor branca
 * <ul>
 *   <li>Código hexadecimal para página html: #FFFFFF</li>
 *   <li>Código hexadecimal para o NanoChart: 0x00FFFFFF</li>
 * </ul>
 *
 * @version 1.0
 * @author Flavio Augusto Siodoni Ximenes
 */
public interface Cor {

    /**
     * cor amarelo claro - valor <code>0x00FFEEAA</code>
     */
    public static final int AMARELO1 = 0x00FFEEAA;
    /**
     * cor amarelo médio - valor <code>0x00FFCC00</code>
     */
    public static final int AMARELO2 = 0x00FFCC00;
    /**
     * cor amarelo escuro - valor <code>0x00AA8800</code>
     */
    public static final int AMARELO3 = 0x00AA8800;
    /**
     * cor azul claro - valor <code>0x00AACCFF</code>
     */
    public static final int AZUL1 = 0x00AACCFF;
    /**
     * cor azul médio - valor <code>0x000066FF</code>
     */
    public static final int AZUL2 = 0x000066FF;
    /**
     * cor azul escuro - valor <code>0x00002255</code>
     */
    public static final int AZUL3 = 0x00002255;
    /**
     * cor branco - valor <code>0x00FFFFFF</code>
     */
    public static final int BRANCO = 0x00FFFFFF;
    /**
     * cor cinza claro - valor <code>0x00EEEEEC</code>
     */
    public static final int CINZA1 = 0x00EEEEEC;
    /**
     * cor cinza médio - valor <code>0x00B4B4B4</code>
     */
    public static final int CINZA2 = 0x00B4B4B4;
    /**
     * cor cinza escuro - valor <code>0x00888A85</code>
     */
    public static final int CINZA3 = 0x00888A85;
    /**
     * cor laranja claro - valor <code>0x00FF8380</code>
     */
    public static final int LARANJA1 = 0x00FF8380;
    /**
     * cor laranja médio - valor <code>0x00FF6600</code>
     */
    public static final int LARANJA2 = 0x00FF6600;
    /**
     * cor laranja escuro - valor <code>0x00AA4400</code>
     */
    public static final int LARANJA3 = 0x00AA4400;
    /**
     * cor marrom claro - valor <code>0x00E9C6AF</code>
     */
    public static final int MARROM1 = 0x00E9C6AF;
    /**
     * cor marrom médio - valor <code>0x00C87137</code>
     */
    public static final int MARROM2 = 0x00C87137;
    /**
     * cor marrom escuro - valor <code>0x00502D16</code>
     */
    public static final int MARROM3 = 0x00502D16;
    /**
     * cor preto - valor <code>0x00000000</code>
     */
    public static final int PRETO = 0x00000000;
    /**
     * cor rosa claro - valor <code>0x00FFAACC</code>
     */
    public static final int ROSA1 = 0x00FFAACC;
    /**
     * cor rosa médio - valor <code>0x00FF0066</code>
     */
    public static final int ROSA2 = 0x00FF0066;
    /**
     * cor rosa escuro - valor <code>0x00550022</code>
     */
    public static final int ROSA3 = 0x00550022;
    /**
     * cor roxo claro - valor <code>0x00C6AFE9</code>
     */
    public static final int ROXO1 = 0x00C6AFE9;
    /**
     * cor roxo médio - valor <code>0x007137C8</code>
     */
    public static final int ROXO2 = 0x007137C8;
    /**
     * cor roxo escuro - valor <code>0x002D1650</code>
     */
    public static final int ROXO3 = 0x002D1650;
    /**
     * cor verde claro - valor <code>0x00AAFFAA</code>
     */
    public static final int VERDE1 = 0x00AAFFAA;
    /**
     * cor verde médio - valor <code>0x0000FF00</code>
     */
    public static final int VERDE2 = 0x0000FF00;
    /**
     * cor verde escuro - valor <code>0x00005500</code>
     */
    public static final int VERDE3 = 0x00005500;
    /**
     * cor vermelho claro - valor <code>0x00FFAAAA</code>
     */
    public static final int VERMELHO1 = 0x00FFAAAA;
    /**
     * cor vermelho médio - valor <code>0x00FF0000</code>
     */
    public static final int VERMELHO2 = 0x00FF0000;
    /**
     * cor vermelho escuro - valor <code>0x00550000</code>
     */
    public static final int VERMELHO3 = 0x00550000;
}
