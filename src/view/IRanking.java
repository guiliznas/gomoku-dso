package view;

import javax.swing.*;
import java.awt.GridLayout;

public class IRanking extends JDialog{
    private JDialog rankingJDialog;
    private String [] colunas = {"Posicao","Nome", "Vitorias", "Vs.Pessoas","Vs.Bots","Duracao"};
    private Object [][] dados = new Object[100][6];
 
    private JTable tabela = new JTable(dados, colunas);
    private JScrollPane barraRolagem = new JScrollPane(tabela);

    public IRanking()
    {
        adicionaComponentesRanking(); 
    }
    public void adicionaComponentesRanking() {
        this.rankingJDialog = new JDialog();
        this.rankingJDialog.setTitle("Ranking");
        this.rankingJDialog.setLayout(new GridLayout(1, 1));
        preencherTabela();
        this.rankingJDialog.add(this.barraRolagem);
    }

    public void preencherTabela() {
        this.dados[0][0]= "Posicao";
        this.dados[0][1]= "Nome";
        this.dados[0][2]= "Vitorias";
        this.dados[0][3]= "Vs.Pessoas";
        this.dados[0][4]= "Vs.Bots";
        this.dados[0][5]= "Duracao";
    }

    public JDialog getRanking() {
        return this.rankingJDialog;
    }
}