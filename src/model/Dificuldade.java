package model;

public enum Dificuldade {
    FACIL (1),
    MEDIO (2),
    DIFICIL (3);

    public int nivel;

    Dificuldade(int nivel){
        this.nivel = nivel;
    }

}