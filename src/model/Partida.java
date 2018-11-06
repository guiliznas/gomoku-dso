package model;

public class Partida {

    private Jogador jogador1;
    private Jogador jogador2;
    private String vencedor;

    public Partida() {

    }

    public Partida(Jogador jogador1, Jogador jogador2) {
        
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

}