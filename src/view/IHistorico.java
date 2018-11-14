package view;

import javax.swing.*;
import java.awt.GridLayout;

public class IHistorico extends JDialog{
    private JDialog historicoJDialog;
    private String [] colunas = {"Jogador 1","Jogador 2", "Ganhador", "Data"};
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
        this.dados[0][0]= "Alfeu";
        this.dados[0][1]= "Guilherme";
        this.dados[0][2]= "Alfeu";
        this.dados[0][3]= "13/11/2018";
    }
}