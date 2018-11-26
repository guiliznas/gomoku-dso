package model;

import control.Serializer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Historico {

    private ArrayList<Partida> partidas;

    public Historico() {
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

    public ArrayList<Partida> getHistorico() {
        return partidas;
    }

    public Map<String, Integer> getRanking() {
        HashMap<String, Integer> rank = new LinkedHashMap<String, Integer>();

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

        return sortByValues(rank);
    }

    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

}
