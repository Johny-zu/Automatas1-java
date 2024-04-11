package AnalisisLex;
public class Token {
    private String lexema;
    private int token;
    private int posicionEnTabla;
    private int numeroDeLinea;

    public Token(String lexema, int token, int posicionEnTabla, int numeroDeLinea) {
        this.lexema = lexema;
        this.token = token;
        this.posicionEnTabla = posicionEnTabla;
        this.numeroDeLinea = numeroDeLinea;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public int getPosicionEnTabla() {
        return posicionEnTabla;
    }

    public void setPosicionEnTabla(int posicionEnTabla) {
        this.posicionEnTabla = posicionEnTabla;
    }

    public int getNumeroDeLinea() {
        return numeroDeLinea;
    }

    public void setNumeroDeLinea(int numeroDeLinea) {
        this.numeroDeLinea = numeroDeLinea;
    }

    @Override
    public String toString() {
        return String.format(" %-20s  % -6d  % -15d  % -5d",
                lexema, token, posicionEnTabla, numeroDeLinea);
    }
}

