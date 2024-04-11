package AnalisisI;

public class Error {
    private String lexema;
    private int numeroDeLinea;

    public Error(String lexema, int numeroDeLinea) {
        this.lexema = lexema;
        this.numeroDeLinea = numeroDeLinea;
    }

    public String getLexema() {
        return lexema;
    }

    public int getNumeroDeLinea() {
        return numeroDeLinea;
    }

    @Override
    public String toString() {
        return String.format(" %-20s  %d", lexema, numeroDeLinea);
    }
}
