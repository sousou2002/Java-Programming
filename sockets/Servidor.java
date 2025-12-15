
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Socket socket = serverSocket.accept();

        // son equivalentes 
        Scanner fromClient = new Scanner(socket.getInputStream());
        //BufferedReader fromclient2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // son equivalentes
        PrintWriter fromServer = new PrintWriter(socket.getOutputStream());
        //DataOutputStream fromServer2 = new DataOutputStream(socket.getOutputStream());

        Scanner fromConsole = new Scanner(System.in);

        String inputFromServer, inputFromConsole, output;
        while (true) {
            // Mensaje recibido del cliente
            inputFromServer = fromClient.nextLine();
            System.out.println("Client: " + inputFromServer);

            if (inputFromServer.equals("exit")) {
                break;
            }

            // Respuesta del servidor
            System.out.print("Server: ");
            inputFromConsole = fromConsole.nextLine();

            // output = inputfromServer.toUpperCase();
            fromServer.println(inputFromConsole);
            fromServer.flush();

            if (inputFromConsole.equals("exit")) {
                break;
            }

        }
        socket.close();
    }
}
