package br.com.siodoni.nanochart;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Texto {

    private String texto;
    private int x, y;
    private String fonte;
    private Image imgFonte;

    public Texto(String texto, int x, int y, String fonte) {
        this.x = x;
        this.y = y;
        this.setFonte(fonte);
        this.setTexto(texto);
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
        try {
            imgFonte = Image.createImage("" + fonte);
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem");
        }
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {
        int tamLetra;
        tamLetra = imgFonte.getWidth() / 94;
        for (int i = 0; i < this.getTexto().length(); i++) {
            g.drawRegion(imgFonte, (this.getTexto().substring(i, i + 1).hashCode() - 32) * tamLetra, 0, tamLetra, imgFonte.getHeight(), 0, this.getX() + (i * tamLetra), this.getY(), 0);
        }
    }
}
