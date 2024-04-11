package AnalisisI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main2 {

    public static void main(String[] args) {
        leerTablaDeTokensDesdeArchivo("C:\\Users\\angel\\Downloads\\workspace\\Proyecto_Integrador_Automatas1\\src\\AnalisisI\\TablaTokens.txt");
    }

    private static void leerTablaDeTokensDesdeArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Saltar la primera línea (encabezados)
                }

                String[] partes = linea.split("\\s+");
                if (partes.length >= 3) {
                    String lexema = partes[0];
                    String tokenString = partes[1];
                    String posicionEnTabla = partes[2];

                    // Tratar de convertir la posición en la tabla a un entero
                    try {
                        int posicion = Integer.parseInt(posicionEnTabla);
                        // Resto del código para procesar el lexema, token y posición en la tabla
                        System.out.printf("Lexema: %s, Token: %s, Posición en tabla: %d%n", lexema, tokenString, posicion);
                    } catch (NumberFormatException e) {
                        System.err.println("Error: No se pudo convertir la posición en la tabla a un entero.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
