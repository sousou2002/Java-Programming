import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ApplyWordPositions {
  public static void main(String[] args) throws IOException {
    String nameEntrada = "Entrada2.txt";
    String nameSalida = "Salida2.txt";
    WordPositions wp = new WordPositions();
    try (BufferedReader entrada = new BufferedReader(new FileReader(nameEntrada));
         PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(nameSalida)));) {

          boolean end = false;
          do {
            String line = entrada.readLine();
            end = line == null;
            if (!end) {
              String[] words = line.split(" ");
              for (String word : words) {
                word = word.toUpperCase();
                wp.add(word);
              }
            }
          } while (!end);
          wp.printResults(salida);
    }
  }
}
