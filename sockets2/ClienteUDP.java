
import cuenta.Cuenta;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.*;
import java.nio.*;
import java.net.*;

public class ClienteUDP {

    public static void main(String[] args) {
        //codigo del cliente UDP
        try (Scanner scan = new Scanner(System.in); DatagramSocket socket = new DatagramSocket()) {
            System.out.print("Introoduce numero de cuenta:");
            int ccc = scan.nextInt();
            System.out.print("Introduzca deposito: ");
            double deposito = scan.nextDouble();
            Cuenta cuenta = new Cuenta(ccc, deposito);
            cuenta.deposito(deposito);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(cuenta);
            oos.flush();

            //Envia una peticion a 127.0.0.1:4321
            byte[] bufferEnvio = bos.toByteArray();

            DatagramPacket datagrama = new DatagramPacket(bufferEnvio, bufferEnvio.length, InetAddress.getLocalHost(), 4321);
            socket.send(datagrama);

            //Recibe la respuesta del servidor
            byte[] bufferReception = new byte[256];
            datagrama = new DatagramPacket(bufferReception, bufferReception.length);
            socket.receive(datagrama);
            //La muestra por pantalla y cierra el socket
            String res = new String(datagrama.getData(),
                    0, datagrama.getLength(),
                    StandardCharsets.UTF_8);
            System.out.println("El servidor ha dicho: " + res);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
