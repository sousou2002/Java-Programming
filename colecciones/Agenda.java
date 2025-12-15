
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Agenda<K, V> {

    private Map<K, Set<V>> elementos;

    public Agenda() {
        elementos = new TreeMap<>();
    }

    public void incluir(K elemento) {
        elementos.put(elemento, new TreeSet<>());
    }

    public void asociar(K elemento, V valor) throws Exception {
        if (elementos.containsKey(elemento)) {
            Set<V> valores = elementos.get(elemento);
            if (valores.contains(valor)) {
                throw new Exception("la agenda ya contiene ese valor para ese elemento.");
            } else {
                valores.add(valor);
            }
        } else {
            throw new Exception("ese elemento no se ha incluido previamente en la agenda.");
        }

    }

    public void eliminar(K elemento, V valor) throws Exception {
        if (elementos.containsKey(elemento)) {
            Set<V> valores = elementos.get(elemento);
            if (valores.contains(valor)) {
                valores.remove(valor);
            } else {
                throw new Exception("ese valor no existe para ese elemento.");
            }

        } else {
            throw new Exception("ese elemento no existe.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (K k : elementos.keySet()) {
            sb.append("- ");
            sb.append(k);
            sb.append(": ");
            Set<V> valores = elementos.get(k);
            boolean primero = true;
            for (V v : valores) {
                if (!primero) {
                    sb.append(",");
                }
                sb.append(v);
                primero = false;
            }
            sb.append("\n");
        }
        return sb.toString();

    }

}
