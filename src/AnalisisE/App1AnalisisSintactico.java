package AnalisisE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class App1AnalisisSintactico
{
    public static void main(String[] args)
    {
        try {
            String tabla_token = "C:\\Users\\Moony\\Downloads\\Angel\\automatas\\ProyectoIntegrador\\src\\AnalisisE\\tabla_tokens.txt";
            BufferedReader br = new BufferedReader(new FileReader(tabla_token));
            String linea;
            ArrayList<String> tokens = new ArrayList<>();

            while((linea = br.readLine()) != null)
            {
                String[] tokensLine = linea.split("\\|");
                tokens.add(tokensLine[1].trim());
            }

            int indiceToken = 0;

            indiceToken = MetodosDeValidacion.validarEncabezado(tokens, indiceToken);
            if(indiceToken != -1)
            {
                if(tokens.get(indiceToken).equals("-15"))
                {
                    indiceToken++;
                    indiceToken = MetodosDeValidacion.validarVariables(tokens, indiceToken);
                    if(indiceToken != -1)
                    {
                        if(tokens.get(indiceToken).equals("-2"))
                        {
                            indiceToken++;
                            indiceToken = MetodosDeValidacion.validarBody(tokens, indiceToken);
                            if(indiceToken != -1)
                            {
                                MetodosDeValidacion.aceptar();
                            }
                            else
                            {
                                MetodosDeValidacion.error();
                            }
                        }
                        else
                        {
                            MetodosDeValidacion.error();
                        }
                    }
                    else
                    {
                        MetodosDeValidacion.error();
                    }
                }
                else
                {
                    MetodosDeValidacion.error();
                }
            }
            else
            {
                MetodosDeValidacion.error();
            }
            br.close();
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.toString());
        }
    }
}