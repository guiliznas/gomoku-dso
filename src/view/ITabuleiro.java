package view;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Tabuleiro;
import principal.Gomoku;

public class ITabuleiro extends JPanel{
    private static final int TAMANHO_TABULEIRO = 15; //define a quantidade de colunas e linhas que terá o tabuleiro
    private JButton[][] matrizBotoes = new JButton[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
    private JPanel panel;
    private Tabuleiro tabuleiro;
    
    public ITabuleiro()
    {
        adicionaComponentesTabuleiro(); 
    }

    public void adicionaComponentesTabuleiro() {
        this.panel= new JPanel();
        this.panel.setLayout(new GridLayout(TAMANHO_TABULEIRO, TAMANHO_TABULEIRO));
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for(int j = 0; j < TAMANHO_TABULEIRO; j++){
                Gomoku.matrizBotoes[i][j] = new JButton();
                Gomoku.matrizBotoes[i][j].setText("");
                final int testeI = i;
                final int testeJ = j;
                Gomoku.matrizBotoes[i][j].addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Gomoku.jogada(testeI, testeJ);
                    }
                });
                this.panel.add(Gomoku.matrizBotoes[i][j]);
            }   
        }
    }
    public JPanel getTabuleiro() {
        return this.panel;
    }
    
}