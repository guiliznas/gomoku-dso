import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;

public class ISobre extends JDialog{
    private JLabel sobreJLabel;
    private JLabel versaoJLabel;
    private JLabel autor1JLabel;
    private JLabel autor2JLabel;
    private JLabel autor3JLabel;
    private JLabel direitosJLabel;

    private JDialog sobreJDialog;
    
    Icon icon = new ImageIcon("sobre.gif");
    JLabel gifJLabel = new JLabel(icon);

    public ISobre()
    {
        adicionaComponentesSobre(); 
    }
    public void adicionaComponentesSobre() {
        this.sobreJDialog = new JDialog();
        this.sobreJDialog.setTitle("Sobre");
        this.sobreJDialog.setLayout(new GridLayout(7, 1));

        this.sobreJLabel = new JLabel( "Gomoku :)");
        this.sobreJLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        this.sobreJLabel.setHorizontalAlignment(JLabel.CENTER);

        this.autor1JLabel = new JLabel( "Alfeu Goncalves dos Santos");
        this.autor1JLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        this.autor1JLabel.setHorizontalAlignment(JLabel.CENTER);

        this.autor2JLabel = new JLabel( "Guilherme Nascimento");
        this.autor2JLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        this.autor2JLabel.setHorizontalAlignment(JLabel.CENTER);

        this.autor3JLabel = new JLabel( "Joao Brognolli");
        this.autor3JLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        this.autor3JLabel.setHorizontalAlignment(JLabel.CENTER);

        this.versaoJLabel = new JLabel( "Versao 1.0.0 | Data 11/2018");
        this.versaoJLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        this.versaoJLabel.setHorizontalAlignment(JLabel.CENTER);

        this.direitosJLabel = new JLabel("Trabalho apresentado a disciplina de Desenvolvimento de Sistemas Orientados a Objetos I");
        this.direitosJLabel.setFont(new Font("Serif", Font.PLAIN, 10));
        this.direitosJLabel.setHorizontalAlignment(JLabel.CENTER);

        this.sobreJDialog.add(this.gifJLabel);
        this.sobreJDialog.add(this.sobreJLabel);
        this.sobreJDialog.add(this.autor1JLabel);
        this.sobreJDialog.add(this.autor2JLabel);
        this.sobreJDialog.add(this.autor3JLabel);
        this.sobreJDialog.add(this.versaoJLabel);
        this.sobreJDialog.add(this.direitosJLabel);

        
    }
    public JDialog getSobre() {
        return this.sobreJDialog;
    }
}
