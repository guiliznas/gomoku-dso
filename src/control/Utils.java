package control;

import java.util.Random;

public class Utils {
    
    public static final int VERIFICA_PRA_CIMA = 0;
    public static final int VERIFICA_PRA_BAIXO = 1;
    public static final int VERIFICA_PRA_ESQUERDA = 2;
    public static final int VERIFICA_PRA_DIREITA = 3;
    public static final int VERIFICA_DIAGONAL_CIMA = 4;
    public static final int VERIFICA_DIAGONAL_BAIXO = 5;
    public static final int VERIFICA_SECUNDARIA_CIMA = 6;
    public static final int VERIFICA_SECUNDARIA_BAIXO = 7;
    
    public static Random gerador = new Random();
    
    public static void questComecarNova() {
        Gomoku.partida.novaPartida();
        Gomoku.partida.tabuleiro.getTabuleiro().repaint();
        Gomoku.partida.tabuleiro.getTabuleiro().revalidate();
    }
    
    
    public static int verificaGanhador(byte[][] tabuleiro, int i, int j) {

        return 0;
    }

    public static int verificaPosicoes(byte[][] tabuleiro, int i, int j, int lugar) {
        return 0;
    }
}