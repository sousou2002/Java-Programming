public class PruebaAgenda {
  public static void prueba1() {
    try {

      Agenda<Restaurante, String> a = new Agenda<>();
      Restaurante r1 = new Restaurante("123456",2013);
      Restaurante r2 = new Restaurante("123457",2020);
      Restaurante r3 = new Restaurante("123458",1995);
      Restaurante r4 = new Restaurante("123456",2005);
      a.incluir(r1);
      a.incluir(r2);
      a.incluir(r3);

      try {
        a.asociar(r4, "pescado");
        System.out.println("No se ha lanzado una excepcion al intentar asociar un pedido r4, que todavia no ha sido incluido en la agenda!!!");
      } catch (Exception e) {
      }
      try {
        a.eliminar(r4, "pescado");
        System.out.println("No se ha lanzado una excepcion al intentar eliminar un pedido de r4, que todavia no ha sido incluido en la agenda!!!");
      } catch (Exception e) {
      }

      a.incluir(r4);

      try {
        a.eliminar(r4, "pescado");
        System.out.println("No se ha lanzado una excepcion al intentar eliminar un pedido no existente!!!");
      } catch (Exception e) {
      }

      a.asociar(r1, "pescado");
      a.asociar(r1, "arroz");
      a.asociar(r1, "carne");
      try {
        a.asociar(r1, "arroz");
        System.out.println("No se ha lanzado una excepcion al intentar asociar un pedido ya existente!!!");
      } catch (Exception e) {
      }
      a.eliminar(r1, "pescado");
      try {
        a.eliminar(r1, "pescado");
        System.out.println("No se ha lanzado una excepcion al intentar eliminar un pedido ya eliminado!!!");
      } catch (Exception e) {
      }

      a.asociar(r2, "fideos");
      a.asociar(r2, "sopa");
      a.asociar(r2, "ensalada");

      a.asociar(r3, "tarta");
      a.asociar(r3, "helado");
      a.asociar(r3, "flan");

      System.out.println("Contenido de la Agenda:\n"+a);

    } catch (Exception e) {
      System.err.println("Excepcion no prevista!!!!!");
      e.printStackTrace();
    }
  }

  public static void prueba2() {
    try {

      AgendaLista<Restaurante, String> a = new AgendaLista<>();
      Restaurante r1 = new Restaurante("123456",2013);
      Restaurante r2 = new Restaurante("123457",2020);
      Restaurante r3 = new Restaurante("123458",1995);
      Restaurante r4 = new Restaurante("123456",2005);
      a.incluir(r1);
      a.incluir(r2);
      a.incluir(r3);

      try {
        a.asociar(r4, "pescado");
        System.out.println("No se ha lanzado una excepcion al intentar asociar un pedido r4, que todavia no ha sido incluido en la agenda!!!");
      } catch (Exception e) {
      }
      try {
        a.eliminar(r4, "pescado");
        System.out.println("No se ha lanzado una excepcion al intentar eliminar un pedido de r4, que todavia no ha sido incluido en la agenda!!!");
      } catch (Exception e) {
      }

      a.incluir(r4);

      try {
        a.eliminar(r4, "pescado");
        System.out.println("No se ha lanzado una excepcion al intentar eliminar un pedido no existente!!!");
      } catch (Exception e) {
      }

      a.asociar(r1, "pescado");
      a.asociar(r1, "arroz");
      a.asociar(r1, "carne");
      try {
        a.asociar(r1, "arroz");
        System.out.println("No se ha lanzado una excepcion al intentar asociar un pedido ya existente!!!");
      } catch (Exception e) {
      }
      a.eliminar(r1, "pescado");
      try {
        a.eliminar(r1, "pescado");
        System.out.println("No se ha lanzado una excepcion al intentar eliminar un pedido ya eliminado!!!");
      } catch (Exception e) {
      }

      a.asociar(r2, "fideos");
      a.asociar(r2, "sopa");
      a.asociar(r2, "ensalada");

      a.asociar(r3, "tarta");
      a.asociar(r3, "helado");
      a.asociar(r3, "flan");

      System.out.println("Contenido de la Agenda:\n"+a);

    } catch (Exception e) {
      System.err.println("Excepcion no prevista!!!!!");
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    prueba1();
    prueba2();
  }
}
