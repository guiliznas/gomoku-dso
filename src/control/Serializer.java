package control;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import model.Configuracao;
import model.Partida;

public class Serializer {
    /**
     * Abre arquivo que armazena partidas
     */
    public void openFilePartidas() {

    }

    /**
     * Fecha arquivo
     */
    public void closeFilePartidas() {

    }

    /**
     * Adiciona Partida no arquivo de serialização
     *
     * @param r Partida a ser salva
     */
    public void addPartida(Partida p) {

    }

    /**
     * Leitura de todos registros de Partidas
     *
     * @return ArrayList com todas rodads registradas
     */
    public ArrayList<Partida> readPartidas() {
        Partida partida;
        ArrayList<Partida> partidas = new ArrayList<>();

        return partidas;
    }

    public Configuracao readConfig() {

        Configuracao config = new Configuracao();
        return config;
    }

    public void openFileConfig() {
    }

    public void salvarConfiguracao(Configuracao c) {

    }

}
