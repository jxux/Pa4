package pe.edu.isil;

import pe.edu.isil.modelo.Laptop;
import pe.edu.isil.modelo.Producto;
import pe.edu.isil.modelo.Smartphone;
import pe.edu.isil.servicio.GestorInventario;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final GestorInventario gestor = new GestorInventario();

    public static void main(String[] args) {
        cargarDatosIniciales();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1 -> agregarProducto();
                case 2 -> editarProducto();
                case 3 -> eliminarProducto();
                case 4 -> listarProductos();
                case 5 -> buscarProducto();
                case 6 -> listarOrdenadosPorPrecio();
                case 7 -> listarOrdenadosPorNombre();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion no valida. Intente nuevamente.");
            }
        } while (opcion != 0);

        sc.close();
    }

    private static void cargarDatosIniciales() {
        gestor.registrarProducto(new Laptop("LAP-001", "Lenovo ThinkPad", 4200.00, 10, 16));
        gestor.registrarProducto(new Laptop("LAP-002", "HP Pavilion", 2800.00, 5, 8));
        gestor.registrarProducto(new Smartphone("CEL-001", "Xiaomi 14T Pro", 2600.00, 15, true));
        gestor.registrarProducto(new Smartphone("CEL-002", "Samsung Galaxy A35", 1300.00, 20, false));
    }

    private static void mostrarMenu() {
        System.out.println("\n********* GESTION DE INVENTARIO TECNOLOGICO **********");
        System.out.println("1. Agregar producto");
        System.out.println("2. Editar producto");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Listar productos");
        System.out.println("5. Buscar producto por codigo");
        System.out.println("6. Listar ordenados por precio final");
        System.out.println("7. Listar ordenados por nombre");
        System.out.println("0. Salir");
        System.out.println("*******************************************************");
    }

    private static void agregarProducto() {
        System.out.println("\n--- AGREGAR PRODUCTO ---");
        System.out.println("Tipo de producto: [1] Laptop  [2] Smartphone");
        int tipo = leerEntero("Elija el tipo: ");

        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo invalido.");
            return;
        }

        String codigo = leerTexto("Codigo (ej. LAP-003): ");
        String nombre = leerTexto("Nombre: ");
        double precioBase = leerDouble("Precio base (S/): ");
        int stock = leerEntero("Stock: ");

        Producto nuevo;
        if (tipo == 1) {
            int ram = leerEntero("Memoria RAM (GB): ");
            nuevo = new Laptop(codigo, nombre, precioBase, stock, ram);
        } else {
            String resp5G = leerTexto("¿Tiene 5G? (s/n): ");
            boolean tiene5G = resp5G.equalsIgnoreCase("s");
            nuevo = new Smartphone(codigo, nombre, precioBase, stock, tiene5G);
        }

        if (gestor.registrarProducto(nuevo)) {
            System.out.println("Producto registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar: ya existe un producto con el codigo " + codigo + ".");
        }
    }

    private static void editarProducto() {
        System.out.println("\n--- EDITAR PRODUCTO ---");
        String codigo = leerTexto("Ingrese el codigo del producto a editar: ");
        Producto p = gestor.buscarPorCodigo(codigo);

        if (p == null) {
            System.out.println("No se encontro ningún producto con el codigo " + codigo + ".");
            return;
        }

        System.out.println("Producto actual: " + p);
        System.out.println("(Presione Enter sin escribir nada para mantener el valor actual)");

        System.out.print("Nuevo nombre [" + p.getNombre() + "]: ");
        String nuevoNombre = sc.nextLine().trim();
        if (!nuevoNombre.isEmpty()) {
            p.setNombre(nuevoNombre);
        }

        System.out.print("Nuevo precio base [" + p.getPrecioBase() + "]: ");
        String nuevoPrecioStr = sc.nextLine().trim();
        if (!nuevoPrecioStr.isEmpty()) {
            try {
                p.setPrecioBase(Double.parseDouble(nuevoPrecioStr));
            } catch (NumberFormatException e) {
                System.out.println("Precio invalido, se mantuvo el anterior.");
            }
        }

        System.out.print("Nuevo stock [" + p.getStock() + "]: ");
        String nuevoStockStr = sc.nextLine().trim();
        if (!nuevoStockStr.isEmpty()) {
            try {
                p.setStock(Integer.parseInt(nuevoStockStr));
            } catch (NumberFormatException e) {
                System.out.println("Stock invalido, se mantuvo el anterior.");
            }
        }

        if (p instanceof Laptop laptop) {
            System.out.print("Nueva RAM en GB [" + laptop.getMemoriaRamGB() + "]: ");
            String nuevaRamStr = sc.nextLine().trim();
            if (!nuevaRamStr.isEmpty()) {
                try {
                    laptop.setMemoriaRamGB(Integer.parseInt(nuevaRamStr));
                } catch (NumberFormatException e) {
                    System.out.println("RAM invalida, se mantuvo la anterior.");
                }
            }
        } else if (p instanceof Smartphone phone) {
            System.out.print("¿Tiene 5G? (s/n) [" + (phone.isTiene5G() ? "s" : "n") + "]: ");
            String nuevo5G = sc.nextLine().trim();
            if (!nuevo5G.isEmpty()) {
                phone.setTiene5G(nuevo5G.equalsIgnoreCase("s"));
            }
        }

        System.out.println("Producto actualizado: " + p);
    }

    private static void eliminarProducto() {
        System.out.println("\n--- ELIMINAR PRODUCTO ---");
        String codigo = leerTexto("Ingrese el codigo del producto a eliminar: ");
        if (gestor.eliminarPorCodigo(codigo)) {
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("No se encontro el codigo " + codigo + ".");
        }
    }

    private static void listarProductos() {
        System.out.println("\n--- LISTA DE PRODUCTOS (" + gestor.cantidadTotal() + ") ---");
        gestor.listarProductos();
    }

    private static void buscarProducto() {
        System.out.println("\n--- BUSCAR PRODUCTO ---");
        String codigo = leerTexto("Ingrese el codigo a buscar: ");
        Producto encontrado = gestor.buscarPorCodigo(codigo);
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado);
        } else {
            System.out.println("No se encontro el codigo " + codigo + ".");
        }
    }

    private static void listarOrdenadosPorPrecio() {
        System.out.println("\n--- ORDENADOS POR PRECIO FINAL (MENOR A MAYOR) ---");
        List<Producto> lista = gestor.listarOrdenadosPorPrecioFinal();
        for (Producto p : lista) {
            System.out.println(" - " + p);
        }
    }

    private static void listarOrdenadosPorNombre() {
        System.out.println("\n--- ORDENADOS ALFABETICAMENTE POR NOMBRE ---");
        List<Producto> lista = gestor.listarOrdenadosPorNombre();
        for (Producto p : lista) {
            System.out.println(" - " + p);
        }
    }

    // Métodos auxiliares para evitar errores de lectura con Scanner
    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero valido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número decimal valido.");
            }
        }
    }
}