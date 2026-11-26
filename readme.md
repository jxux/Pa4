# PA4 - Sistema de Gestión de Inventario Tecnológico (POO)

## Descripción del trabajo

Aplicación de consola desarrollada en **Java 21** con **Gradle** para la evaluación **PA4** del curso **Programación Orientada a Objetos (código 30626)**.

El sistema gestiona un inventario de productos tecnológicos (laptops y smartphones) mediante un menú interactivo que permite **agregar, editar, eliminar, listar, buscar y ordenar** productos. La solución aplica los contenidos del sílabo hasta la sesión 13: herencia, clase abstracta, interfaz, sobrescritura de métodos y colecciones genéricas (`HashSet` y `ArrayList`).

## Integrantes y porcentaje de participación

| # | Integrante | Responsabilidad principal | Participación |
| :---: | :--- | :--- | :---: |
| 1 | **Angie Damaris Evangelista Pardo** | Interfaz `Descontable` y clase abstracta `Producto` (`equals`, `hashCode`) | 100% |
| 2 | **Jesús Alexander García Flores** | Configuración inicial de Gradle y subclases `Laptop` y `Smartphone` | 100% |
| 3 | **Misael Yeremi Solis Sánchez** | `GestorInventario`: colección `HashSet` (registro, verificación, eliminación y listado) | 100% |
| 4 | **Dante Zosimo Alanya Molina** | `GestorInventario`: búsqueda por código y ordenamientos con `ArrayList` y `Comparator` | 100% |
| 5 | **Romel Gaspar Cayo** | Menú interactivo CRUD en `Main`, pruebas y documentación (`README.md`) | 100% |

## Diseño orientado a objetos adoptado

### Jerarquía de clases e interfaz

* **Interfaz `Descontable`:** define el contrato `calcularDescuento()` para asegurar que todo producto del inventario cuente con una política de descuento.
* **Clase abstracta `Producto`:** implementa `Descontable`, encapsula los atributos comunes (`codigo`, `nombre`, `precioBase`, `stock`) y declara el método abstracto `calcularPrecioFinal()`. Además, sobrescribe `equals()` y `hashCode()` en función del `codigo` (sin distinguir mayúsculas o minúsculas) para evitar registros duplicados dentro del `HashSet`.
* **Subclases `Laptop` y `Smartphone`:** heredan de `Producto` mediante `extends`, reutilizan el constructor padre con `super(...)` y sobrescriben (`@Override`) los métodos `calcularDescuento()`, `calcularPrecioFinal()` y `toString()`:
  * `Laptop`: aplica 10% de descuento si cuenta con 16 GB de RAM o más, y 5% en caso contrario.
  * `Smartphone`: aplica un descuento fijo de S/ 150.00 si cuenta con conectividad 5G, y S/ 80.00 si es 4G.

### Colecciones genéricas, búsqueda y ordenamiento

* **`GestorInventario`:** almacena los objetos en un `HashSet<Producto>` para garantizar la unicidad por código mediante `add()`, `contains()`, `remove()` y `size()`.
* **Búsqueda:** implementa `buscarPorCodigo(String codigo)` recorriendo la colección con `equalsIgnoreCase()`.
* **Ordenamiento:** convierte temporalmente el `HashSet<Producto>` a un `ArrayList<Producto>` para ordenar con `Comparator` por precio final ascendente (`listarOrdenadosPorPrecioFinal()`) y alfabéticamente por nombre (`listarOrdenadosPorNombre()`).
* **`Main`:** ofrece un menú interactivo por consola (con 4 productos precargados) para realizar operaciones CRUD (agregar, editar, eliminar, listar), búsquedas y reportes ordenados.

## Estructura del proyecto

```text
Pa4/
├── src/
│   └── main/
│       └── java/
│           └── pe/edu/isil/
│               ├── interfaces/
│               │   └── Descontable.java
│               ├── modelo/
│               │   ├── Producto.java
│               │   ├── Laptop.java
│               │   └── Smartphone.java
│               ├── servicio/
│               │   └── GestorInventario.java
│               └── Main.java
├── build.gradle
├── settings.gradle
└── README.md
```

## Instrucciones de ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/jxux/Pa4.git
   ```
2. Abrir la carpeta `Pa4` en **IntelliJ IDEA** y esperar a que Gradle sincronice el proyecto (requiere **JDK 21**).
3. Abrir el archivo `src/main/java/pe/edu/isil/Main.java`.
4. Hacer clic en el botón **Run (▶)** para iniciar el menú interactivo en consola.

## Video de exposición

* **Enlace en YouTube:** []