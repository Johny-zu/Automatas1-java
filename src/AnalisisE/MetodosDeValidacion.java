package AnalisisE;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MetodosDeValidacion
{
    public static int validarEncabezado(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals(-1))
        {
            indiceToken++;
            if(tokens.get(indiceToken).equals("-55"))
            {
                indiceToken++;
                if(tokens.get(indiceToken).equals("-75"))
                {
                    return indiceToken+1;
                }
            }
        }
        return -1;
    }

    public static int validarVariables(ArrayList<String> tokens, int indiceToken)
    {
        indiceToken++;
        while(indiceToken >= 0 &&
                indiceToken < tokens.size() &&
                (tokens.get(indiceToken).equals("-51") ||
                        tokens.get(indiceToken).equals("-52") ||
                        tokens.get(indiceToken).equals("-53") ||
                        tokens.get(indiceToken).equals("-54")))
        {
            indiceToken = declaracion(tokens, indiceToken);
        }
        return indiceToken;
    }

    public static int validarBody(ArrayList<String> tokens, int indiceToken)
    {
        indiceToken = bloqueBody(tokens, indiceToken);
        if(indiceToken != -1)
        {
            if(tokens.get(indiceToken).equals("-3"))
            {
                indiceToken++;
                return indiceToken++;
            }
            else
            {
                return -1;
            }
        }
        return indiceToken;
    }

    public static int bloqueBody(ArrayList<String> tokens, int indiceToken)
    {
        while(indiceToken >= 0 &&
                indiceToken < tokens.size() &&
                (tokens.get(indiceToken).equals("-51")) ||
                (tokens.get(indiceToken).equals("-52")) ||
                (tokens.get(indiceToken).equals("-53")) ||
                (tokens.get(indiceToken).equals("-54")))
        {
            if(tokens.get(indiceToken).equals("-51") ||
                    tokens.get(indiceToken).equals("-52") ||
                    tokens.get(indiceToken).equals("-53") ||
                    tokens.get(indiceToken).equals("-54"))
            {
                indiceToken = asignacion(tokens, indiceToken);
            }
            if(indiceToken >= 0 &&
                    indiceToken < tokens.size() &&
                    (tokens.get(indiceToken).equals("-6") ||
                            tokens.get(indiceToken).equals("-8") ||
                            tokens.get(indiceToken).equals("-9")))
            {
                indiceToken = estructurasControl(tokens, indiceToken);
            }
            if(indiceToken >= 0 &&
                    indiceToken < tokens.size() &&
                    (tokens.get(indiceToken).equals("-4") ||
                            tokens.get(indiceToken).equals("-5")))
            {
                indiceToken = escribirOLeer(tokens, indiceToken);
            }
        }
        return indiceToken;
    }

    public static int asignacion(ArrayList<String> tokens, int indiceToken)
    {
        indiceToken = allVar(tokens, indiceToken);
        if(indiceToken != -1)
        {
            if(tokens.get(indiceToken).equals("-26"))
            {
                indiceToken++;
                if(tokens.get(indiceToken).equals("-73"))
                {
                    indiceToken = parentesis(tokens, indiceToken);
                }
                indiceToken = todo(tokens, indiceToken);
                if(indiceToken != -1)
                {
                    if(tokens.get(indiceToken).equals("-75"))
                    {
                        indiceToken++;
                        return indiceToken;
                    }
                }
            }
        }
        return indiceToken;
    }

    public static int estructurasControl(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-6"))
        {
            indiceToken = ifElse(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-8"))
        {
            indiceToken = mientras(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-9"))
        {
            indiceToken = repetirHasta(tokens, indiceToken);
            return indiceToken;
        }
        return indiceToken;
    }

    public static int escribirOLeer(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-4") ||
                tokens.get(indiceToken).equals("-5"))
        {
            indiceToken++;
            if(tokens.get(indiceToken).equals("-73"))
            {
                indiceToken++;
                indiceToken = allVar(tokens, indiceToken);
                if(indiceToken != -1)
                {
                    if(tokens.get(indiceToken).equals("-74"))
                    {
                        indiceToken++;
                        if(tokens.get(indiceToken).equals("-75"))
                        {
                            indiceToken++;
                            return indiceToken;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static int ifElse(ArrayList<String> tokens, int indiceToken)
    {
        indiceToken = bloqueCondicion(tokens, indiceToken);
        if(indiceToken != -1)
        {
            indiceToken = bloqueEstructuras(tokens, indiceToken);
        }
        if(tokens.get(indiceToken).equals("-7"))
        {
            indiceToken++;
            indiceToken = bloqueEstructuras(tokens, indiceToken);
        }
        return indiceToken;
    }

    public static int mientras(ArrayList<String> tokens, int indiceToken)
    {
        indiceToken = bloqueCondicion(tokens, indiceToken);
        if(indiceToken != -1)
        {
            indiceToken = bloqueEstructuras(tokens, indiceToken);
        }
        return indiceToken;
    }

    public static int repetirHasta(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-9"))
        {
            indiceToken++;
            indiceToken = bloqueEstructuras(tokens, indiceToken);
            if(indiceToken != -1)
            {
                indiceToken = bloqueCondicion(tokens, indiceToken);
                if(indiceToken != -1)
                {
                    return indiceToken;
                }
            }
        }
        return indiceToken;
    }

    public static int bloqueEstructuras(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-2"))
        {
            indiceToken++;
            indiceToken = bloqueBody(tokens, indiceToken);
            if(indiceToken != -1)
            {
                if(tokens.get(indiceToken).equals("-3"))
                {
                    indiceToken++;
                    return indiceToken;
                }
            }
        }
        return -1;
    }

    public static int bloqueCondicion(ArrayList<String> tokens, int indiceToken)
    {
        indiceToken = estructura(tokens, indiceToken);
        if(indiceToken != -1)
        {
            if(tokens.get(indiceToken).equals("-74"))
            {
                indiceToken++;
                indiceToken = estructuraFin(tokens, indiceToken);
                if(indiceToken != -1)
                {
                    return indiceToken;
                }
            }
        }
        return -1;
    }

    public static int condicion(ArrayList<String> tokens, int indiceToken)
    {
        indiceToken = todo(tokens, indiceToken);
        indiceToken = todo(tokens, indiceToken);
        return indiceToken;
    }

    public static int estructura(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-6"))
        {
            return indiceToken+1;
        }
        else if(tokens.get(indiceToken).equals("-8"))
        {
            return indiceToken+1;
        }
        else if(tokens.get(indiceToken).equals("-10"))
        {
            return indiceToken+1;
        }
        return -1;
    }

    public static int estructuraFin(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-16"))
        {
            return indiceToken+1;
        }
        else if(tokens.get(indiceToken).equals("-17"))
        {
            return indiceToken+1;
        }
        else if(tokens.get(indiceToken).equals("-75"))
        {
            return indiceToken+1;
        }
        return -1;
    }

    public static int allVar(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-51"))
        {
            indiceToken++;
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-52"))
        {
            indiceToken++;
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-53"))
        {
            indiceToken++;
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-54"))
        {
            indiceToken++;
            return indiceToken;
        }
        return -1;
    }

    public static int declaracion(ArrayList<String> tokens, int indiceToken)
    {
        indiceToken = varComa(tokens, indiceToken);
        if(tokens.get(indiceToken).equals("-77"))
        {
            indiceToken++;
            indiceToken = tipoDato(tokens, indiceToken);
            if(tokens.get(indiceToken).equals("-75"))
            {
                indiceToken++;
                return indiceToken;
            }

        }
        return -1;
    }

    public static int varComa(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-51"))
        {
            indiceToken++;
            indiceToken = comaVar(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-52"))
        {
            indiceToken++;
            indiceToken = comaVar(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-53"))
        {
            indiceToken++;
            indiceToken = comaVar(tokens, indiceToken);
            return indiceToken;

        }
        else if(tokens.get(indiceToken).equals("-54"))
        {
            indiceToken++;
            indiceToken = comaVar(tokens, indiceToken);
            return indiceToken;
        }
        return -1;
    }

    public static int comaVar(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-76"))
        {
            indiceToken++;
            indiceToken = varComa(tokens, indiceToken);
            return indiceToken;
        }
        return indiceToken;
    }

    public static int tipoDato(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-11"))
        {
            return indiceToken+1;
        }
        else if(tokens.get(indiceToken).equals("-12"))
        {
            return indiceToken+1;
        }
        else if(tokens.get(indiceToken).equals("-13"))
        {
            return indiceToken+1;
        }
        else if(tokens.get(indiceToken).equals("-14"))
        {
            return indiceToken+1;
        }
        return -1;
    }

    public static int signos(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-21"))
        {
            indiceToken++;
            indiceToken = todo(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-22"))
        {
            indiceToken++;
            indiceToken = todo(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-24"))
        {
            indiceToken++;
            indiceToken = todo(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-25"))
        {
            indiceToken++;
            indiceToken = todo(tokens, indiceToken);
            return indiceToken;
        }
        return indiceToken;
    }

    public static int constantes(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-61"))
        {
            indiceToken++;
            indiceToken = signos(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-62"))
        {
            indiceToken++;
            indiceToken = signos(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-63"))
        {
            indiceToken++;
            indiceToken = signos(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-64"))
        {
            indiceToken++;
            indiceToken = signos(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-65"))
        {
            indiceToken++;
            indiceToken = signos(tokens, indiceToken);
            return indiceToken;
        }
        return -1;
    }

    public static int parentesis(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-73"))
        {
            indiceToken++;
            indiceToken = todo(tokens, indiceToken);
            if(indiceToken != -1)
            {
                if(tokens.get(indiceToken).equals("-74"))
                {
                    indiceToken++;
                    indiceToken = signos(tokens, indiceToken);
                    return indiceToken;
                }
            }
        }
        return -1;
    }

    public static int var(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-51"))
        {
            indiceToken++;
            indiceToken = signos(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-52"))
        {
            indiceToken++;
            indiceToken = signos(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-53"))
        {
            indiceToken++;
            indiceToken = signos(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-54"))
        {
            indiceToken++;
            indiceToken = signos(tokens, indiceToken);
            return indiceToken;
        }
        return -1;
    }

    public static int relacionales(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-31"))
        {
            indiceToken++;
            indiceToken = todo(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-32"))
        {
            indiceToken++;
            indiceToken = todo(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-33"))
        {
            indiceToken++;
            indiceToken = todo(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-34"))
        {
            indiceToken++;
            indiceToken = todo(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-35"))
        {
            indiceToken++;
            indiceToken = todo(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-36"))
        {
            indiceToken++;
            indiceToken = todo(tokens, indiceToken);
            return indiceToken;
        }
        return -1;
    }

    public static int todo(ArrayList<String> tokens, int indiceToken)
    {
        if(tokens.get(indiceToken).equals("-61") ||
                tokens.get(indiceToken).equals("-62") ||
                tokens.get(indiceToken).equals("-63") ||
                tokens.get(indiceToken).equals("-64") ||
                tokens.get(indiceToken).equals("-65"))
        {
            indiceToken = constantes(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-73"))
        {
            indiceToken = parentesis(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-51") ||
                tokens.get(indiceToken).equals("-52") ||
                tokens.get(indiceToken).equals("-53") ||
                tokens.get(indiceToken).equals("-54"))
        {
            indiceToken = var(tokens, indiceToken);
            return indiceToken;
        }
        else if(tokens.get(indiceToken).equals("-31") ||
                tokens.get(indiceToken).equals("-32") ||
                tokens.get(indiceToken).equals("-33") ||
                tokens.get(indiceToken).equals("-34") ||
                tokens.get(indiceToken).equals("-35") ||
                tokens.get(indiceToken).equals("-36"))
        {
            indiceToken = relacionales(tokens, indiceToken);
            return indiceToken;
        }
        return -1;
    }

    public static void aceptar()
    {
        JOptionPane.showMessageDialog(null, "El lenguaje de prueba es correcto");
    }

    public static void error()
    {
        JOptionPane.showMessageDialog(null, "Error en la sintaxis del lenguaje de prueba");
    }
}