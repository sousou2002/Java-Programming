# Java Examples Collection

Repositorio con una colección de ejemplos y ejercicios en Java organizados por carpetas (regata, io, sockets, StudentApplication, ...).

Compilar todo (ejemplo genérico):

```bash
# desde la raíz del proyecto
javac -d out $(find . -name "*.java")
```

En Windows PowerShell:

```powershell
Get-ChildItem -Recurse -Filter *.java | ForEach-Object { javac -d out $_.FullName }
```

Ejecutar (ejemplo para una clase con método `main`):

```bash
# asumiendo que compilaste en 'out' y la clase es regata.PruebaBarco
java -cp out regata.PruebaBarco
```

Proyecto Maven (subcarpeta `StudentApplication`):

```bash
cd StudentApplication
mvn package
```

Contribuciones: abre un issue o PR.
