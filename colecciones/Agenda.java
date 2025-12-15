
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
    public void asociar(K elemento, V valor) throws Exception{
        if(elementos.containsKey(elemento)){
            Set<V> valores = elementos.get(elemento);
            if(valores.contains(valor)){
                
            }
        }

    }

}
