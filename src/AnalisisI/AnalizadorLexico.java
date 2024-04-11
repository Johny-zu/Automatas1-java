package AnalisisI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorLexico {
    private static final String[] PALABRAS_RESERVADAS = {
            "programa", "inicio", "fin", "leer", "escribir",
            "si", "sino", "mientras", "repetir", "hasta",
            "entero", "real", "Cadena", "Logico", "var",
            "Entonces", "Hacer", "+", "-", "=", "<", "<=", ">", ">=", "==", "!=",
            "&", "|", "!", "(", ")", ";", ",", ":","*"
    };
    private List<Token> tablaDeTokens = new ArrayList<>();
    private List<Error> tablaDeErrores = new ArrayList<>();

    public List<Token> getTablaDeTokens() {
        return tablaDeTokens;
    }

    public List<Error> getTablaDeErrores() {return tablaDeErrores;}
    private void agregarError(String palabra, int numeroDeLinea) {
        tablaDeErrores.add(new Error(palabra, numeroDeLinea));
    }

    public void analizarArchivo(String nombreArchivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            int numeroDeLinea = 1;

            while ((linea = br.readLine()) != null) {
                analizarLinea(linea, numeroDeLinea);
                numeroDeLinea++;
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean esPalabraReservada(String palabra) {
        for (String reservada : PALABRAS_RESERVADAS) {
            if (reservada.equals(palabra)) {
                return true;
            }
        }
        return false;
    }

    private void analizarLinea(String linea, int numeroDeLinea) {
        // Buscar comentarios en la línea
        Pattern pattern = Pattern.compile("//(.*?)(//|$)");
        Matcher matcher = pattern.matcher(linea);

        if (matcher.find()) {
            String comentario = matcher.group(1).trim();
            // Ignorar comentarios vacíos
            if (!comentario.isEmpty() && matcher.group(2) != null) {
                // System.out.println("//" + comentario + "//");
                // Continuar procesando la línea después del comentario
                linea = linea.substring(matcher.end());
            } else {
                // Si no hay comentario válido, procesar el resto de la línea
                linea = linea.substring(0, matcher.start());
            }
        }
        // Dividir la línea en palabras y procesar como antes
        String[] palabras = linea.split("\\s+");
        boolean esToken = false;


        for (String palabra : palabras) {
            palabra = palabra.trim();
            if (palabra.isEmpty()) {
                continue;  // Ignorar palabras vacías
            }


            if (palabra.matches("([a-zA-Z][a-zA-Z0-9_]*)[$%&@?]$")) {
                char tipoIdentificador = palabra.charAt(palabra.length() - 1);
                int tokenType = -100;
                esToken = true;

                switch (tipoIdentificador) {
                    case '$':
                        tokenType = -51; // Identificador de valor entero
                        esToken = true;

                        break;
                    case '%':
                        tokenType = -52; // Identificador de valor real
                        esToken = true;

                        break;
                    case '&':
                        tokenType = -53; // Identificador de tipo String
                        esToken = true;

                        break;
                    case '@':
                        tokenType = -54; // Identificador de valor lógico
                        esToken = true;

                        break;
                    case '?':
                        tokenType = -55;
                        esToken = true;
                        break;

                }

                tablaDeTokens.add(new Token(palabra, tokenType, -2, numeroDeLinea));
                esToken = true;
            } else if (palabra.matches("\\d+[$%&@?]?$")) {
                // Manejar números enteros seguidos opcionalmente por $%&@?
                char tipoIdentificador = palabra.charAt(palabra.length() - 1);
                int tokenType = 0;

                switch (tipoIdentificador) {
                    case '$':
                        tokenType = -51; // Identificador de valor entero
                        esToken = true;

                        break;
                    case '%':
                        tokenType = -52; // Identificador de valor real
                        esToken = true;

                        break;
                    case '&':
                        tokenType = -53; // Identificador de tipo String
                        esToken = true;

                        break;
                    case '@':
                        tokenType = -54; // Identificador de valor lógico
                        esToken = true;

                        break;
                    case '?':
                        tokenType = -55;
                        esToken = true;
                        break;

                }
                esToken = true;

                if (tokenType != 0) { // Agregar esta condición
                    tablaDeTokens.add(new Token(palabra, tokenType, -1, numeroDeLinea));
                    esToken = true;

                }


            }


            // Identificar palabras reservadas
            switch (palabra) {
                case "(":
                    tablaDeTokens.add(new Token(palabra, -73, -1, numeroDeLinea));
                    break;
                case ")":
                    tablaDeTokens.add(new Token(palabra, -74, -1, numeroDeLinea));
                    break;
                case ";":
                    tablaDeTokens.add(new Token(palabra, -75, -1, numeroDeLinea));
                    break;
                case ",":
                    tablaDeTokens.add(new Token(palabra, -76, -1, numeroDeLinea));
                    break;
                case ":":
                    tablaDeTokens.add(new Token(palabra, -77, -1, numeroDeLinea));
                    break;
                case "programa":
                    tablaDeTokens.add(new Token(palabra, -1, -1, numeroDeLinea));
                    break;
                case "inicio":
                    tablaDeTokens.add(new Token(palabra, -2, -1, numeroDeLinea));
                    break;
                case "fin":
                    tablaDeTokens.add(new Token(palabra, -3, -1, numeroDeLinea));
                    break;
                case "leer":
                    tablaDeTokens.add(new Token(palabra, -4, -1, numeroDeLinea));
                    break;
                case "escribir":
                    tablaDeTokens.add(new Token(palabra, -5, -1, numeroDeLinea));
                    break;
                case "si":
                    tablaDeTokens.add(new Token(palabra, -6, -1, numeroDeLinea));
                    break;
                case "sino":
                    tablaDeTokens.add(new Token(palabra, -7, -1, numeroDeLinea));
                    break;
                case "mientras":
                    tablaDeTokens.add(new Token(palabra, -8, -1, numeroDeLinea));
                    break;
                case "repetir":
                    tablaDeTokens.add(new Token(palabra, -9, -1, numeroDeLinea));
                    break;
                case "hasta":
                    tablaDeTokens.add(new Token(palabra, -10, -1, numeroDeLinea));
                    break;
                case "entero":
                    tablaDeTokens.add(new Token(palabra, -11, -1, numeroDeLinea));
                    break;
                case "real":
                    tablaDeTokens.add(new Token(palabra, -12, -1, numeroDeLinea));
                    break;
                case "Cadena":
                    tablaDeTokens.add(new Token(palabra, -13, -1, numeroDeLinea));
                    break;
                case "Logico":
                    tablaDeTokens.add(new Token(palabra, -14, -1, numeroDeLinea));
                    break;
                case "var":
                    tablaDeTokens.add(new Token(palabra, -15, -1, numeroDeLinea));
                    break;
                case "Entonces":
                    tablaDeTokens.add(new Token(palabra, -16, -1, numeroDeLinea));
                    break;
                case "Hacer":
                    tablaDeTokens.add(new Token(palabra, -17, -1, numeroDeLinea));
                    break;
                case "+":
                    tablaDeTokens.add(new Token(palabra, -24, -1, numeroDeLinea));
                    break;
                case "-":
                    tablaDeTokens.add(new Token(palabra, -25, -1, numeroDeLinea));
                    break;
                case "*":
                    tablaDeTokens.add(new Token(palabra, -21, -1, numeroDeLinea));
                    break;
                case "/":
                    tablaDeTokens.add(new Token(palabra, -22, -1, numeroDeLinea));
                    break;
                case "=":
                    tablaDeTokens.add(new Token(palabra, -26, -1, numeroDeLinea));
                    break;
                case "<":
                    tablaDeTokens.add(new Token(palabra, -31, -1, numeroDeLinea));
                    break;
                case "<=":
                    tablaDeTokens.add(new Token(palabra, -32, -1, numeroDeLinea));
                    break;
                case ">":
                    tablaDeTokens.add(new Token(palabra, -33, -1, numeroDeLinea));
                    break;
                case ">=":
                    tablaDeTokens.add(new Token(palabra, -34, -1, numeroDeLinea));
                    break;
                case "==":
                    tablaDeTokens.add(new Token(palabra, -35, -1, numeroDeLinea));
                    break;
                case "!=":
                    tablaDeTokens.add(new Token(palabra, -36, -1, numeroDeLinea));
                    break;
                case "&":
                    tablaDeTokens.add(new Token(palabra, -41, -1, numeroDeLinea));
                    break;
                case "|":
                    tablaDeTokens.add(new Token(palabra, -42, -1, numeroDeLinea));
                    break;
                case "!":
                    tablaDeTokens.add(new Token(palabra, -43, -1, numeroDeLinea));
                    break;
            }
            esToken = true;
            if (palabra.endsWith("$") || palabra.endsWith("%") || palabra.endsWith("&")|| palabra.endsWith("@") || palabra.endsWith("?") ) {
                continue;
            }
            if (palabra.matches("\\d+[$%&@?]?$")) {
            continue;
            }
            if (palabra.matches(("\"(.*?)\""))) {
                continue;
            }


            if (!esPalabraReservada(palabra) || !esToken ) {
                agregarError(palabra, numeroDeLinea);
            }
        }

              // Identificar constantes enteras
            Pattern enteroPattern = Pattern.compile("\\b\\d+(?![%&])\\b");
            Matcher enteroMatcher = enteroPattern.matcher(linea);
            while (enteroMatcher.find()) {
                tablaDeTokens.add(new Token(enteroMatcher.group(), -61, -1, numeroDeLinea));
                esToken = true;
            }



        // Identificar constantes reales
        Pattern realPattern = Pattern.compile("\\b\\d+\\.\\d+\\b");
        Matcher realMatcher = realPattern.matcher(linea);
        while (realMatcher.find()) {
            tablaDeTokens.add(new Token(realMatcher.group(), -62, -1, numeroDeLinea));
            esToken=true;
        }
        esToken=true;

        // Identificar constantes de cadena
        Pattern cadenaPattern = Pattern.compile("\"(.*?)\"");
        Matcher cadenaMatcher = cadenaPattern.matcher(linea);
        while (cadenaMatcher.find()) {
            String cadena = cadenaMatcher.group(1);
            tablaDeTokens.add(new Token("\"" + cadena + "\"", -63, -1, numeroDeLinea));
            esToken = true;
        }
        esToken=true;


        // Identificar constantes lógicas (verdadero, falso)
        if (linea.contains("verdadero")) {
            tablaDeTokens.add(new Token("verdadero", -64, -1, numeroDeLinea));
            esToken = true;
        } else if (linea.contains("falso")) {
            tablaDeTokens.add(new Token("falso", -65, -1, numeroDeLinea));
            esToken = true;
        }
        esToken=true;

    }




}
