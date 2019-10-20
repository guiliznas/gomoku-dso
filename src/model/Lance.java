package model;

import br.ufsc.inf.leobr.cliente.Jogada;

/**
 * @author(name= Guilherme de Liz, date= 20/10/2019)
 */
public class Lance implements Jogada {

    private int linha;
    private int coluna;
    private String jogador;

    public Lance(int linha, int coluna, String jogador) {
        this.linha = linha;
        this.coluna = coluna;
        this.jogador = jogador;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public String getJogador() {
        return jogador;
    }

    @Override
    public String toString() {
        return "Lance{" + "linha=" + linha + ", coluna=" + coluna + ", jogador=" + jogador + '}';
    }

    
}
