import control.Serializer;
import java.awt.Color;
import java.util.ArrayList;
import model.Configuracao;
import model.Partida;
import model.Pessoa;
import org.junit.Test;
import static org.junit.Assert.*;

public class SerializeJUnitTest {

    @Test
    public void partidas(){
        ArrayList<Partida> partidas = new ArrayList<>();
        Serializer s = new Serializer();
        Partida p = new Partida(new Pessoa("Teste1"), new Pessoa("Teste2"));
        p.setVencedor("Teste1");
        partidas.add(p);
//        s.addPartida(p);
        p = new Partida(new Pessoa("Teste1"), new Pessoa("Teste2"));
        p.setVencedor("Teste1");
        partidas.add(p);
//        s.addPartida(p);
        p = new Partida(new Pessoa("Teste1"), new Pessoa("Teste2"));
        p.setVencedor("Teste2");
        partidas.add(p);
//        s.addPartida(p);
        ArrayList<Partida> pList = s.readPartidas();
        System.out.println(pList);
    }

//    @Test
//    public void teste(){
//        System.out.println(Dificuldade.valueOf("FACIL"));
//    }
    
    @Test
    public void configuracao(){
        Configuracao c = new Configuracao();
        Serializer s = new Serializer();
        c.setNome1("Teste 1");
        c.setCorPartida(Color.GRAY);
        c.setCorTabuleiro(Color.blue);
        s.salvarConfiguracao(c);
        System.out.println(c);
        System.out.println(s.readConfig());
        assertEquals(c.toString(), s.readConfig().toString());
    }
    
    @Test
    public void loadPartidas(){
        Serializer s = new Serializer();
        System.out.println(s.readPartidas());
    }
    
}