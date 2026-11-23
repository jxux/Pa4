package pe.edu.isil.modelo;

public class Smartphone extends Producto {
    private boolean tiene5G;

    public Smartphone(String codigo, String nombre, double precioBase, int stock, boolean tiene5G) {
        super(codigo, nombre, precioBase, stock);
        this.tiene5G = tiene5G;
    }

    public boolean isTiene5G() {
        return tiene5G;
    }

    public double calcularDescuento() {
        // Descuento fijo de S/ 150 si es 5G, o S/ 80 si no lo es
        return tiene5G ? 150.0 : 80.0;
    }

    public double calcularPrecioFinal() {
        return Math.max(0, getPrecioBase() - calcularDescuento());
    }

    public String toString() {
        return super.toString() + " | Tipo: Smartphone (" + (tiene5G ? "5G" : "4G") + ")";
    }

    public void setTiene5G(boolean tiene5G) {
        this.tiene5G = tiene5G;
    }
}
