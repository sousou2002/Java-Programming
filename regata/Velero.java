

public class Velero extends Barco {

    public Velero(String n, Posicion p, int r, int v) {
        super(n, p, validarR(r), v);
    }

    public void avanza(int mnt) {
        if (rumbo <= 45 || rumbo >= 315) {
            velocidad -= 3;
            posicion = posicion.posicionTrasRecorrer(mnt, rumbo, velocidad);
        } else if (rumbo <= 145 || rumbo >= 225) {
            velocidad += 3;
            posicion = posicion.posicionTrasRecorrer(mnt, rumbo, velocidad);
        } else {

            super.avanza(mnt);
        }
    }

}
