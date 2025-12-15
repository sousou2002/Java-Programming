import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ApplyCompactador {
  public static void main(String[] args) throws IOException {

    //String currentPath = new java.io.File(".").getCanonicalPath();
    //System.out.println("Current dir:" + currentPath);

    String nameEntrada = "entrada.txt";
    String nameSalida = "Salida.txt";
    Compactador comp = new Compactador();
    try (BufferedReader entrada = new BufferedReader(new FileReader(nameEntrada));
         PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(nameSalida)));) {
      boolean end = false;
      boolean primero = true;
      do {
        String line = entrada.readLine();
        end = line == null;
        if (!end) {
          String[] words = line.split(" ");
          for (String word : words) {
            word = word.toUpperCase();
            end = word.equals("FIN");
            if (end)
              break;
            String str = comp.add(word);
            if (!str.equals("")) {
              if (!primero) salida.print(' ');
              salida.print(str);
              primero = false;
            }
          }
        }
      } while (!end);
      salida.println();
      salida.println("El porcentaje de compactacion es " + (comp.ratioCompactacion() * 100) + "%");
    }
  }
}
