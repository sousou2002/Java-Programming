import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AgendaLista<K, V> {
  private Map<K, List<V>> elementos;

  public AgendaLista() {
    elementos = new TreeMap<>();
  }

  public void incluir(K elem) {
    elementos.put(elem, new ArrayList<>());
  }

  public void asociar(K elem, V val) throws Exception {
    if (elementos.containsKey(elem)) {
      List<V> vals = elementos.get(elem);
      if (vals.contains(val)) {
        throw new Exception("La agenda ya contiene ese subelemento valor para ese elemento clave");
      } else {
        vals.add(val);
      }
    } else {
      throw new Exception("Ese elemento clave no se ha incluido previamente en la agenda");
    }
  }

  public void eliminar(K elem, V subelemento) throws Exception {
    if (elementos.containsKey(elem)) {
      List<V> vals = elementos.get(elem);
      if (vals.contains(subelemento)) {
        vals.remove(subelemento);
      } else {
        throw new Exception("La agenda no contiene ese subelemento valor para ese elemento clave");
      }
    } else {
      throw new Exception("Ese elemento clave no se ha incluido previamente en la agenda");
    }
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    for (K k: elementos.keySet()) {
      sb.append("- ");
      sb.append(k);
      sb.append(": ");
      List<V> vs = elementos.get(k);
      boolean primero = true;
      for (V v: vs) {
        if (!primero) {
          sb.append(", ");
        }
        sb.append(v);
        primero = false;
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
