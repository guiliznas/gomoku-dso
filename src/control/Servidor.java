package control;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import javax.swing.JOptionPane;

/**
 * @author(name= Guilherme de Liz, date= 19/10/2019)
 */
public class Servidor implements OuvidorProxy {

    private static final long serialVersionUID = 1L;
    protected Proxy proxy;

    public Servidor() {
        super();
        this.proxy = Proxy.getInstance();
        proxy.addOuvinte(this);
        System.out.println("Servidor criado!");
    }

    public String conectar(String server, String nick) {

        try {
            this.proxy.conectar(server, nick);
        } catch (JahConectadoException e) {
            e.printStackTrace();
            return "Já conectado";
        } catch (NaoPossivelConectarException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
            return "Não foi possível conectar";
        } catch (ArquivoMultiplayerException ex) {
            ex.printStackTrace();
            return "Sem arquivo de configurações";
        }
        return "Conectado com sucesso!";
    }

    public String desconectar() {
        try {
            proxy.desconectar();
        } catch (NaoConectadoException e) {
            e.printStackTrace();
            return "Não está conectado";
        }
        return "Desconectado com sucesso!";
    }

    public String iniciarPartida() {
        try {
            proxy.iniciarPartida(new Integer(1));
        } catch (NaoConectadoException e) {
            e.printStackTrace();
            return "Erro ao tentar iniciar partida";
        }
        return "Solicitação de inicio enviada";
    }

    @Override
    public void iniciarNovaPartida(Integer posicao) {
        JOptionPane.showMessageDialog(null, "Solicitação de inicio recebida");
    }

    @Override
    public void finalizarPartidaComErro(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receberMensagem(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receberJogada(Jogada jogada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tratarConexaoPerdida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}