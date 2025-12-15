
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4321);) {
            System.out.println("Servidor inicializado");
            while (true) {
                System.out.println("Esperando al cliente!");
                try (
                        Socket clientSocket = serverSocket.accept(); DataOutputStream out = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream())); BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));) {
                    System.out.println("Aceptada nueva conexion");
                    String inputLine = in.readLine();
                    if ((inputLine) != null) {
                        System.out.println("Fichero a enviar: " + inputLine);
                        File f = new File(inputLine);
                        if (!f.exists()) {
                            System.out.println("Fichero no existe!");
                            out.writeLong(-1);
                        } else {
                            long l = f.length();
                            System.out.println("Fichero existe, longitud " + l);
                            out.writeLong(l);
                            System.out.println("Longitud enviada ");
                            try (BufferedInputStream bs = new BufferedInputStream(new FileInputStream(f));) {
                                for (long i = 0; i < l; i++) {
                                    //System.out.println("enviando byte "+i);
                                    out.writeByte(bs.read());
                                    //System.out.println("enviado byte "+i);
                                }
                                System.out.println("fichero terminado de enviar");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        out.flush();
                    }
                } catch (IOException e) {
                    System.out.println("Excepcion con una conexion con un cliente");
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            System.out.println("Excepcion al crear el socket servidor");
            System.out.println(e.getMessage());
        }

    }
}
