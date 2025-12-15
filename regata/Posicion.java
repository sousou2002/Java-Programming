

public class Posicion {

    private double longitud;
    private double latitud;

    public Posicion(double longitud, double latitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    @Override
    public String toString() {
        return "\"l = " + this.getLatitud() + " L = " + this.getLongitud() + " \"";
    }

    public Posicion posicionTrasRecorrer(int minutos, int rumbo, int velocidad) {
        double angulo = Math.toRadians((360 + 90 - rumbo) % 360);
        double velocidadKmH = velocidad / 1.60934;
        double deltaLongitud = velocidadKmH * Math.cos(angulo) * Math.cos(Math.toRadians(this.latitud)) * minutos / 60.0;
        double deltaLatitud = velocidadKmH * Math.sin(angulo) * minutos / 60.0;
        double nuevaLongitud = this.longitud + deltaLongitud;
        double nuevaLatitud = this.latitud + deltaLatitud;
        return new Posicion(nuevaLongitud, nuevaLatitud);
        }
}
