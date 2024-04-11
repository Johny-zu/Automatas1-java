package AnalisisLex;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Mainlll {
    public static void main(String[] args) {
        String nombreArchivo = "C:\\Users\\angel\\Downloads\\workspace\\Proyecto_Integrador_Automatas1\\src\\AnalisisJ\\Entrada.txt";
        String nombreArchivoSalida = "C:\\Users\\angel\\Downloads\\workspace\\Proyecto_Integrador_Automatas1\\src\\AnalisisJ\\TablaTokens.txt";

        AnalizadorLexico analizador = new AnalizadorLexico();
        analizador.analizarArchivo(nombreArchivo);

        // Obtener la tabla de tokens
        List<Token> tablaDeTokens = analizador.getTablaDeTokens();

        // Imprimir la tabla de tokens en el archivo de salida
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivoSalida))) {
            // Escribir encabezado
            writer.write("Lexema              Token  Pos. en tabla     Número de línea\n");

            // Escribir cada token en una nueva línea
            for (Token token : tablaDeTokens) {
                writer.write(token + "\n");
            }
            TablaErrores te = new TablaErrores();
            System.out.print(te.toString());

            System.out.println("Tabla de tokens generada exitosamente en " + nombreArchivoSalida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
