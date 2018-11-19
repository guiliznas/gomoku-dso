import model.Historico;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HistoricoJUnitTest {
    
    public HistoricoJUnitTest() {
    }
    
    @Test
    public void ordenar(){
        Historico h = new Historico();
        System.out.println(h.getRanking());
    }
    
    @Test
    public void buscarHistorico(){
        Historico h = new Historico();
        System.out.println(h.getHistorico());
    }
    
}
