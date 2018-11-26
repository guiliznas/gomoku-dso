package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;
import java.awt.FlowLayout;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.text.SimpleDateFormat;
import principal.Gomoku;
import model.Configuracao;
import model.Jogador;
import model.Partida;
import model.Pessoa;

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
    
    private JMenu ajudaJMenu;// menu ajuda
    private JMenuItem sobreJMenuItem;//item ajuda-sobre

    private JPanel panelTabuleiro = new JPanel();
    private JPanel panelPlacar;

    private JFrame frame = new JFrame("Gomoku");
    private ITabuleiro tabuleiro = new ITabuleiro();

    private JLabel placarJogador1JLabel;
    private JLabel placarJogador2JLabel;
    private JLabel tempoJogoJLabel;
    private long tempoInicio = System.currentTimeMillis();

    private Timer timer = null;      
    private final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    private JDialog rankingJDialog;
    private IRanking ranking = new IRanking();

    private JDialog configuracoesJDialog;
    public IConfiguracoes configuracoes = new IConfiguracoes();

    private JDialog historicoJDialog;
    private IHistorico historico = new IHistorico();

    private JDialog sobreJDialog;
    private ISobre sobre = new ISobre();
    
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

    public void novaPartida(){
        Gomoku.tabuleiro.reset();
        IConfiguracoes.config.load();
        Configuracao c = IConfiguracoes.config;
        placarJogador1JLabel.setText(c.getNome1());
        placarJogador2JLabel.setText(c.getNome2());
        Gomoku.part = new Partida(new Pessoa(c.getNome1()), new Pessoa(c.getNome2()));
        Gomoku.part.setData(format.format(new Date().getTime()));
        
        tabuleiro.getTabuleiro().setBackground(c.getCorTabuleiro());
        panelPlacar.setBackground(c.getCorPartida());
    }
    
    public void criarTabuleiro(){
        panelTabuleiro = tabuleiro.getTabuleiro();
        panelTabuleiro.setBounds( 20, 50, 500, 500 ); //(x, y, width, height)
        frame.add(panelTabuleiro);
    }

    public void criarRanking(){
        rankingJDialog = new JDialog(frame, true);
        rankingJDialog = ranking.getRanking();
        rankingJDialog.setSize(500, 300);
        rankingJDialog.setLocation(30, 200);
        rankingJDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        rankingJDialog.setVisible(true);
    }

    public void criarSobre(){
        sobreJDialog = new JDialog(frame, true);
        sobreJDialog = sobre.getSobre();
        sobreJDialog.setSize(400, 400);
        sobreJDialog.setLocation(80, 150);
        sobreJDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        sobreJDialog.setVisible(true);
    }

    public void criarConfiguracoes(){
        configuracoesJDialog = new JDialog(frame, true);
        configuracoesJDialog = configuracoes.getConfiguracoes();
        configuracoesJDialog.setSize(400, 400);
        configuracoesJDialog.setLocation(80, 150);
        configuracoesJDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        configuracoesJDialog.setVisible(true);
    }

    public void criarHistorico(){
        historicoJDialog = new JDialog(frame, true);
        historicoJDialog = historico.getHistorico();
        historicoJDialog.setSize(400, 400);
        historicoJDialog.setLocation(80, 150);
        historicoJDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        historicoJDialog.setVisible(true);
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
        novoJMenuItem.addActionListener(
            new ActionListener() 
            {
               public void actionPerformed( ActionEvent event )
               {
                    timer = null;
//                    placarJogador1JLabel.setText("Jogador 1 : 0    ");
//                    placarJogador2JLabel.setText("     Jogador 2 : 0 ");
                    placarJogador1JLabel.setText(IConfiguracoes.config.getNome1());
                    placarJogador2JLabel.setText(IConfiguracoes.config.getNome2());
                    tempoInicio = System.currentTimeMillis();
                    iniciaCronometro();
                    novaPartida();
               }
            } 
        ); 

        salvarJMenuItem = new JMenuItem( "Salvar" );
        arquivoJMenu.add( salvarJMenuItem );
        arquivoJMenu.addSeparator();

        sairJMenuItem = new JMenuItem( "Sair" );
        arquivoJMenu.add( sairJMenuItem );
        sairJMenuItem.addActionListener(
            new ActionListener() 
            {
               public void actionPerformed( ActionEvent event )
               {    
                    placarJogador1JLabel.setText( "     Jogador 1     ");
                    tempoJogoJLabel.setText( "     Tempo     ");
                    placarJogador2JLabel.setText( "     Jogador 2     ");
                    if(timer!=null){
                        timer.cancel();
                    }
               }
            } 
        ); 

        opcoesJMenu = new JMenu( "Opcoes" );
        barraJMenuBar.add(opcoesJMenu);

        configuracoesJMenuItem = new JMenuItem( "Configuracoes" );
        opcoesJMenu.add( configuracoesJMenuItem );
        opcoesJMenu.addSeparator();
        configuracoesJMenuItem.addActionListener(
            new ActionListener() 
            {
               public void actionPerformed( ActionEvent event )
               {
                    criarConfiguracoes();
               }
            } 
        ); 

        historicoJMenuItem = new JMenuItem( "Historico" );
        opcoesJMenu.add( historicoJMenuItem );
        opcoesJMenu.addSeparator();
        historicoJMenuItem.addActionListener(
            new ActionListener() 
            {
               public void actionPerformed( ActionEvent event )
               {
                    criarHistorico();
                    historico.preencherTabela();
               }
            } 
        ); 

        rankingJMenuItem = new JMenuItem( "Ranking" );
        opcoesJMenu.add( rankingJMenuItem );
        rankingJMenuItem.addActionListener(
            new ActionListener() 
            {
               public void actionPerformed( ActionEvent event )
               {
                    criarRanking();
               }
            } 
        ); 

        ajudaJMenu = new JMenu( "Ajuda" );
        barraJMenuBar.add(ajudaJMenu);
        sobreJMenuItem = new JMenuItem( "Sobre" );
        ajudaJMenu.add( sobreJMenuItem );
        sobreJMenuItem.addActionListener(
            new ActionListener() 
            {
               public void actionPerformed( ActionEvent event )
               {
                    criarSobre();
               }
            } 
        ); 
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

    public void iniciaCronometro(){
        if (timer == null){      
            timer = new Timer();
            TimerTask tarefa = new TimerTask() {     
                public void run(){      
                    try {     
                         String data = format.format(new Date().getTime());//pega a data atual
                         long diferenca=((System.currentTimeMillis() - tempoInicio)/ 1000);//armazena a diferença entre o inicio da exucação do programa e o tempo atual
                         tempoJogoJLabel.setText("Tempo: "+diferenca );
                         if (Gomoku.part != null) {
                            Gomoku.part.setDuracao(diferenca);
                         }
                         //System.out.println(data + "  " + diferenca);  descomente isso se quiser testar
                    } catch (Exception e) {      
                         e.printStackTrace();      
                    }
                }   
            };      
            timer.scheduleAtFixedRate(tarefa, 0, 1000);      
        }    
    }
    public static void main(String[] args) {
        IPartida partida = new IPartida();
    }
}