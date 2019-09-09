package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import model.Configuracao;

public class IConfiguracoes extends JDialog{
    private static String[] cores = {"Branco", "Azul" , "Verde", "Amarelo" , "Laranja", "Cinza","Vermelho","Rosa","Preto"};
    private static Map<String, Color> colors = new HashMap<>();
    private JDialog configuracoesJDialog;

    private JLabel nome1JLabel;
    private JLabel nome2JLabel;
    private JLabel corTabuleiroJLabel;
    private JLabel corPartidaJLabel;
    private JTextField nome1JTextField;
    private JTextField nome2JTextField;
    private JComboBox corTabuleiroJComboBox;
    private JComboBox corPartidaJComboBox;
    private JButton cancelJButton;
    private JButton okJButton;

    public static Configuracao config = new Configuracao();
    
    public IConfiguracoes()
    {
        colors.put("Branco", Color.WHITE);
        colors.put("Azul", Color.BLUE);
        colors.put("Verde", Color.GREEN);
        colors.put("Amarelo", Color.YELLOW);
        colors.put("Laranja", Color.ORANGE);
        colors.put("Cinza", Color.GRAY);
        colors.put("Vermelho", Color.RED);
        colors.put("Rosa", Color.PINK);
        colors.put("Preto", Color.DARK_GRAY); 
        config.load();
        System.out.println(config);
        adicionaComponentesConfiguracoes();
    }
    public void adicionaComponentesConfiguracoes() {
        this.configuracoesJDialog = new JDialog();
        this.configuracoesJDialog.setTitle("Configuracoes");
        this.configuracoesJDialog.setLayout(null);

        this.nome1JLabel= new JLabel( "Nome 1:");
        this.nome1JLabel.setBounds( 40, 60, 150, 20 ); //(x, y, width, height)
        this.nome2JLabel= new JLabel( "Nome 2:");
        this.nome2JLabel.setBounds( 40, 100, 150, 20 ); //(x, y, width, height)
        this.corTabuleiroJLabel= new JLabel( "Cor de Fundo Tabuleiro:");
        this.corTabuleiroJLabel.setBounds( 40, 200, 150, 20 ); //(x, y, width, height)
        this.corPartidaJLabel= new JLabel( "Cor de Fundo Partida:");
        this.corPartidaJLabel.setBounds( 40, 240, 150, 20 ); //(x, y, width, height)
        this.cancelJButton = new JButton("Cancelar");
        this.cancelJButton.setBounds( 90, 300, 100, 20 ); //(x, y, width, height)
        this.cancelJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Cancelado");
            }
        });

        this.nome1JTextField = new JTextField(config == null ? " " : config.getNome1());
        this.nome1JTextField.setBounds( 190, 60, 150, 20 ); //(x, y, width, height)
        this.nome2JTextField = new JTextField(config == null ? " " : config.getNome2());
        this.nome2JTextField.setBounds( 190, 100, 150, 20 ); //(x, y, width, height)
        this.corTabuleiroJComboBox = new JComboBox(this.cores);
        this.corTabuleiroJComboBox.setBounds( 190, 200, 150, 20 ); //(x, y, width, height)
        this.corPartidaJComboBox = new JComboBox(this.cores);
        this.corPartidaJComboBox.setBounds( 190, 240, 150, 20 ); //(x, y, width, height)
        this.okJButton = new JButton("OK");
        this.okJButton.setBounds( 200, 300, 100, 20 ); //(x, y, width, height)
        JDialog jd = this;
        this.okJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Salvo");
            }
        });

        this.configuracoesJDialog.add(this.nome1JLabel);
        this.configuracoesJDialog.add(this.nome1JTextField);
        this.configuracoesJDialog.add(this.nome2JLabel);
        this.configuracoesJDialog.add(this.nome2JTextField);

        this.configuracoesJDialog.add(this.corTabuleiroJLabel);
        this.configuracoesJDialog.add(this.corTabuleiroJComboBox);
        this.configuracoesJDialog.add(this.corPartidaJLabel);
        this.configuracoesJDialog.add(this.corPartidaJComboBox);
        this.configuracoesJDialog.add(this.cancelJButton);
        this.configuracoesJDialog.add(this.okJButton);

    }
    public JDialog getConfiguracoes() {
        return this.configuracoesJDialog;
    }

}