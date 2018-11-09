package model;

public class Bot extends Jogador {
    
    private Dificuldade dificuldade; // enum

    public Bot (String nome, Dificuldade dificuldade) {
        super(nome);
        this.dificuldade = dificuldade;
    }

    public boolean jogar(){
        // TODO
        return false;
    }

}