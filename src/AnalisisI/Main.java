package AnalisisI;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String nombreArchivo = "C:\\Users\\angel\\Downloads\\workspace\\Proyecto_Integrador_Automatas1\\src\\AnalisisI\\Entrada.txt";
        String nombreArchivoTokens = "C:\\Users\\angel\\Downloads\\workspace\\Proyecto_Integrador_Automatas1\\src\\AnalisisI\\TablaTokens.txt";
        String nombreArchivoErrores = "C:\\Users\\angel\\Downloads\\workspace\\Proyecto_Integrador_Automatas1\\src\\AnalisisI\\TablaErrores.txt";

        AnalizadorLexico analizador = new AnalizadorLexico();
        analizador.analizarArchivo(nombreArchivo);

        // Obtener la tabla de tokens
        List<Token> tablaDeTokens = analizador.getTablaDeTokens();
        // Obtener la tabla de errores
        List<Error> tablaDeErrores = analizador.getTablaDeErrores();

        // Imprimir la tabla de tokens en el archivo de salida
        try (BufferedWriter writerTokens = new BufferedWriter(new FileWriter(nombreArchivoTokens))) {
            // Escribir encabezado
            writerTokens.write("Lexema              Token  Pos. en tabla     Número de línea\n");

            // Escribir cada token en una nueva línea
            for (Token token : tablaDeTokens) {
                writerTokens.write(token + "\n");
            }

            System.out.println("Tabla de tokens generada exitosamente en " + nombreArchivoTokens);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir la tabla de errores en el archivo de salida
        try (BufferedWriter writerErrores = new BufferedWriter(new FileWriter(nombreArchivoErrores))) {
            // Escribir encabezado
            writerErrores.write("Lexema no válido      Número de línea\n");

            // Escribir cada error en una nueva línea
            for (Error error : tablaDeErrores) {
                writerErrores.write(error + "\n");
            }

            System.out.println("Tabla de errores generada exitosamente en " + nombreArchivoErrores);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}