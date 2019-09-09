package control;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Partida;
import model.Tabuleiro;
import view.IPartida;

public class Gomoku {

    public static IPartida partida;
    private static String jogadorAtual = "Jogador1";

    public static Tabuleiro tabuleiro = new Tabuleiro(15);
    public static JButton[][] matrizBotoes = new JButton[15][15];

    public static ImageIcon icone1;
    public static ImageIcon icone2;
    public static ImageIcon icone0;

    public static boolean roboJogando = false;    

    public static Partida part;

    public Gomoku() {
        partida = new IPartida();
        Gomoku.icone1 = new ImageIcon(getClass().getResource("/001_.png"));
        Gomoku.icone2 = new ImageIcon(getClass().getResource("/002_.png"));
        Gomoku.icone0 = new ImageIcon(getClass().getResource("/000.png"));
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
//                matrizBotoes[i][j].setOpaque(false);
//                matrizBotoes[i][j].setContentAreaFilled(false);
//                matrizBotoes[i][j].setBorderPainted(false);
//                matrizBotoes[i][j].setIcon(icone0);
            }
        }
        System.out.println(partida.configuracoes);
        Utils.questComecarNova();
    }

    public static void jogada(int i, int j) {
        if (jogadorAtual == "Jogador1") {
            System.out.println("Jogada de: " + jogadorAtual);
            if (tabuleiro.getPosicao(i, j) == 0) {
                tabuleiro.get()[i][j] = jogadorAtual == "Jogador1" ? (byte) 1 : (byte) 2; //operador ternário
                matrizBotoes[i][j].setIcon(icone1);
            } else {
                if (verificaEmpate(tabuleiro.get())) {
                    System.out.println("Empatou!!");
                    JOptionPane.showMessageDialog(null, "Empatou");
                }
                System.out.println("Não foi possivel jogar");
            }
        }
        if (part.getVencedor() != null) {
            Utils.questComecarNova();
        }
    }

    public static void resetJogadorAtual(){
        Gomoku.jogadorAtual = "Jogador1";
    }

    public static boolean verificaEmpate(byte[][] tabuleiro) {
        return false;
    }

    
}
