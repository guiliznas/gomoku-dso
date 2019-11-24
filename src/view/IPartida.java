package view;

import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;
import java.awt.FlowLayout;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.text.SimpleDateFormat;
import control.Gomoku;
import model.Configuracao;
import model.Partida;
import model.Jogador;

public class IPartida extends JFrame {

    private JMenuBar barraJMenuBar;// barra de menu

    private JMenu netgamesMenu; // menu netgames
    private JMenuItem itemConectar;
    private JMenuItem itemIniciar;
    private JMenuItem itemDesconectar;

    private JMenu opcoesJMenu;// menu opcoes
    private JMenuItem configuracoesJMenuItem;//item opcoes-configuracoes
    private JMenuItem historicoJMenuItem; //item opcoes-historico 
    private JMenuItem rankingJMenuItem;//item opcoes - ranking 

    private JMenu ajudaJMenu;// menu ajuda
    private JMenuItem sobreJMenuItem;//item ajuda-sobre

    private JPanel panelTabuleiro = new JPanel();
    private JPanel panelPlacar;

    private JFrame frame = new JFrame("Gomoku");
    public ITabuleiro tabuleiro = new ITabuleiro();

    private JLabel placarJogador1JLabel;
    private JLabel placarJogador2JLabel;
    private JLabel tempoJogoJLabel;
    private long tempoInicio = System.currentTimeMillis();

    private Timer timer = null;
    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");

    private JDialog rankingJDialog;
    private IRanking ranking = new IRanking();

    private JDialog configuracoesJDialog;
    public IConfiguracoes configuracoes = new IConfiguracoes();

    private JDialog historicoJDialog;
    private IHistorico historico = new IHistorico();

    private JDialog sobreJDialog;
    private ISobre sobre = new ISobre();

    private TimerTask tarefa;

    public IPartida() {
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
        frame.setLocationRelativeTo(null);
    }

    public void novaPartida() {
        Gomoku.tabuleiro.reset();
        IConfiguracoes.config.load();
        Configuracao c = IConfiguracoes.config;
        placarJogador1JLabel.setText("Brancas  - " + c.getNome1() + "  |");
        placarJogador2JLabel.setText("|   " + c.getNome2() + " -   Pretas");
        Gomoku.part = new Partida(new Jogador(c.getNome1()), new Jogador(c.getNome2()));
        System.out.println("Partida iniciada");
        Gomoku.part.setData(format.format(new Date().getTime()));

        timer = null;
        tempoInicio = System.currentTimeMillis();
        iniciaCronometro();

        tabuleiro.getTabuleiro().setBackground(c.getCorTabuleiro());
        panelPlacar.setBackground(c.getCorPartida());

        panelTabuleiro.setVisible(true);
        panelTabuleiro.setFocusable(true);

    }

    public void criarTabuleiro() {
        panelTabuleiro = tabuleiro.getTabuleiro();
        panelTabuleiro.setBounds(20, 50, 500, 500); //(x, y, width, height)
        frame.add(panelTabuleiro);
    }

    public void criarRanking() {
        rankingJDialog = new JDialog(frame, true);
        rankingJDialog = ranking.getRanking();
        rankingJDialog.setSize(500, 300);
        rankingJDialog.setLocation(430, 200);
        rankingJDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        rankingJDialog.setVisible(true);
    }

    public void criarSobre() {
        sobreJDialog = new JDialog(frame, true);
        sobreJDialog = sobre.getSobre();
        sobreJDialog.setSize(400, 400);
        sobreJDialog.setLocation(480, 150);
        sobreJDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        sobreJDialog.setVisible(true);
    }

    public void criarConfiguracoes() {
        configuracoesJDialog = new JDialog(frame, true);
        configuracoesJDialog = configuracoes.getConfiguracoes();
        configuracoesJDialog.setSize(400, 400);
        configuracoesJDialog.setLocation(480, 150);
        configuracoesJDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        configuracoesJDialog.setVisible(true);
    }

    public void criarHistorico() {
        historicoJDialog = new JDialog(frame, true);
        historicoJDialog = historico.getHistorico();
        historicoJDialog.setSize(400, 400);
        historicoJDialog.setLocation(490, 150);
        historicoJDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        historicoJDialog.setVisible(true);
    }

    public void criarMenu() {
        // barra de menu
        barraJMenuBar = new JMenuBar();
        frame.setJMenuBar(barraJMenuBar);

        netgamesMenu = new JMenu("Netgames");
        itemConectar = new JMenuItem("Conectar");
        itemConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String retorno = Gomoku.conectar("localhost", "user1");
                System.out.println("Conectar");
                JOptionPane.showMessageDialog(null, retorno);
            }
        });
        itemIniciar = new JMenuItem("Iniciar");
        itemIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String retorno = Gomoku.iniciarPartida();
                System.out.println("Iniciar");
                JOptionPane.showMessageDialog(null, retorno);
            }
        });
        itemDesconectar = new JMenuItem("Desconectar");
        itemDesconectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String retorno = Gomoku.desconectar();
                System.out.println("Desconectar");
                JOptionPane.showMessageDialog(null, retorno);
            }
        });
        netgamesMenu.add(itemConectar);
        netgamesMenu.add(itemIniciar);
        netgamesMenu.add(itemDesconectar);
        barraJMenuBar.add(netgamesMenu);

        opcoesJMenu = new JMenu("Opcoes");
        barraJMenuBar.add(opcoesJMenu);

        configuracoesJMenuItem = new JMenuItem("Configuracoes");
        opcoesJMenu.add(configuracoesJMenuItem);
        opcoesJMenu.addSeparator();
        configuracoesJMenuItem.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                criarConfiguracoes();
            }
        }
        );

        historicoJMenuItem = new JMenuItem("Historico");
        opcoesJMenu.add(historicoJMenuItem);
        opcoesJMenu.addSeparator();
        historicoJMenuItem.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                criarHistorico();
                historico.preencherTabela();
            }
        }
        );

        rankingJMenuItem = new JMenuItem("Ranking");
        opcoesJMenu.add(rankingJMenuItem);
        rankingJMenuItem.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                criarRanking();
                ranking.preencherTabela();
            }
        }
        );

        ajudaJMenu = new JMenu("Ajuda");
        barraJMenuBar.add(ajudaJMenu);
        sobreJMenuItem = new JMenuItem("Sobre");
        ajudaJMenu.add(sobreJMenuItem);
        sobreJMenuItem.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                criarSobre();
            }
        }
        );
    }

    public void criarPlacar() {
        panelPlacar = new JPanel();
        panelPlacar.setLayout(new FlowLayout());

        placarJogador1JLabel = new JLabel("     Jogador 1     ");
        panelPlacar.add(placarJogador1JLabel);

        tempoJogoJLabel = new JLabel("     Tempo     ");
        panelPlacar.add(tempoJogoJLabel);

        placarJogador2JLabel = new JLabel("     Jogador 2     ");
        panelPlacar.add(placarJogador2JLabel);

        panelPlacar.setBounds(0, 0, 500, 20); //(x, y, width, height)
        frame.add(panelPlacar);

    }

    public void iniciaCronometro() {
        if (timer == null) {
            timer = new Timer();
            if (tarefa != null) {
                tarefa.cancel();
                timer.purge();
            }
            tarefa = new TimerTask() {
                public void run() {
                    try {
                        String data = format.format(new Date().getTime());//pega a data atual
                        long diferenca = ((System.currentTimeMillis() - tempoInicio) / 1000);//armazena a diferença entre o inicio da exucação do programa e o tempo atual
                        tempoJogoJLabel.setText("Tempo: " + diferenca);
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
        } else {
            tempoInicio = System.currentTimeMillis();
        }
    }

    public static void main(String[] args) {
        IPartida partida = new IPartida();
    }
}
