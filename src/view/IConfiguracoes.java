package view;

import control.Serializer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.JSlider;
import model.Configuracao;

public class IConfiguracoes extends JDialog{
    private static String[] cores = {"Branco", "Azul" , "Verde", "Amarelo" , "Laranja", "Cinza","Vermelho","Rosa","Preto"};
    
    private JDialog configuracoesJDialog;

    private JLabel nome1JLabel;
    private JLabel nome2JLabel;
    private JLabel nivelBotJLabel;
    private JLabel corTabuleiroJLabel;
    private JLabel corPartidaJLabel;
    private JTextField nome1JTextField;
    private JTextField nome2JTextField;
    private JSlider nivelBotJSlider;
    private JComboBox corTabuleiroJComboBox;
    private JComboBox corPartidaJComboBox;
    private JButton cancelJButton;
    private JButton okJButton;

    private int sMin = 0;
    private int sMax = 2;
    private int sPos = 1;

    public static Configuracao config = new Configuracao();
    
    public IConfiguracoes()
    {
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
        this.nivelBotJLabel= new JLabel( "Nivel Bot:");
        this.nivelBotJLabel.setBounds( 40, 150, 150, 20 ); //(x, y, width, height)
        this.corTabuleiroJLabel= new JLabel( "Cor de Fundo Tabuleiro:");
        this.corTabuleiroJLabel.setBounds( 40, 200, 150, 20 ); //(x, y, width, height)
        this.corPartidaJLabel= new JLabel( "Cor de Fundo Partida:");
        this.corPartidaJLabel.setBounds( 40, 240, 150, 20 ); //(x, y, width, height)
        this.cancelJButton = new JButton("Cancelar");
        this.cancelJButton.setBounds( 90, 300, 100, 20 ); //(x, y, width, height)

        this.nome1JTextField = new JTextField(config == null ? " " : config.getNome1());
        this.nome1JTextField.setBounds( 190, 60, 150, 20 ); //(x, y, width, height)
        this.nome2JTextField = new JTextField(config == null ? " " : config.getNome2());
        this.nome2JTextField.setBounds( 190, 100, 150, 20 ); //(x, y, width, height)
        this.nivelBotJSlider = new JSlider(JSlider.HORIZONTAL, sMin, sMax, config == null ? sPos : config.getNivelBot().get());
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(0, new JLabel("Dificil"));
        labels.put(1, new JLabel("Médio"));
        labels.put(2, new JLabel("Facil"));
        this.nivelBotJSlider.setLabelTable(labels);
        this.nivelBotJSlider.setMajorTickSpacing(1);
        this.nivelBotJSlider.setMinorTickSpacing(1);
        this.nivelBotJSlider.setPaintTicks(true);
        this.nivelBotJSlider.setPaintLabels(true);
        this.nivelBotJSlider.setBounds( 190, 140, 150, 50 ); //(x, y, width, height)
        this.corTabuleiroJComboBox = new JComboBox(this.cores);
        this.corTabuleiroJComboBox.setBounds( 190, 200, 150, 20 ); //(x, y, width, height)
        this.corPartidaJComboBox = new JComboBox(this.cores);
        this.corPartidaJComboBox.setBounds( 190, 240, 150, 20 ); //(x, y, width, height)
        this.okJButton = new JButton("OK");
        this.okJButton.setBounds( 200, 300, 100, 20 ); //(x, y, width, height)
        this.okJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Serializer s = new Serializer();
                Configuracao c = new Configuracao();
                c.setNome1(nome1JTextField.getText());
                c.setNome2(nome2JTextField.getText());
//                c.setNivelBot(nivelBotJSlider.getLabelTable().get(nivelBotJSlider.getValue()));
                s.salvarConfiguracao(c);
                System.out.println("salvo");
            }
        });

        this.configuracoesJDialog.add(this.nome1JLabel);
        this.configuracoesJDialog.add(this.nome1JTextField);
        this.configuracoesJDialog.add(this.nome2JLabel);
        this.configuracoesJDialog.add(this.nome2JTextField);
        this.configuracoesJDialog.add(this.nivelBotJLabel);
        this.configuracoesJDialog.add(this.nivelBotJSlider);

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