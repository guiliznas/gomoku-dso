package view;

import javax.swing.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Map;
import model.Historico;
import model.Partida;

public class IRanking extends JDialog{
    private JDialog rankingJDialog;
    private String [] colunas = {"Nome", "Vitorias"};
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
//        this.dados[0][0]= "Posicao";
//        this.dados[0][1]= "Nome";
//        this.dados[0][2]= "Vitorias";
//        this.dados[0][3]= "Vs.Pessoas";
//        this.dados[0][4]= "Vs.Bots";
//        this.dados[0][5]= "Duracao";
    
        Historico h = new Historico();
        Map<String, Integer> rank = h.getRanking();
        
        int count = 0;
        for (Map.Entry<String, Integer> posicao : rank.entrySet()) {
            this.dados[count][0] = posicao.getKey();
            this.dados[count++][1] = posicao.getValue();
        }

    }

    public JDialog getRanking() {
        return this.rankingJDialog;
    }
}