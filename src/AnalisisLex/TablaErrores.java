package AnalisisLex;

public class TablaErrores {
    public enum TipoErrorLexico {
        ERROR_LEXICO,
        IDENTIFICADOR_INVALIDO,
        SIMBOLO_DESCONOCIDO;
    }

    public class ErrorLexico {
        private String lexema;
        private TipoErrorLexico tipoError;
        private int numeroDeLinea;

        public ErrorLexico(String lexema, TipoErrorLexico tipoError, int numeroDeLinea) {
            this.lexema = lexema;
            this.tipoError = tipoError;
            this.numeroDeLinea = numeroDeLinea;
        }

        public String toString() {
            switch (tipoError) {
                case ERROR_LEXICO:
                    return String.format("Error léxico: '%s' en la línea %d.", lexema, numeroDeLinea);
                case IDENTIFICADOR_INVALIDO:
                    return String.format("Identificador inválido: '%s' en la línea %d.", lexema, numeroDeLinea);
                case SIMBOLO_DESCONOCIDO:
                    return String.format("Símbolo desconocido: '%s' en la línea %d.", lexema, numeroDeLinea);
                default:
                    return String.format("Error desconocido en la línea %d.", numeroDeLinea);
            }
        }
    }
}