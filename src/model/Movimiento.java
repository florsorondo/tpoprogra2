package model;

public class Movimiento {
    public Producto producto;
    public int cantidad;
    public String tipo; 

    public Movimiento(Producto producto, int cantidad, String tipo) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    public void revertir() {
        // Revertir un movimiento significa hacer la operación contraria:
        // un INGRESO se deshace restando, y un EGRESO se deshace sumando
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