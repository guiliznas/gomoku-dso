import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;


public class IPartida extends JFrame{
    private JMenuBar barraJMenuBar;// barra de menu

    private JMenu arquivoJMenu;// menu arquivo
    private JMenuItem novoJMenuItem;//item arquivo-novo 
    private JMenuItem salvarJMenuItem; //item arquivo - salvar 
    private JMenuItem sairJMenuItem;//item arquivo - sair 

    private JMenu opcoesJMenu;// menu opcoes
    private JMenuItem configuracoesJMenuItem;//item opcoes-configuracoes
    private JMenuItem historicoJMenuItem; //item opcoes-historico 
    private JMenuItem rankingJMenuItem;//item opcoes - ranking 
    
    private JMenu sobreJMenu;// menu sobre

    private JPanel panelTabuleiro = new JPanel();
    private JPanel panelPlacar;

    private JFrame frame = new JFrame("Gomoku");
    private ITabuleiro tabuleiro = new ITabuleiro();

    private JLabel placarJogador1JLabel;
    private JLabel placarJogador2JLabel;
    private JLabel tempoJogoJLabel;
    private long tempoInicio = System.currentTimeMillis();

    public IPartida()
    {
        criarPartida(); 
    }

    public void criarPartida() {
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        criarMenu();       
        criarTabuleiro();
        criarPlacar();
        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(560, 650);
        frame.setVisible(true);
    }

    public void criarTabuleiro(){
        tabuleiro.adicionaComponentesTabuleiro();
        panelTabuleiro = tabuleiro.getTabuleiro();
        panelTabuleiro.setBounds( 20, 50, 500, 500 ); //(x, y, width, height)
        frame.add(panelTabuleiro);
    }

    public void criarMenu(){
        // barra de menu
        barraJMenuBar = new JMenuBar();
        frame.setJMenuBar( barraJMenuBar);

        arquivoJMenu = new JMenu( "Arquivo" );
        barraJMenuBar.add(arquivoJMenu);
       
        novoJMenuItem = new JMenuItem( "Novo" );
        arquivoJMenu.add( novoJMenuItem );
        arquivoJMenu.addSeparator();

        salvarJMenuItem = new JMenuItem( "Salvar" );
        arquivoJMenu.add( salvarJMenuItem );
        arquivoJMenu.addSeparator();

        sairJMenuItem = new JMenuItem( "Sair" );
        arquivoJMenu.add( sairJMenuItem );

        opcoesJMenu = new JMenu( "Opcoes" );
        barraJMenuBar.add(opcoesJMenu);

        configuracoesJMenuItem = new JMenuItem( "Configuracoes" );
        opcoesJMenu.add( configuracoesJMenuItem );
        opcoesJMenu.addSeparator();

        historicoJMenuItem = new JMenuItem( "Historico" );
        opcoesJMenu.add( historicoJMenuItem );
        opcoesJMenu.addSeparator();

        rankingJMenuItem = new JMenuItem( "Ranking" );
        opcoesJMenu.add( rankingJMenuItem );

        sobreJMenu = new JMenu( "Sobre" );
        barraJMenuBar.add(sobreJMenu);

    }

    public void criarPlacar(){
        panelPlacar = new JPanel();
        panelPlacar.setLayout(new FlowLayout());

        placarJogador1JLabel = new JLabel( "     Jogador 1     ");
        panelPlacar.add( placarJogador1JLabel );

        tempoJogoJLabel = new JLabel( "     Tempo     ");
        panelPlacar.add( tempoJogoJLabel );

        placarJogador2JLabel = new JLabel( "     Jogador 2     ");
        panelPlacar.add( placarJogador2JLabel );

        panelPlacar.setBounds( 0, 0, 500, 20 ); //(x, y, width, height)
        frame.add(panelPlacar);

    }

    public static void main(String[] args) {
        IPartida partida = new IPartida();
    }
}