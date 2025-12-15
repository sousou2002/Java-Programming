import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Frecuencia {

    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>(); // new HashMap<>();
        Scanner sc = new Scanner(System.in);
        String linea = sc.nextLine();

        String[] words = linea.split(" ");

        for (String word : words) {
            word = word.toLowerCase();
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }
        System.out.println("Resultado: " + map);
        sc.close();
    }
}
