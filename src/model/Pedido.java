package model;

public class Pedido {
    public int id;
    public String codigoProducto;
    public int cantidad;
    public String estado;

    public Pedido(int id, String codigoProducto, int cantidad, String estado) {
        this.id = id;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.estado = estado;
    }
    public String toString() {
        return "Pedido #" + id + " | Producto: " + codigoProducto + " x" + cantidad + " | Estado: " + estado;
    }
}