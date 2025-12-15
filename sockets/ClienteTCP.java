
import java.io.*;
import java.net.*;

public class ClienteTCP {

    public static void main(String[] args) {
        try (
                // Abro una conexion con mi maquina en el puerto 28102
                Socket c = new Socket("127.0.0.1", 28102); // Obtengo los flujos de entrada y salida
                PrintWriter out = new PrintWriter(c.getOutputStream(), true); 
                BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream())); 
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
                    String input;
                    boolean continuar = true;
                    while(continuar){
                        input = stdIn.readLine(); // Leo de teclado
                        continuar = input != null && !input.equalsIgnoreCase("exit");
                        if(continuar)
                        {
                            out.println(input);
                            System.out.println("Respuesta del servidor:" + in.readLine());
                        }
                    }

        } catch (UnknownHostException h) {
            System.out.println("Host desconocido" + h.getMessage());
            System.exit(1);

        } catch (IOException e) {
            System.out.println("Excepcion en el cliente: " + e.getMessage());
            System.exit(1);

        }

    }
}
