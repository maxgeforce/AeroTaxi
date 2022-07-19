package Travel;

public enum Distances {
    BsAs_Cor (695),
    BsAs_San (1400),
    BsAs_Mon (950),
    Cor_Mon (1190),
    Cor_San (1050),
    Mon_San (2100),
    ;

    private final int numero;
    Distances(int numero) {
        this.numero = numero;
    }

    public int retornarNumero() {
        return numero;
    }

}

