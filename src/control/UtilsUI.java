package control;

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
            break;
            case (byte)-1:
                return imgJogador2;
            break;
            case (byte)0:
                return imgCampoVazio;
            break;
            default:
                return null;
        }
    }

}