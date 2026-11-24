package pe.edu.isil.modelo;

import pe.edu.isil.interfaces.Descontable;
import java.util.Objects;

public abstract class Producto implements Descontable {
    private String codigo;
    private String nombre;
    private double precioBase;
    private int stock;

    public Producto(String codigo, String nombre, double precioBase, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Método abstracto que cada subclase debe sobrescribir
    public abstract double calcularPrecioFinal();

    // Sobrescritura de equals y hashCode para que HashSet evite códigos duplicados
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return Objects.equals(codigo.toUpperCase(), producto.codigo.toUpperCase());
    }

    public int hashCode() {
        return Objects.hash(codigo.toUpperCase());
    }

    public String toString() {
        return String.format("[%s] %s | Base: S/ %.2f | Final: S/ %.2f | Stock: %d",
                codigo, nombre, precioBase, calcularPrecioFinal(), stock);
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }
}
