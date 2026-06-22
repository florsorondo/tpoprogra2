public class Movimiento {
    Producto producto;
    int cantidad;
    String tipo; 

    public Movimiento(Producto producto, int cantidad, String tipo) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public void revertir() {
        if (tipo.equals("INGRESO")) {
            producto.disminuirStock(cantidad);
        } else if (tipo.equals("EGRESO")) {
            producto.aumentarStock(cantidad);
        }
    }

    public String toString() {
        return tipo + " de " + cantidad + " unidades de " + producto.nombre;
    }
}