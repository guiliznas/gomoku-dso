import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;

public class ITabuleiro extends JPanel{
    private static final int TAMANHO_TABULEIRO = 15; //define a quantidade de colunas e linhas que terá o tabuleiro
    private JButton[][] matrizBotoes = new JButton[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
    private JPanel panel;
    
    public void adicionaComponentesTabuleiro() {
        this.panel= new JPanel();
        this.panel.setLayout(new GridLayout(TAMANHO_TABULEIRO, TAMANHO_TABULEIRO));
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for(int j = 0; j < TAMANHO_TABULEIRO; j++){
                this.matrizBotoes[i][j] = new JButton();
                this.matrizBotoes[i][j].setText("");
                this.panel.add(this.matrizBotoes[i][j]);
            }   
        }
    }
    public JPanel getTabuleiro() {
        return this.panel;
    }
    
}