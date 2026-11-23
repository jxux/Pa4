package pe.edu.isil.modelo;

public class Laptop extends Producto {
    private int memoriaRamGB;

    public Laptop(String codigo, String nombre, double precioBase, int stock, int memoriaRamGB) {
        super(codigo, nombre, precioBase, stock);
        this.memoriaRamGB = memoriaRamGB;
    }

    public int getMemoriaRamGB() {
        return memoriaRamGB;
    }

    public double calcularDescuento() {
        // 10% de descuento si tiene 16GB de RAM o más; 5% en caso contrario
        return memoriaRamGB >= 16 ? getPrecioBase() * 0.10 : getPrecioBase() * 0.05;
    }
    public double calcularPrecioFinal() {
        return getPrecioBase() - calcularDescuento();
    }

    public String toString() {
        return super.toString() + " | Tipo: Laptop (" + memoriaRamGB + "GB RAM)";
    }

    public void setMemoriaRamGB(int memoriaRamGB) {
        this.memoriaRamGB = memoriaRamGB;
    }
}
