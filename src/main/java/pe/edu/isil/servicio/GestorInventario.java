package pe.edu.isil.servicio;

import pe.edu.isil.modelo.Producto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GestorInventario {
    // Colección genérica HashSet para evitar productos con el mismo código
    private Set<Producto> productos;

    public GestorInventario() {
        this.productos = new HashSet<>();
    }

    // Operación de registro usando HashSet.add()
    public boolean registrarProducto(Producto producto) {
        return productos.add(producto);
    }

    // Operación de verificación usando HashSet.contains()
    public boolean existeProducto(Producto producto) {
        return productos.contains(producto);
    }

    // Operación de eliminación por código usando HashSet.remove()
    public boolean eliminarPorCodigo(String codigo) {
        Producto encontrado = buscarPorCodigo(codigo);
        if (encontrado != null) {
            return productos.remove(encontrado);
        }
        return false;
    }

    // Operación de recorrido
    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        for (Producto p : productos) {
            System.out.println(" - " + p);
        }
    }

    // Búsqueda por Código
    public Producto buscarPorCodigo(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    // Ordenamiento por Precio final Ascendente (convierte HashSet a ArrayList)
    public List<Producto> listarOrdenadosPorPrecioFinal() {
        List<Producto> listaOrdenada = new ArrayList<>(productos);
        listaOrdenada.sort(Comparator.comparingDouble(Producto::calcularPrecioFinal));
        return listaOrdenada;
    }

    // Ordenamiento Alfabético por Nombre
    public List<Producto> listarOrdenadosPorNombre() {
        List<Producto> listaOrdenada = new ArrayList<>(productos);
        listaOrdenada.sort(Comparator.comparing(Producto::getNombre, String.CASE_INSENSITIVE_ORDER));
        return listaOrdenada;
    }

    public int cantidadTotal() {
        return productos.size();
    }
}