import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordPositions {
  private HashMap<String, List<Integer>> allwords;
  private List<String> sequence;
  private int nextPos;
  public WordPositions() {
    allwords = new HashMap<>();
    sequence = new ArrayList<>();
    nextPos = 1;
  }
  public void add(String str) {
    if (!allwords.containsKey(str)) {
      sequence.add(str);
      allwords.put(str, new ArrayList<>());
    }
    allwords.get(str).add(nextPos);
    nextPos++;
  }
  public void printResults(PrintWriter pw) {
    pw.println("Posiciones de las palabras:");
    for (String word : sequence) {
      pw.print(word);
      for (Integer i : allwords.get(word)) {
        pw.print(" "+i);
      }
      pw.println();
    }
  }
}
