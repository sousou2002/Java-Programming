
public class Barco {

    protected String nombre;
    protected Posicion posicion;
    protected int rumbo;
    protected int velocidad;

    public Barco(String n, Posicion p, int r, int v) {
        nombre = n;
        posicion = p;
        rumbo = validarR(r);
        velocidad = v;
    }

    public static int validarR(int r) {
        if (r < 0 || r >= 360) {
            throw new IllegalArgumentException();
        }
        return r;
    }

    public String getNombre() {
        return nombre;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getRumbo() {
        return rumbo;
    }

    public boolean equals(Object o) {
        return (o instanceof Barco) && nombre.equalsIgnoreCase(((Barco) o).nombre);
    }

    public int hashCode() {
        return nombre.toUpperCase().hashCode();
    }

    public void avanza(int mnt) {
        posicion = posicion.posicionTrasRecorrer(mnt, rumbo, velocidad);
    }

    @Override
    public String toString() {
        return nombre + ": l=" + posicion.getLatitud() + " L= " + posicion.getLongitud() + " R= " + rumbo + " V= " + velocidad;
    }

}
