package principal;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Tabuleiro;
import view.IPartida;

public class Gomoku {
    
    private IPartida partida;
    private static String jogadorAtual = "Jogador1";

    public static Tabuleiro tabuleiro = new Tabuleiro(15);
    public static JButton[][] matrizBotoes = new JButton[15][15];
    
    public static ImageIcon icone1;
    public static ImageIcon icone2;
    
    public Gomoku () {
        partida = new IPartida();
        Gomoku.icone1 = new ImageIcon(getClass().getResource("/001.png"));
        Gomoku.icone2 = new ImageIcon(getClass().getResource("/002.png"));
    }
    
    public static void jogada(int i, int j){
        System.out.println("Jogada de: " + jogadorAtual);
        if (tabuleiro.getPosicao(i, j) == 0) {
            tabuleiro.get()[i][j] = jogadorAtual == "Jogador1" ? (byte)1 : (byte)2; //operador ternário
            matrizBotoes[i][j].setIcon(jogadorAtual == "Jogador1" ? icone1 : icone2);
            jogadorAtual = jogadorAtual == "Jogador1" ? "Jogador2" : "Jogador1";
        } else {
            System.out.println("Não foi possivel jogar");
        }
    }
}
