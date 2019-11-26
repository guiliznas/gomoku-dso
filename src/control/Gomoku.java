package control;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Lance;
import model.Partida;
import model.Tabuleiro;
import view.IConfiguracoes;
import view.IPartida;

public class Gomoku {

    public static IPartida partida;
    public static String jogadorAtual = "Jogador1";
    public static String meuJogador = "Jogador1";

    public static Tabuleiro tabuleiro = new Tabuleiro(15);
    public static JButton[][] matrizBotoes = new JButton[15][15];

    public static ImageIcon icone1;
    public static ImageIcon icone2;
    public static ImageIcon icone0;

    public static Partida part;

    public static Servidor ngServer;
    public static boolean conectado = false;
    public static boolean partidaAndamento = false;

    public Gomoku() {
        ngServer = new Servidor();
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
//        Utils.questComecarNova();
    }
    
    public static void iniciarNovaPartida(Integer posicao) {
        meuJogador = "Jogador"+posicao;
        partidaAndamento = true;
        Utils.questComecarNova();
    }

    public static void jogada(int i, int j) {
        if (!partidaAndamento) {
            JOptionPane.showMessageDialog(null, "Nenhuma partida em andamento");
            return;
        }
        if (jogadorAtual.equals(meuJogador)) {
            System.out.println("Jogada de: " + jogadorAtual);
            if (tabuleiro.getPosicao(i, j) == 0) {
                if (partidaAndamento) {
                    ngServer.realizarJogada(i, j);
                }
                tabuleiro.get()[i][j] = jogadorAtual == "Jogador1" ? (byte) 1 : (byte) 2; //operador ternário
                matrizBotoes[i][j].setIcon(jogadorAtual == "Jogador1" ? icone1 : icone2);
                jogadorAtual = jogadorAtual == "Jogador1" ? "Jogador2" : "Jogador1";

                Utils.verificaGanhador(tabuleiro.get(), i, j);
            } else {
                if (verificaEmpate(tabuleiro.get())) {
                    System.out.println("Empatou!!");
                    JOptionPane.showMessageDialog(null, "Empatou");
                }
                System.out.println("Não foi possivel jogar");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não é a sua vez de jogar");
        }

//        if (part.getVencedor() != null) {
//            Utils.questComecarNova();
//        }
    }

    public static void mudarPosicao(Lance jogada) {
        tabuleiro.get()[jogada.getLinha()][jogada.getColuna()] = jogada.getJogador().equals("Jogador1") ? (byte) 1 : (byte) 2; //operador ternário
        matrizBotoes[jogada.getLinha()][jogada.getColuna()].setIcon(jogada.getJogador().equals("Jogador1") ? icone1 : icone2);
        jogadorAtual = jogada.getJogador().equals("Jogador1") ? "Jogador2" : "Jogador1";
        
        Utils.verificaGanhador(tabuleiro.get(), jogada.getLinha(), jogada.getColuna());
    }

    public static void resetJogadorAtual() {
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

    // Netgames
    public static String conectar(String string, String string2) {
        String mensagem = "Não foi possível iniciar"; // Definir condições
        boolean permitido = true;
        if (permitido) {
            mensagem = ngServer.conectar(string, string2);
            if (mensagem.equals("Conectado com sucesso!")) {
                conectado = true;
            }
        }
        return mensagem;
    }

    public static String desconectar() {
        String mensagem = "Não foi possível desconectar"; // Definir condições
        boolean permitido = true;
        if (permitido) {
            mensagem = ngServer.desconectar();
            if (mensagem.equals("Desconectado com sucesso!")) {
                conectado = false;
            }
        }
        return mensagem;
    }

    public static String iniciarPartida() {
        String mensagem = "Não foi possível iniciar"; // Definir condições
        boolean permitido = true;
        if (permitido) {
            mensagem = ngServer.iniciarPartida();
        }
        return mensagem;
    }
}
