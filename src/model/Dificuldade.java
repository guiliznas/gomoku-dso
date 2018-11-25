package model;

public enum Dificuldade {
    FACIL (2),
    MEDIO (1),
    DIFICIL (0);

    public int nivel;

    Dificuldade(int nivel){
        this.nivel = nivel;
    }
    
    public int get(){
        return nivel;
    }
    
}