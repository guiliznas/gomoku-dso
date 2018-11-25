package model;

import control.Serializer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Historico {
    
    private ArrayList<Partida> partidas;
    
    public Historico(){
        try {
            Serializer s = new Serializer();
            partidas = s.readPartidas();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        if (partidas == null) {
            partidas = new ArrayList<>();
            Partida p = new Partida(new Pessoa("Teste1"), new Pessoa("Teste2"));
            p.setVencedor("Teste1");
            partidas.add(p);
            p = new Partida(new Pessoa("Teste1"), new Pessoa("Teste2"));
            p.setVencedor("Teste1");
            partidas.add(p);
            p = new Partida(new Pessoa("Teste1"), new Pessoa("Teste2"));
            p.setVencedor("Teste2");
            partidas.add(p);
        }
    }
    
    public ArrayList<Partida> getHistorico(){
        return partidas;
    }
    
    public Map<String, Integer> getRanking(){
        Map<String, Integer> rank = new LinkedHashMap<String, Integer>();
        
        for (Partida p : partidas) {
            if (rank.get(p.getJogador1().getNome()) == null) {
                rank.put(p.getJogador1().getNome(), 0);
            }
            if (rank.get(p.getJogador2().getNome()) == null) {
                rank.put(p.getJogador2().getNome(), 0);
            }
            if (rank.get(p.getVencedor()) == null) {
                rank.put(p.getVencedor(), 1);
            } else {
                rank.put(p.getVencedor(), rank.get(p.getVencedor()) + 1);
            }
        }
        
        return rank;
    }
    
}