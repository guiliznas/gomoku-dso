package control;

import java.awt.Color;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import model.Configuracao;
import model.Partida;

public class Serializer {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Scanner configI;
    private Formatter configO;

    /**
     * Abre arquivo que armazena partidas
     */
    public void openFilePartidas() {
        try {
            ArrayList<Partida> records = readPartidas();
            output = new ObjectOutputStream(
                    new FileOutputStream("partidas.ser"));
            System.out.println("Teste");
            for (Partida r : records) {
                output.writeObject(r);
            }
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "Error opening file.");
            System.err.println("Error opening file.");
        }
    }

    /**
     * Fecha arquivo
     */
    public void closeFilePartidas() {
        try {
            if (output != null) {
                output.close();
                System.out.println("File closed.");
            }
        } catch (IOException ioException) {
            System.err.println("Error closing file.");
            System.exit(1);
        }
    }

    /**
     * Adiciona Partida no arquivo de serialização
     *
     * @param r Partida a ser salva
     */
    public void addPartida(Partida p) {
        try {
            if (output == null) {
                openFilePartidas();
            }
            output.writeObject(p);
//            System.out.println(r);
            System.out.println("Salvou registro");
            output.close();
            System.out.println("File closed.");
//            System.out.println(readRecords().size());
        } catch (IOException ioException) {
            System.err.println("Error writing to file.");
        }
    }

    /**
     * Leitura de todos registros de Partidas
     *
     * @return ArrayList com todas rodads registradas
     */
    public ArrayList<Partida> readPartidas() {
        Partida partida;
        ArrayList<Partida> partidas = new ArrayList<>();
        try {
            input = new ObjectInputStream(
                    new FileInputStream("partidas.ser"));
            System.out.println("Lido");
            while (true) {
                partida = (Partida) input.readObject();
                partidas.add(partida);
            }
        } catch (EOFException endOfFileException) {
//            System.out.println(partidas);
            return partidas;
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Unable to create object.");
//            System.out.println(partidas);
            return partidas;
        } catch (IOException ioException) {
            System.out.println(ioException);
            System.err.println("Error during reading from file {partidas.ser}.");
        }
        System.out.println(partidas);
        return partidas;
    }

    public Configuracao readConfig() {

        Configuracao config = new Configuracao();
        try {
            configI = new Scanner(new File("config.txt")).useDelimiter(" - ");
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error opening file.");
//            System.exit(1);
        }
        try {
            while (configI.hasNext()) {
                String configText = configI.nextLine();
                config.setNome1(configText.split(" - ")[0]);
                config.setNome2(configText.split(" - ")[1]);
                config.setTamanhoTabuleiro(Integer.parseInt(configText.split(" - ")[2]));
                config.setCorPartida(new Color(Integer.parseInt(configText.split(" - ")[3])));
                config.setCorTabuleiro(new Color(Integer.parseInt(configText.split(" - ")[4])));
            }
        } catch (NoSuchElementException elementException) {
            System.out.println(elementException);
            System.err.println("File improperly formed.");
            configI.close();
            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file.");
            System.exit(1);
        } finally {
            configI.close();
        }
        System.out.println(config);
        return config;
    }

    public void openFileConfig() {
        try {
            configO = new Formatter("config.txt");
        } catch (SecurityException securityException) {
            System.err.println("You do not have write access to this file.");
            System.exit(1);
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error opening or creating file.");
            System.exit(1);
        }
    }

    public void salvarConfiguracao(Configuracao c) {
        openFileConfig();
        try {
            configO.format("%s - %s - %d - %s - %s\n", c.getNome1(),
                    c.getNome2(),
                    c.getTamanhoTabuleiro(), Integer.toString(c.getCorPartida().getRGB()),
                    Integer.toString(c.getCorTabuleiro().getRGB()));
            System.out.println("Configuração salva.");
        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Error writing to file.");
            return;
        } catch (NoSuchElementException elementException) {
            System.err.println("Invalid input. Please try again.");
        } finally {
            configO.close();
        }
    }

}
