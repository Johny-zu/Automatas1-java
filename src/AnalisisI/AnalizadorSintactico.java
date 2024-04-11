package AnalisisI;
import java.util.List;

public class AnalizadorSintactico {

    public static void main(String[] args) {
        AnalizadorLexico lexico = new AnalizadorLexico();
        lexico.analizarArchivo("C:\\Users\\angel\\Downloads\\workspace\\Proyecto_Integrador_Automatas1\\src\\AnalisisI\\TablaTokens.txt");

        List<Token> tablaDeTokens = lexico.getTablaDeTokens();
        System.out.println("Tokens: " + tablaDeTokens);

        if (parseProgram(tablaDeTokens)) {
            System.out.println("La estructura del programa es correcta.");
        } else {
            System.out.println("Error: La estructura del programa es incorrecta.");
        }
        System.out.println("Tokens: " + tablaDeTokens);
    }

    private static boolean parseProgram(List<Token> tokens) {
        // Asegurarse de que haya suficientes tokens para verificar la estructura del programa
        if (tokens.size() < 5) {
            return false;
        }

        // Verificar la palabra clave "programa"
        if (expectKeyword(tokens, "programa")) {
            // Consumir la palabra clave "programa"
            tokens.remove(0);

            // Verificar un identificador
            if (expectIdentifier(tokens)) {
                // Consumir el identificador
                tokens.remove(0);

                // Verificar un punto y coma
                if (expectSymbol(tokens, ";")) {
                    // Consumir el punto y coma
                    tokens.remove(0);

                    // Analizar las variables
                    if (parseVariables(tokens)) {
                        // Verificar la palabra clave "inicio"
                        if (expectKeyword(tokens, "inicio")) {
                            // Consumir la palabra clave "inicio"
                            tokens.remove(0);

                            // Analizar las sentencias (cuerpo del programa)
                            if (parseSentencias(tokens)) {
                                // Verificar la palabra clave "fin"
                                return expectKeyword(tokens, "fin");
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private static boolean parseVariables(List<Token> tokens) {
        if (tokens.isEmpty()) {
            return false;
        }

        if (tokens.get(0).getLexema().equals("var")) {
            tokens.remove(0); // Consumir 'var'
            return parseDeclaracionVariable(tokens) && parseVariables(tokens);
        }

        return true; // No hay variables que declarar
    }

    private static boolean parseDeclaracionVariable(List<Token> tokens) {
        return expectIdentifier(tokens) &&
                expectTipo(tokens) &&
                expectSymbol(tokens, ";");
    }

    private static boolean parseSentencias(List<Token> tokens) {
        while (!tokens.isEmpty() && !tokens.get(0).getLexema().equals("fin")) {
            if (!parseSentencia(tokens)) {
                return false;
            }
        }
        return true;
    }

    private static boolean parseSentencia(List<Token> tokens) {
        // Por simplicidad, admitimos solo sentencias de asignación
        return parseAsignacion(tokens);
    }

    private static boolean parseAsignacion(List<Token> tokens) {
        return expectIdentifier(tokens) &&
                expectSymbol(tokens, "=") &&
                parseExpresion(tokens) &&
                expectSymbol(tokens, ";");
    }

    private static boolean parseExpresion(List<Token> tokens) {
        // Análisis simplificado de expresiones
        return true;
    }

    // Métodos de ayuda para la expectativa básica de tokens
    private static boolean expectKeyword(List<Token> tokens, String keyword) {
        if (!tokens.isEmpty() && tokens.get(0).getLexema().equals(keyword)) {
            tokens.remove(0); // Consumir la palabra clave esperada
            return true;
        }
        return false;
    }

    private static boolean expectIdentifier(List<Token> tokens) {
        if (!tokens.isEmpty() && tokens.get(0).getToken() == -51) {
            tokens.remove(0); // Consumir el identificador esperado
            return true;
        }
        return false;
    }

    private static boolean expectTipo(List<Token> tokens) {
        // Expectativa simplificada de tipo
        return expectSymbol(tokens, "$") || expectSymbol(tokens, "%");
    }

    private static boolean expectSymbol(List<Token> tokens, String symbol) {
        if (!tokens.isEmpty() && tokens.get(0).getLexema().equals(symbol)) {
            tokens.remove(0); // Consumir el símbolo esperado
            return true;
        }
        return false;
    }
}
