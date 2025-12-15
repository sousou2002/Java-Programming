public class PruebaBarco {
    public static void main(String[] args) {
        try {
            //Primero, probamos cosas que esperamos que produzcan excepciones
            try {
              Posicion p = new Posicion(-91, 0);
            } catch (Exception e) {
              System.out.println("Se genera correctamente una excepcion para una latitud fuera de rango");
            }
            try {
              Posicion p = new Posicion(91, 0);
            } catch (Exception e) {
              System.out.println("Se genera correctamente una excepcion para una latitud fuera de rango");
            }
            try {
              Posicion p = new Posicion(0, -1);
            } catch (Exception e) {
              System.out.println("Se genera correctamente una excepcion para una longitud fuera de rango");
            }
            try {
              Posicion p = new Posicion(0, 361);
            } catch (Exception e) {
              System.out.println("Se genera correctamente una excepcion para una longitud fuera de rango");
            }
            try {
              Barco b = new Barco("erroneo", new Posicion(0, 0), -1, 0);
            } catch (Exception e) {
              System.out.println("Se genera correctamente una excepcion para un rumbo fuera de rango");
            }
            try {
              Barco b = new Barco("erroneo", new Posicion(0, 0), 360, 0);
            } catch (Exception e) {
              System.out.println("Se genera correctamente una excepcion para un rumbo fuera de rango");
            }
            //Ahora, probamos cosas que esperamos que no produzcan excepciones
            Barco b = new Barco("gamonal", new Posicion(70, 45), 220, 30);
            Velero v = new Velero("gamonal", new Posicion(70, 45), 220, 30);
            System.out.println("Son Iguales? "+(b.equals(v)));
            System.out.println("Barco antes: "+b);
            System.out.println("Velero antes: "+v);
            b.avanza(60);
            v.avanza(60);
            System.out.println("Barco despues: "+b);
            System.out.println("Velero despues: "+v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
