package principal;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.Random;
import model.Tabuleiro;
import view.IPartida;


public class Gomoku {
    
    private IPartida partida;
    private static String jogadorAtual = "Jogador1";

    public static Tabuleiro tabuleiro = new Tabuleiro(15);
    public static JButton[][] matrizBotoes = new JButton[15][15];
    
    public static ImageIcon icone1;
    public static ImageIcon icone2;
    
    public static final int VERIFICA_PRA_CIMA = 0;
    public static final int VERIFICA_PRA_BAIXO = 1;
    public static final int VERIFICA_PRA_ESQUERDA = 2;
    public static final int VERIFICA_PRA_DIREITA = 3;
    public static final int VERIFICA_DIAGONAL_CIMA=4;
    public static final int VERIFICA_DIAGONAL_BAIXO=5;
    public static final int VERIFICA_SECUNDARIA_CIMA=6;
    public static final int VERIFICA_SECUNDARIA_BAIXO=7;
    
    public static boolean roboJogando = true;
    public static Random gerador = new Random();
    
   
    public Gomoku () {
        partida = new IPartida();
        Gomoku.icone1 = new ImageIcon(getClass().getResource("/001.png"));
        Gomoku.icone2 = new ImageIcon(getClass().getResource("/002.png"));
    }
    
    public static void jogada(int i, int j){
        if(jogadorAtual == "Jogador1" || roboJogando==false){
            System.out.println("Jogada de: " + jogadorAtual);
            if (tabuleiro.getPosicao(i, j) == 0) {
                tabuleiro.get()[i][j] = jogadorAtual == "Jogador1" ? (byte)1 : (byte)2; //operador ternário
                matrizBotoes[i][j].setIcon(jogadorAtual == "Jogador1" ? icone1 : icone2);
                jogadorAtual = jogadorAtual == "Jogador1" ? "Jogador2" : "Jogador1";
            
                verificaGanhador(tabuleiro.get(),i,j);
            } else {
                if(verificaEmpate(tabuleiro.get())){
                   System.out.println("Empatou!!");
                }
                System.out.println("Não foi possivel jogar");
            }
        }
        
        if(roboJogando && jogadorAtual == "Jogador2"){ //robo jogando
            System.out.println("Jogada de: " + jogadorAtual);
            int[] linhaColuna = jogadaRobo(tabuleiro.get(),i, j,0);//ultimo argumento é o nivel de dificuldade
                                                                   //0 é dificil - 1 é médio - 2 é facil
            tabuleiro.get()[linhaColuna[0]][linhaColuna[1]] = 2; 
            matrizBotoes[linhaColuna[0]][linhaColuna[1]].setIcon(icone2);
            verificaGanhador(tabuleiro.get(),linhaColuna[0],linhaColuna[1]);
            jogadorAtual = "Jogador1";//depois da jogada do robo passa a jogada pro player
        }
    }
    
    public static int verificaGanhador(byte[][] tabuleiro,int i, int j){

        int contadorCimaBaixo;
        int contadorEsquerdaDireita;
        int contadorDiagonalPrincipal;
        int contadorDiagonalSecundaria;
        
        contadorCimaBaixo =  verificaPosicoes(tabuleiro,i,j,VERIFICA_PRA_CIMA);
        contadorCimaBaixo += verificaPosicoes(tabuleiro,i,j,VERIFICA_PRA_BAIXO);
        
        contadorEsquerdaDireita =  verificaPosicoes(tabuleiro,i,j,VERIFICA_PRA_ESQUERDA);
        contadorEsquerdaDireita += verificaPosicoes(tabuleiro,i,j,VERIFICA_PRA_DIREITA);
        
        contadorDiagonalPrincipal =  verificaPosicoes(tabuleiro,i,j,VERIFICA_DIAGONAL_CIMA);
        contadorDiagonalPrincipal += verificaPosicoes(tabuleiro,i,j,VERIFICA_DIAGONAL_BAIXO);
        
        contadorDiagonalSecundaria =  verificaPosicoes(tabuleiro,i,j,VERIFICA_SECUNDARIA_CIMA);
        contadorDiagonalSecundaria += verificaPosicoes(tabuleiro,i,j,VERIFICA_SECUNDARIA_BAIXO);
        
        if(contadorDiagonalSecundaria>=4||contadorDiagonalPrincipal>=4||contadorEsquerdaDireita>=4||contadorCimaBaixo>=4){//4 porque considera a propria peca 
            System.out.println("Ganhou o Jogador"+tabuleiro[i][j]);
            return tabuleiro[i][j];//retorna o jogador ganhador
        }else{
            return 0; //ninguém ganhou
        }
    }
    
    public static int verificaPosicoes(byte[][] tabuleiro,int i, int j,int lugar){
        boolean continua = true;
        int posicao=0;
        int contadorSucesso=0;
        byte valor = 0;
 

        while(continua){
            posicao++;
            valor = 0;//é necessário apagar o valor senão estraga a lógica e entra em looping
            
            switch(lugar){
                case VERIFICA_PRA_CIMA:
                    if(i-posicao>=0){//verifiquei com if antes de ler na matriz para não dar arrayoutofbounds
                        valor = tabuleiro[i-posicao][j];
                    }
                break;
                case VERIFICA_PRA_BAIXO:
                    if(i+posicao<15){
                        valor = tabuleiro[i+posicao][j];
                    }
                break;
                case VERIFICA_PRA_ESQUERDA:
                    if((j-posicao)>=0){
                        valor = tabuleiro[i][j-posicao]; 
                    }
                break;
                case VERIFICA_PRA_DIREITA:
                    if((j+posicao)<15){
                        valor = tabuleiro[i][j+posicao];
                    }
                break;
                case VERIFICA_DIAGONAL_CIMA:
                    if((i-posicao>=0)&& (j-posicao>=0)){
                        valor = tabuleiro[i-posicao][j-posicao];
                    }
                break;
                case VERIFICA_DIAGONAL_BAIXO:
                    if((i+posicao<15) && (j+posicao<15)){
                        valor = tabuleiro[i+posicao][j+posicao];
                    }
                break;
                case VERIFICA_SECUNDARIA_CIMA:
                    if((i-posicao>=0) && (j+posicao<15)){
                        valor = tabuleiro[i-posicao][j+posicao];
                    }
                break;
                case VERIFICA_SECUNDARIA_BAIXO:
                    if((i+posicao<15) && (j-posicao>=0)){
                        valor = tabuleiro[i+posicao][j-posicao];
                    }
                break;
                default:
                    System.out.println("Opcao invalida!");
                break;
            }
            
            if(tabuleiro[i][j] == valor){
                contadorSucesso++;
            }else{
                continua=false;
            }
        }
        return contadorSucesso;
    }
    
    public static boolean verificaEmpate(byte[][] tabuleiro){
        boolean empate = true; 
        for(int i = 0; i < 15; i++){ //varre o tabuleiro buscando alguma posição vazia pra jogar
            for(int j= 0; j < 15; j++){//caso estejam todas ocupadas é porque empatou
                if(tabuleiro[i][j]==0){
                    empate = false;
                }  
            }
        }
        return empate;
    }
    public static int[] jogadaRobo(byte[][] tabuleiro,int i, int j,int nivel){
        int[] posicoes = {0,0};
        int evitaDemora = 0;
        boolean empatou = false;
        

        int linha = 1;
        int coluna = 1;
        int contadorCimaBaixo;
        int contadorEsquerdaDireita;
        int contadorDiagonalPrincipal;
        int contadorDiagonalSecundaria;

        contadorEsquerdaDireita =  verificaPosicoes(tabuleiro,i,j,VERIFICA_PRA_ESQUERDA);
        contadorEsquerdaDireita += verificaPosicoes(tabuleiro,i,j,VERIFICA_PRA_DIREITA);

        contadorCimaBaixo =  verificaPosicoes(tabuleiro,i,j,VERIFICA_PRA_CIMA);
        contadorCimaBaixo += verificaPosicoes(tabuleiro,i,j,VERIFICA_PRA_BAIXO);

        contadorDiagonalPrincipal =  verificaPosicoes(tabuleiro,i,j,VERIFICA_DIAGONAL_CIMA);
        contadorDiagonalPrincipal += verificaPosicoes(tabuleiro,i,j,VERIFICA_DIAGONAL_BAIXO);

        contadorDiagonalSecundaria =  verificaPosicoes(tabuleiro,i,j,VERIFICA_SECUNDARIA_CIMA);
        contadorDiagonalSecundaria += verificaPosicoes(tabuleiro,i,j,VERIFICA_SECUNDARIA_BAIXO);            


        if(tabuleiro[i][j] != 0 && contadorCimaBaixo>=nivel&&contadorCimaBaixo>=contadorEsquerdaDireita&&contadorCimaBaixo>=contadorDiagonalPrincipal&&contadorCimaBaixo>=contadorDiagonalSecundaria){
            if(i+linha>=0 && i+linha<15){
                if(tabuleiro[i+linha][j] == 0){
                    i = i+linha;
                }
            }
            if(i-linha>=0 && i-linha<15){
                if(tabuleiro[i-linha][j] == 0){
                    i = i-linha;
                }
            }
        }
        if(tabuleiro[i][j] != 0 &&contadorEsquerdaDireita>=nivel&&contadorEsquerdaDireita>=contadorCimaBaixo&&contadorEsquerdaDireita>=contadorDiagonalPrincipal&&contadorEsquerdaDireita>=contadorDiagonalSecundaria){
            if(j+coluna>=0 && j+coluna<15){
                if(tabuleiro[i][j+coluna] == 0){
                    j=j+coluna;
                }
            }
            if(j-coluna >=0 && j-coluna<15){
                if(tabuleiro[i][j-coluna] == 0){
                    j=j-coluna;
                }
            }
        }
        if(tabuleiro[i][j] != 0 &&contadorDiagonalPrincipal>=nivel&&contadorDiagonalPrincipal>=contadorCimaBaixo&&contadorDiagonalPrincipal>=contadorEsquerdaDireita&&contadorDiagonalPrincipal>=contadorDiagonalSecundaria){
            if(i - linha>=0 && i-linha < 15 && j-coluna<15 && j-coluna>=0){
                if(tabuleiro[i-linha][j-coluna] == 0){
                    j=j-coluna;
                    i=i-linha;
                }
            }
            if(i+linha<15&& i+linha >=0 &&coluna+j<15&&coluna+j>=0){
                if(tabuleiro[i+linha][j+coluna] == 0){
                    j=coluna+j;
                    i=i+linha;
                }
            }
        }
        if(tabuleiro[i][j] != 0 &&contadorDiagonalSecundaria>=nivel&&contadorDiagonalSecundaria>=contadorCimaBaixo&&contadorDiagonalSecundaria>=contadorEsquerdaDireita&&contadorDiagonalSecundaria>=contadorDiagonalPrincipal){ 
            if(i - linha>=0 && i-linha < 15 && j+coluna<15 && j+coluna>=0){
                if(tabuleiro[i-linha][j+coluna] == 0){
                    j=j+coluna;
                    i=i-linha;
                }
            }
            if(i+linha<15&& i+linha >=0 &&j-coluna<15&&j-coluna>=0){
                if(tabuleiro[i+linha][j-coluna] == 0){
                    j=j-coluna;
                    i=i+linha;
                }
            }
        }
        if(nivel>0){ // faz algumas randomicas se o nivel for mais fraco
            while (tabuleiro[i][j] != 0 && evitaDemora<30) {
                i=gerador.nextInt(14);    
                j=gerador.nextInt(14);
                evitaDemora++;
            }
            evitaDemora=0;
        }
        while(empatou==false && tabuleiro[i][j] != 0) {//em sequencia, para tentar ganhar
            for(int k = 0; k < 15; k++){ 
                for(int l= 0; l < 15; l++){
                    if(tabuleiro[k][l]==0){
                        i=k;    
                        j=l;
                    }  
                }
            }
            empatou=verificaEmpate(tabuleiro);
            if(empatou){
                System.out.println("Empatou!");
            }
        }
        posicoes[0] = i;
        posicoes[1] = j;


        
        return posicoes;
    }
}
