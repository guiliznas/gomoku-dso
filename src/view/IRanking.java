package view;

import javax.swing.*;
import java.awt.GridLayout;
import java.util.Map;
import model.Historico;

public class IRanking extends JDialog {
    private JDialog rankingJDialog;
    private String[] colunas = { "Nome", "Vitorias" };
    private Object[][] dados = new Object[100][6];

    private JTable tabela = new JTable(dados, colunas);
    private JScrollPane barraRolagem = new JScrollPane(tabela);

    public IRanking() {
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