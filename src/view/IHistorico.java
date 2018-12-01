package view;

import javax.swing.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import model.Historico;
import model.Partida;

public class IHistorico extends JDialog{
    private JDialog historicoJDialog;
    private String [] colunas = {"Jogador 1","Jogador 2", "Ganhador", "Duração", "Data"};
    private Object [][] dados = new Object[100][6];
 
    private JTable tabela = new JTable(dados, colunas);
    private JScrollPane barraRolagem = new JScrollPane(tabela);

    public IHistorico()
    {
        adicionaComponentesHistorico(); 
    }
    public void adicionaComponentesHistorico() {
        this.historicoJDialog = new JDialog();
        this.historicoJDialog.setTitle("Historico");
        this.historicoJDialog.setLayout(new GridLayout(1, 1));
        preencherTabela();
        this.historicoJDialog.add(this.barraRolagem);
    }
    public JDialog getHistorico() {
        return this.historicoJDialog;
    }
    public void preencherTabela() {
        Historico h = new Historico();
        ArrayList<Partida> historico = h.getHistorico();
        
        for (int i = 0; i < historico.size(); i++) {
            this.dados[i][0] = historico.get(i).getJogador1().getNome();
            this.dados[i][1] = historico.get(i).getJogador2().getNome();
            this.dados[i][2] = historico.get(i).getVencedor();
            this.dados[i][3] = historico.get(i).getDuracao();
            this.dados[i][4] = historico.get(i).getData();
        }

    }
}