public class Pedido {
    int id;
    String codigoProducto;
    int cantidad;
    String estado;

    public Pedido(int id, String codigoProducto, int cantidad, String estado) {
        this.id = id;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " | Producto: " + codigoProducto + " x" + cantidad + " | Estado: " + estado;
    }
}