
import java.io.*;
import java.net.*;

public class ClienteTCP {

    public static void main(String[] args) {
        try (
                // Abrimos una conexión con nuestra máquina en el puerto 4321
                Socket client = new Socket("127.0.0.1", 4321); // Obtenemos un controlador de fichero de entrada/salida del socket
                 PrintWriter out = new PrintWriter(client.getOutputStream(), true); DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream())); BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            String input1, input2;
            System.out.print("Introduzca la ruta de un fichero de texto en el servidor: ");
            input1 = stdIn.readLine();
            System.out.print("Introduzca la ruta de destino en el cliente: ");
            input2 = stdIn.readLine();
            System.out.println("Enviando nombre de fichero al servidor!");
            out.println(input1);
            long longitud = in.readLong();
            if (longitud == -1) {
                System.out.println("Error de fichero en el servidor");
            } else {
                try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(input2));) {
                    for (int i = 0; i < longitud; i++) {
                        os.write(in.read());
                    }
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("El host es desconocido " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: "
                    + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

    }
}
