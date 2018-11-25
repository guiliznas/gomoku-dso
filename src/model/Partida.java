package model;

import java.io.Serializable;

public class Partida implements Serializable {

    private Jogador jogador1;
    private Jogador jogador2;
    private String vencedor;
    private long duracao;
    private String data;

    public Partida() {
        
    }

    public Partida(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
    }

    public Jogador getJogador1(){
        return jogador1;
    }

    public Jogador getJogador2(){
        return jogador2;
    }

    public boolean setVencedor(String nome){
        this.vencedor = nome;
        return true;
    }

    public String getVencedor(){
        return vencedor;
    }
    
    public boolean setData(String data){
        this.data = data;
        return true;
    }
    
    public String getData(){
        return data;
    }
    
    public boolean setDuracao(long duracao){
        this.duracao = duracao;
        return true;
    }
    
    public long getDuracao(){
        return duracao;
    }

    @Override
    public String toString() {
        return "\nPartida{" + "jogador1=" + jogador1 + ", jogador2=" + jogador2 + ", vencedor=" + vencedor + ", duracao=" + duracao + ", data=" + data + '}';
    }

}