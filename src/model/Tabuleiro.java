package model;

public class Tabuleiro {
    byte[][] tabuleiro;

    public Tabuleiro(int tamanho){
        tabuleiro = new byte[tamanho][tamanho];
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = 0;
            }
        }
    }

    public byte getPosicao(int x, int y){
        return tabuleiro[x][y];
    }
    
    public byte[][] get(){
        return tabuleiro;
    }
}