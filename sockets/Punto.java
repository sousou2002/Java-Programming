
import java.util.Objects;

public class Punto {

    public int x;
    public int y;

    public Punto(int laX, int laY) {
        x = laX;
        y = laY;
    }

    @Override
    public String toString() {
        return "Punto(x=" + this.x + ", y="
                + this.y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Punto)
                && ((Punto) obj).x == this.x
                && ((Punto) obj).y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);

    }

    public static void main(String[] args) {

        Punto p1 = new Punto(20, 10);
        Punto p2 = new Punto(28, 12);

        if (!p1.equals(p2)) {
            System.out.println("No son iguales.");
        }
        System.out.println(p1.toString());
        System.out.println(p2.toString());

        System.out.println("Num: " + p2.hashCode());
        System.out.println("Num: " + p1.hashCode());

    }
}
