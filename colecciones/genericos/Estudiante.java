import java.util.Objects;

public class Estudiante implements Comparable<Estudiante> {

  private String dni, nombre;
  private double nota;

  public Estudiante(String dni, String nombre, double nota) {
    this.dni = dni;
    this.nombre = nombre;
    this.nota = nota;
  }
  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public String getDni() {
    return dni;
  }
  public void setDni(String dni) {
    this.dni = dni;
  }
  public double getNota() {
    return nota;
  }
  public void setNota(double nota) {
    this.nota = nota;
  }

  @Override
  public String toString() {
    return "Estudiante [dni=" + dni + ", nombre=" + nombre + ", nota=" + nota + "]";
    //return "("+dni+", "+nombre+", "+nota+")";
  }

  @Override
  public int compareTo(Estudiante o) {
    int orden = dni.compareToIgnoreCase(o.dni);
    if (orden==0) {
      orden = nombre.compareToIgnoreCase(o.nombre);
    }
    return orden;
  }

  @Override
  public int hashCode() {
    return Objects.hash(dni.toUpperCase(), nombre.toUpperCase());
  }
  
  @Override
  public boolean equals(Object obj) {
    return (obj instanceof Estudiante) && compareTo((Estudiante)obj)==0;
  }  

  public static void probar(Estudiante e1, Estudiante e2) {
    int orden = e1.compareTo(e2);
    String resultado;
    if (orden==0) {
      resultado = "igual";
    } else if (orden<0) {
      resultado = "menor";
    } else {
      resultado = "mayor";
    }
    System.out.println("El "+e1+" es "+resultado+" que el "+e2);
  }

  public static void main(String[] args) {
    {
      Estudiante e1 = new Estudiante("2234D", "Antonio", 8);
      Estudiante e2 = new Estudiante("2234d", "Antonio", 7);
      probar(e1, e2);
    }
    {
      Estudiante e1 = new Estudiante("3624E", "Luisa", 8);
      Estudiante e2 = new Estudiante("3624H", "Ana", 7);
      probar(e1, e2);
    }
    {
      Estudiante e1 = new Estudiante("2234H", "Antonio", 8);
      Estudiante e2 = new Estudiante("2234D", "Arturo", 7);
      probar(e1, e2);
    }
  }
}