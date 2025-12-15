
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            // Loopback de mi maquina 127.0.0.1 a la cual esta el servidor conectado
            Socket socket = new Socket("localhost", 12345);

            Scanner fromServer = new Scanner(socket.getInputStream());
            PrintWriter fromClient = new PrintWriter(socket.getOutputStream(), true);
            Scanner fromConsole = new Scanner(System.in);

            String input, output;
            while (true) {
                System.out.println("Client: ");
                input = fromConsole.nextLine();
                fromClient.println(input);

                if (input.equals("exit")) {
                    break;
                }

                output = fromServer.nextLine();
                System.out.print("Server: ");
                System.out.println(output);

                if (output.equals("exit")) {
                    break;
                }

            }
            socket.close();

        } catch (IOException e) {
            System.out.println("Excepcion: " + e.getMessage());
        }

    }
}
