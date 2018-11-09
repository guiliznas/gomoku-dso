package control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class UtilsUI {
    
    public static Font fonteBotaoPrimario;
    public static Font fonteBotaoSecundario;
    public static Font fonteMenu;
    public static Font fonteTexto;

    public static Color corTabuleiro;
    public static Color corPartida;
    public static Color corBotaoPrimario;
    public static Color corBotaoSecundario;

    public static Image imgCampoVazio;
    public static Image imgJogador1;
    public static Image imgJogador2;
    public static Image getImagem(byte campo){
        switch (campo){
            case (byte)1:
                return imgJogador1;
            case (byte)-1:
                return imgJogador2;
            case (byte)0:
                return imgCampoVazio;
            default:
                return null;
        }
    }

}