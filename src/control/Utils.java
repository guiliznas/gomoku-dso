package control;

import java.util.Random;
import javax.swing.JOptionPane;
import model.Configuracao;
import view.IConfiguracoes;

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
//        //        int opc = JOptionPane.showConfirmDialog(null, "Começar nova partida?");
//        String[] buttons = {"Jogador", "Bot", "Sair"};
//        int opc = JOptionPane.showOptionDialog(null, "Jogar contra quem?", "Nova Partida",
//                JOptionPane.YES_NO_CANCEL_OPTION, 3, null, buttons, null);
//        System.out.println(opc); // 0 - ok; 1- nao; 2 - cancel
//        if (opc == 0) {
//            System.out.println("Nova");
//            Gomoku.roboJogando = false;
//        } else if (opc == 1) {
//            Gomoku.roboJogando = true;
//        } else {
//            System.exit(0);
//        }
        Gomoku.partida.novaPartida();
        Gomoku.partida.tabuleiro.getTabuleiro().repaint();
        Gomoku.partida.tabuleiro.getTabuleiro().revalidate();
    }

    public static int verificaGanhador(byte[][] tabuleiro, int i, int j) {
        int contadorCimaBaixo;
        int contadorEsquerdaDireita;
        int contadorDiagonalPrincipal;
        int contadorDiagonalSecundaria;

        contadorCimaBaixo = verificaPosicoes(tabuleiro, i, j, VERIFICA_PRA_CIMA);
        contadorCimaBaixo += verificaPosicoes(tabuleiro, i, j, VERIFICA_PRA_BAIXO);

        contadorEsquerdaDireita = verificaPosicoes(tabuleiro, i, j, VERIFICA_PRA_ESQUERDA);
        contadorEsquerdaDireita += verificaPosicoes(tabuleiro, i, j, VERIFICA_PRA_DIREITA);

        contadorDiagonalPrincipal = verificaPosicoes(tabuleiro, i, j, VERIFICA_DIAGONAL_CIMA);
        contadorDiagonalPrincipal += verificaPosicoes(tabuleiro, i, j, VERIFICA_DIAGONAL_BAIXO);

        contadorDiagonalSecundaria = verificaPosicoes(tabuleiro, i, j, VERIFICA_SECUNDARIA_CIMA);
        contadorDiagonalSecundaria += verificaPosicoes(tabuleiro, i, j, VERIFICA_SECUNDARIA_BAIXO);

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
            //JOptionPane.showMessageDialog(null, "Ganhou o jogador " + tabuleiro[i][j]);
            Serializer s = new Serializer();
            try {
                Gomoku.part.setVencedor(tabuleiro[i][j] == 1 ? Gomoku.part.getJogador1().getNome() : Gomoku.part.getJogador2().getNome());
            } catch (Exception ex) {
                System.out.println(ex);
            }
            s.addPartida(Gomoku.part);
            Gomoku.partidaAndamento = false;
            return tabuleiro[i][j];//retorna o jogador ganhador
        } else {
            return 0; //ninguém ganhou
        }
    }

    public static int verificaPosicoes(byte[][] tabuleiro, int i, int j, int lugar) {
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
                        valor = tabuleiro[i - posicao][j];
                    }
                    break;
                case VERIFICA_PRA_BAIXO:
                    if (i + posicao < 15) {
                        valor = tabuleiro[i + posicao][j];
                    }
                    break;
                case VERIFICA_PRA_ESQUERDA:
                    if ((j - posicao) >= 0) {
                        valor = tabuleiro[i][j - posicao];
                    }
                    break;
                case VERIFICA_PRA_DIREITA:
                    if ((j + posicao) < 15) {
                        valor = tabuleiro[i][j + posicao];
                    }
                    break;
                case VERIFICA_DIAGONAL_CIMA:
                    if ((i - posicao >= 0) && (j - posicao >= 0)) {
                        valor = tabuleiro[i - posicao][j - posicao];
                    }
                    break;
                case VERIFICA_DIAGONAL_BAIXO:
                    if ((i + posicao < 15) && (j + posicao < 15)) {
                        valor = tabuleiro[i + posicao][j + posicao];
                    }
                    break;
                case VERIFICA_SECUNDARIA_CIMA:
                    if ((i - posicao >= 0) && (j + posicao < 15)) {
                        valor = tabuleiro[i - posicao][j + posicao];
                    }
                    break;
                case VERIFICA_SECUNDARIA_BAIXO:
                    if ((i + posicao < 15) && (j - posicao >= 0)) {
                        valor = tabuleiro[i + posicao][j - posicao];
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
}
