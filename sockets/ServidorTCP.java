
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;

public class ServidorTCP {

    public static void main(String[] args) {
        // El servidor esta escuchando
        try (ServerSocket s = new ServerSocket(28102);) {
            System.out.println("Servidor escuchando en el puerto 28102");
            // Bucle infinito para aceptar conexiones
            while (true) {
                try (
                    Socket c = s.accept(); 
                    PrintWriter out = new PrintWriter(c.getOutputStream(), true); 
                    BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));) {
                    // Aceptada nueva conexion
                    System.out.println("Nueva conexion desde " + c.getInetAddress() + ":" + c.getPort());

                    // Leer mensaje del cliente
                    String inputLine;
                    boolean continuar = true;
                    while (continuar) {
                        inputLine = in.readLine();
                        continuar = inputLine != null;
                        if (continuar) {
                            File f = new File(inputLine);
                            if (f.exists()) {
                                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                                    int numlineas = 0;
                                    while (br.readLine() != null) {
                                        numlineas++;
                                    }
                                    out.println(numlineas);
                                } catch (IOException e) {
                                    out.println("Error al leer el archivo");
                                }
                            } else {
                                out.println("El fichero no existe");
                            }
                            System.out.println("recibido: " + inputLine);
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Excepcion en la conexion :" + e.getMessage());
                }

            }
        } catch (IOException e) {
            System.out.println("Excepcion en el servidor: " + e.getMessage());
        }

    }

}
