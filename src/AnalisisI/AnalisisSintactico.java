package AnalisisI;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnalisisSintactico {

    public static void main(String[] args) {
        List<String> tablaTokens = obtenerTablaTokensDesdeArchivo("C:\\Users\\josue\\Desktop\\TablaTokens.txt");
        boolean sintaxisCorrecta = analizarSintaxis(tablaTokens);

        if (sintaxisCorrecta) {
            analizadorSintactico.MetodosDeValidacion.aceptar();
        } else {
            analizadorSintactico.MetodosDeValidacion.error();
        }
    }

    private static List<String> obtenerTablaTokensDesdeArchivo(String rutaArchivo) {
        List<String> tablaTokens = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(rutaArchivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split("\\s+");

                for (String parte : partes) {
                    tablaTokens.add(parte);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return tablaTokens;
    }

    private static boolean analizarSintaxis(List<String> tablaTokens) {
        int indiceToken = 0;

        // Realiza el análisis sintáctico utilizando los métodos de validación
        indiceToken = analizadorSintactico.MetodosDeValidacion.validarEncabezado((ArrayList<String>) tablaTokens, indiceToken);
        if (indiceToken != -1) {
            indiceToken = analizadorSintactico.MetodosDeValidacion.validarVariables((ArrayList<String>) tablaTokens, indiceToken);
            if (indiceToken != -1) {
                indiceToken = analizadorSintactico.MetodosDeValidacion.validarBody((ArrayList<String>) tablaTokens, indiceToken);
                if (indiceToken != -1) {
                    analizadorSintactico.MetodosDeValidacion.aceptar();
                    return true;
                }
            }
        }

        // Si el flujo llega aquí, hubo un error en la sintaxis
        analizadorSintactico.MetodosDeValidacion.error();
        return false;
    }
}
