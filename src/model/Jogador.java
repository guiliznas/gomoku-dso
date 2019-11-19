package model;

import java.io.Serializable;

public class Jogador implements Serializable{

    private String nome;

    public Jogador(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Jogador{" + "nome=" + nome + '}';
    }

}