import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import cuenta.Cuenta;

public class ClienteUDP {
  public static void main(String[] args) {
    // Crea un socket datagrama
    try (Scanner scan = new Scanner(System.in);
         DatagramSocket socket = new DatagramSocket()) {
      System.out.print("Introduzca numero de cuenta: ");
      int ccc = scan.nextInt();
      System.out.print("Introduzca deposito: ");
      double deposito = scan.nextDouble();
      Cuenta cuenta = new Cuenta(ccc);
      cuenta.deposito(deposito);
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(cuenta);
      oos.flush();
      // Envia una petición a 127.0.0.1:4321
      byte[] bufferEnvio = bos.toByteArray();
      DatagramPacket datagrama = new DatagramPacket(bufferEnvio, bufferEnvio.length,
          InetAddress.getLocalHost(), 4321);
      socket.send(datagrama);
      // Recibe la respuesta (hay que crear un buffer para la recepcion)
      byte[] bufferRecepcion = new byte[256];
      datagrama = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
      socket.receive(datagrama);
      // La muestra por pantalla y cierra el socket
      String res = new String(datagrama.getData(),
          0, datagrama.getLength(),
          StandardCharsets.UTF_8);
      System.out.println("El servidor ha dicho: " + res);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
