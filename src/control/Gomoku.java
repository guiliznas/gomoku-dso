package control;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Configuracao;
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
    
    public static final int VERIFICA_PRA_CIMA = 0;
    public static final int VERIFICA_PRA_BAIXO = 1;
    public static final int VERIFICA_PRA_ESQUERDA = 2;
    public static final int VERIFICA_PRA_DIREITA = 3;
    public static final int VERIFICA_DIAGONAL_CIMA = 4;
    public static final int VERIFICA_DIAGONAL_BAIXO = 5;
    public static final int VERIFICA_SECUNDARIA_CIMA = 6;
    public static final int VERIFICA_SECUNDARIA_BAIXO = 7;
    
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
    }
   
    public static int verificarVencedor(byte[][] tabuleiro, int i, int j) {
        int contadorCimaBaixo;
        int contadorEsquerdaDireita;
        int contadorDiagonalPrincipal;
        int contadorDiagonalSecundaria;

        contadorCimaBaixo = verificarPosicoes(tabuleiro, i, j, VERIFICA_PRA_CIMA);
        contadorCimaBaixo += verificarPosicoes(tabuleiro, i, j, VERIFICA_PRA_BAIXO);

        contadorEsquerdaDireita = verificarPosicoes(tabuleiro, i, j, VERIFICA_PRA_ESQUERDA);
        contadorEsquerdaDireita += verificarPosicoes(tabuleiro, i, j, VERIFICA_PRA_DIREITA);

        contadorDiagonalPrincipal = verificarPosicoes(tabuleiro, i, j, VERIFICA_DIAGONAL_CIMA);
        contadorDiagonalPrincipal += verificarPosicoes(tabuleiro, i, j, VERIFICA_DIAGONAL_BAIXO);

        contadorDiagonalSecundaria = verificarPosicoes(tabuleiro, i, j, VERIFICA_SECUNDARIA_CIMA);
        contadorDiagonalSecundaria += verificarPosicoes(tabuleiro, i, j, VERIFICA_SECUNDARIA_BAIXO);

        if (contadorDiagonalSecundaria >= 4 || contadorDiagonalPrincipal >= 4 || contadorEsquerdaDireita >= 4 || contadorCimaBaixo >= 4) {//4 porque considera a propria peca 
            IConfiguracoes.config.load();
            Configuracao c = IConfiguracoes.config;

            if (tabuleiro[i][j] == 1) {
                JOptionPane.showMessageDialog(null, "Ganhou o jogador " + c.getNome1());
                System.out.println("Ganhou o Jogador" + c.getNome1());
            } else if (tabuleiro[i][j] == 2) {
                JOptionPane.showMessageDialog(null, "Ganhou o jogador " + c.getNome2());
                System.out.println("Ganhou o Jogador" + c.getNome2());
            }
            Serializer s = new Serializer();
            try {
                part.setVencedor(tabuleiro[i][j] == 1 ? part.getJogador1().getNome() : part.getJogador2().getNome());
            } catch (Exception ex) {
                System.out.println(ex);
            }
            s.addPartida(part);
            Gomoku.partidaAndamento = false;
            return tabuleiro[i][j];//retorna o jogador ganhador
        } else {
            return 0; //ninguém ganhou
        }
    }

    public static byte getPosicao(byte[][] tabuleiro, int i, int j) {
        return tabuleiro[i][j];
    }
    
    public static int verificarPosicoes(byte[][] tabuleiro, int i, int j, int lugar) {
        boolean continua = true;
        int posicao = 0;
        int contadorSucesso = 0;
        byte valor = 0;
        int count = 0;

        while (continua) {
            posicao++;
            valor = 0;//é necessário apagar o valor senão estraga a lógica e entra em looping

            switch (lugar) {
                case VERIFICA_PRA_CIMA:
                    if (i - posicao >= 0) {//verifiquei com if antes de ler na matriz para não dar arrayoutofbounds
                        valor = getPosicao(tabuleiro,i - posicao,j);
                    }
                    break;
                case VERIFICA_PRA_BAIXO:
                    if (i + posicao < 15) {
                        valor = getPosicao(tabuleiro,i + posicao,j);
                    }
                    break;
                case VERIFICA_PRA_ESQUERDA:
                    if ((j - posicao) >= 0) {
                        valor = getPosicao(tabuleiro,i,j - posicao);
                    }
                    break;
                case VERIFICA_PRA_DIREITA:
                    if ((j + posicao) < 15) {
                        valor = getPosicao(tabuleiro,i,j + posicao);
                    }
                    break;
                case VERIFICA_DIAGONAL_CIMA:
                    if ((i - posicao >= 0) && (j - posicao >= 0)) {
                        valor = getPosicao(tabuleiro, i - posicao, j - posicao);
                    }
                    break;
                case VERIFICA_DIAGONAL_BAIXO:
                    if ((i + posicao < 15) && (j + posicao < 15)) {
                        valor = getPosicao(tabuleiro, i + posicao, j + posicao);
                    }
                    break;
                case VERIFICA_SECUNDARIA_CIMA:
                    if ((i - posicao >= 0) && (j + posicao < 15)) {
                        valor = getPosicao(tabuleiro, i - posicao, j + posicao);
                    }
                    break;
                case VERIFICA_SECUNDARIA_BAIXO:
                    if ((i + posicao < 15) && (j - posicao >= 0)) {
                        valor = getPosicao(tabuleiro, i + posicao, j - posicao);
                    }
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }

            if (tabuleiro[i][j] == valor) {
                contadorSucesso++;
                count++;
                if (count > 50) {
                    continua = false;
                }
            } else {
                continua = false;
            }
        }
        return contadorSucesso;
    }
    
    public static void iniciarNovaPartida(Integer posicao) {
        meuJogador = "Jogador"+posicao;
        partidaAndamento = true;

        partida.iniciarPartida();
        partida.tabuleiro.getTabuleiro().repaint();
        partida.tabuleiro.getTabuleiro().revalidate();
    }

    public static void jogada(int i, int j) {
        if (!partidaAndamento) {
            JOptionPane.showMessageDialog(null, "Nenhuma partida em andamento");
            return;
        }
        if (jogadorAtual.equals(meuJogador)) {
            if (tabuleiro.getPosicao(i, j) == 0) {
                ngServer.realizarJogada(i, j);
                tabuleiro.get()[i][j] = jogadorAtual == "Jogador1" ? (byte) 1 : (byte) 2; //operador ternário
                matrizBotoes[i][j].setIcon(jogadorAtual == "Jogador1" ? icone1 : icone2);
                jogadorAtual = jogadorAtual == "Jogador1" ? "Jogador2" : "Jogador1";

                verificarVencedor(tabuleiro.get(), i, j);
            } else {
                if (verificarEmpate(tabuleiro.get())) {
                    JOptionPane.showMessageDialog(null, "Empatou");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não é a sua vez de jogar");
        }
    }

    public static void mudarPosicao(Lance jogada) {
        tabuleiro.get()[jogada.getLinha()][jogada.getColuna()] = jogada.getJogador().equals("Jogador1") ? (byte) 1 : (byte) 2; //operador ternário
        matrizBotoes[jogada.getLinha()][jogada.getColuna()].setIcon(jogada.getJogador().equals("Jogador1") ? icone1 : icone2);
        jogadorAtual = jogada.getJogador().equals("Jogador1") ? "Jogador2" : "Jogador1";
        
        verificarVencedor(tabuleiro.get(), jogada.getLinha(), jogada.getColuna());
    }

    public static void resetJogadorAtual() {
        Gomoku.jogadorAtual = "Jogador1";
    }

    public static boolean verificarEmpate(byte[][] tabuleiro) {
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
    public static String conectar(String server, String nick) {
        String mensagem = "Não foi possível iniciar"; // Definir condições
        boolean permitido = !conectado;
        if (permitido) {
            mensagem = ngServer.conectar(server, nick);
            if (mensagem.equals("Conectado com sucesso!")) {
                conectado = true;
            }
        }
        return mensagem;
    }

    public static String desconectar() {
        String mensagem = "Não conectado ainda."; // Definir condições
        boolean permitido = conectado;
        if (permitido) {
            mensagem = ngServer.desconectar();
            if (mensagem.equals("Desconectado com sucesso!")) {
                conectado = false;
            }
        }
        return mensagem;
    }

    public static String iniciarPartida() {
        String mensagem = "Não conectado ainda."; // Definir condições
        boolean permitido = conectado;
        if (permitido) {
            mensagem = ngServer.iniciarPartida();
        }
        return mensagem;
    }
}
