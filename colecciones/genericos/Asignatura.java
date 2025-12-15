import java.util.ArrayList;
import java.util.List;

public class Asignatura {

  private List<Estudiante> estudiantes;
  public Asignatura() {
    estudiantes = new ArrayList<>();
  }
  
  public void almacenarEstudiante(Estudiante e) {
    int idx = estudiantes.indexOf(e);
    if (idx<0) {
      estudiantes.add(e);
    } else {
      estudiantes.set(idx, e);
    }
  }

  @Override
  public String toString() {
    //return "Asignatura [estudiantes=" + estudiantes + "]";
    StringBuilder b = new StringBuilder();
    b.append("{");
    boolean primero = true;
    for (Estudiante e: estudiantes) {
      if (primero)
        primero = false;
      else
        b.append(" - ");
      b.append(e);
    }
    b.append("}");
    return b.toString();
  }

  public static void main(String[] args) {
    Asignatura a = new Asignatura();
    a.almacenarEstudiante(new Estudiante("3245D", "Antonio", 8));
    a.almacenarEstudiante(new Estudiante("4536X", "Ana", 6));
    a.almacenarEstudiante(new Estudiante("8342P", "Luis", 4));
    a.almacenarEstudiante(new Estudiante("4535H", "Carmen", 9));
    System.out.println(a);
    a.almacenarEstudiante(new Estudiante("3245D", "Antonio", 5));
    System.out.println(a);
  }
}