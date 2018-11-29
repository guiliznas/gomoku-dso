package control;

import javax.swing.JOptionPane;
import principal.Gomoku;

public class Utils {
    
    public static void questComecarNova() {
//        int opc = JOptionPane.showConfirmDialog(null, "Começar nova partida?");
        String[] buttons = {"Jogador", "Bot", "Sair"};
        int opc = JOptionPane.showOptionDialog(null, "Jogar contra quem?", "Nova Partida",
                JOptionPane.YES_NO_CANCEL_OPTION, 3, null, buttons, null);
        System.out.println(opc); // 0 - ok; 1- nao; 2 - cancel
        if (opc == 0) {
            System.out.println("Nova");
            Gomoku.roboJogando = false;
        } else if (opc == 1) {
            Gomoku.roboJogando = true;
        } else if (opc == 2) {
            System.exit(0);
        }
        Gomoku.partida.novaPartida();
    }
    
}