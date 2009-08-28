package br.com.siodoni.nanochart;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author Flavio Augusto Siodoni Ximenes
 */
public class Chart extends MIDlet{

    private Canvas canvas;

    public Chart() {
        //canvas = new PieChart(this);
        canvas = new BarChart(this);
    }

    public void startApp() {
        Display.getDisplay(this).setCurrent(canvas);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
}
