package control;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Partida;
import model.Tabuleiro;
import view.IConfiguracoes;
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
                matrizBotoes[i][j].setOpaque(false);
                matrizBotoes[i][j].setContentAreaFilled(false);
                matrizBotoes[i][j].setBorderPainted(false);
                matrizBotoes[i][j].setIcon(icone0);
            }
        }
        System.out.println(partida.configuracoes);
        Utils.questComecarNova();
    }

    public static void jogada(int i, int j) {
        if (jogadorAtual == "Jogador1" || roboJogando == false) {
            System.out.println("Jogada de: " + jogadorAtual);
            if (tabuleiro.getPosicao(i, j) == 0) {
                tabuleiro.get()[i][j] = jogadorAtual == "Jogador1" ? (byte) 1 : (byte) 2; //operador ternário
//                matrizBotoes[i][j].setIcon(jogadorAtual == "Jogador1" ? icone1 : icone2);
                matrizBotoes[i][j].setIcon(icone1);
                jogadorAtual = jogadorAtual == "Jogador1" ? "Jogador2" : "Jogador1";

                Utils.verificaGanhador(tabuleiro.get(), i, j);
            } else {
                if (verificaEmpate(tabuleiro.get())) {
                    System.out.println("Empatou!!");
                    JOptionPane.showMessageDialog(null, "Empatou");
                }
                System.out.println("Não foi possivel jogar");
            }
        }

        if (roboJogando && jogadorAtual == "Jogador2") { //robo jogando
            System.out.println("Jogada de: " + jogadorAtual);
            int[] linhaColuna = Utils.jogadaRobo(tabuleiro.get(), i, j, IConfiguracoes.config.getNivelBot().get());//ultimo argumento é o nivel de dificuldade
            //0 é dificil - 1 é médio - 2 é facil
            tabuleiro.get()[linhaColuna[0]][linhaColuna[1]] = 2;
            matrizBotoes[linhaColuna[0]][linhaColuna[1]].setIcon(icone2);
            Utils.verificaGanhador(tabuleiro.get(), linhaColuna[0], linhaColuna[1]);
            jogadorAtual = "Jogador1";//depois da jogada do robo passa a jogada pro player
        }

        if (part.getVencedor() != null) {
            Utils.questComecarNova();
        }
    }

    public static void resetJogadorAtual(){
        Gomoku.jogadorAtual = "Jogador1";
    }

    public static boolean verificaEmpate(byte[][] tabuleiro) {
        boolean empate = true;
        for (int i = 0; i < 15; i++) { //varre o tabuleiro buscando alguma posição vazia pra jogar
            for (int j = 0; j < 15; j++) {//caso estejam todas ocupadas é porque empatou
                if (tabuleiro[i][j] == 0) {
                    empate = false;
                }
            }
        }
        return empate;
    }

    
}
