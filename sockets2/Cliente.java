
import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) {
        try (Socket c = new Socket("localhost", 4321); 
        PrintWriter out = new PrintWriter(c.getOutputStream(), true); 
        BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream())); 
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) 
        {//Intercambio de datos
            String entrada;
            boolean continuar = true;
            while (continuar) {
                //Leer datos de teclado
                entrada = stdIn.readLine();
                continuar = entrada != null && !entrada.equalsIgnoreCase("exit");
                if (continuar) {
                    //Enviar datos al servidor
                    out.println(entrada);
                    //Leer respuesta del servidor
                    String respuesta = in.readLine();
                    System.out.println("Respuesta del servidor: " + respuesta);
                }

            }

        } catch (IOException e) {
            e.getStackTrace();
        }

    }
}
