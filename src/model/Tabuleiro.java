package model;

import control.Gomoku;
import static control.Gomoku.icone0;
import static control.Gomoku.matrizBotoes;

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
    
    public void reset(){
        Gomoku.resetJogadorAtual();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                tabuleiro[i][j] = 0;
//                Gomoku.matrizBotoes[i][j].setOpaque(false);
//                Gomoku.matrizBotoes[i][j].setContentAreaFilled(false);
//                Gomoku.matrizBotoes[i][j].setBorderPainted(false);
//                Gomoku.matrizBotoes[i][j].setIcon(icone0);
            }
        }
        
        
//        for (int i = 0; i < tabuleiro.length; i++) {
//            for (int j = 0; j < tabuleiro[0].length; j++) {
//                tabuleiro[i][j] = 0;
//                Gomoku.matrizBotoes[i][j].setIcon(Gomoku.icone0);
//            }
//        }
        Gomoku.partida.tabuleiro.getTabuleiro().repaint();
        Gomoku.partida.tabuleiro.getTabuleiro().revalidate();
    }
}