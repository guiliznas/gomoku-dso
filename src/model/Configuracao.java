package model;

import control.Serializer;
import java.awt.Color;

public class Configuracao {
    
    private String nome1 = "Jogador 1";
    private String nome2 = "Jogador 2";
    private Color corTabuleiro;
    private Color corPartida;
    private int tamanhoTabuleiro = 15;

    public Configuracao(){
        
    }
    
    public void load(){
        Serializer s = new Serializer();
        Configuracao c = s.readConfig();
        if (c != null) {
            this.nome1 = c.getNome1();
            this.nome2 = c.getNome2();
            this.corPartida = c.getCorPartida();
            this.corTabuleiro = c.getCorTabuleiro();
            this.tamanhoTabuleiro = c.getTamanhoTabuleiro();
        }
    }

    public String getNome1() {
        return nome1;
    }

    public void setNome1(String nome1) {
        this.nome1 = nome1;
    }

    public String getNome2() {
        return nome2;
    }

    public void setNome2(String nome2) {
        this.nome2 = nome2;
    }

    public Color getCorTabuleiro() {
        return corTabuleiro;
    }

    public void setCorTabuleiro(Color corTabuleiro) {
        this.corTabuleiro = corTabuleiro;
    }

    public Color getCorPartida() {
        return corPartida;
    }

    public void setCorPartida(Color corPartida) {
        this.corPartida = corPartida;
    }

    public int getTamanhoTabuleiro() {
        return tamanhoTabuleiro;
    }

    public void setTamanhoTabuleiro(int tamanhoTabuleiro) {
        this.tamanhoTabuleiro = tamanhoTabuleiro;
    }

    @Override
    public String toString() {
        return "Configuracao{" + "nome1=" + nome1 + ", nome2=" + nome2 + ", corTabuleiro=" + corTabuleiro + ", corPartida=" + corPartida + ", tamanhoTabuleiro=" + tamanhoTabuleiro + '}';
    }
    
}