import java.util.HashMap;

public class Compactador {
  private HashMap<String, Integer> positions;
  private int totalCaracteres, compactados;
  public Compactador() { 
    positions = new HashMap<>(); 
    totalCaracteres = 0;
    compactados = 0;
  }
  public String add(String str) {
    if (str.equals("")) {
      return str;
    }
    String out;
    if (positions.containsKey(str)) {
      int pos = positions.get(str);
      out = Integer.toString(pos);
    } else {
      int pos = positions.size();
      positions.put(str, pos);
      out = str;
      compactados += str.length();
    }
    totalCaracteres += str.length();
    return out;
  }
  public double ratioCompactacion() {
    return (double)(totalCaracteres-compactados)/totalCaracteres;
  }
}
