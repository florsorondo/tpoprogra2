package model;

public class Producto {
    public String codigo;
    public String nombre;
    public int stock;
    public String ubicacion;

    public Producto(String codigo, String nombre, int stock, String ubicacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.ubicacion = ubicacion;
    }

    public void aumentarStock(int cantidad) {
        stock += cantidad;
    }

    public void disminuirStock(int cantidad) {
        if (cantidad <= stock) {
            stock -= cantidad;
        } else {
            System.out.println("Error: stock insuficiente.");
        }
    }

    public String toString() {
        return codigo + " - " + nombre + " | Stock: " + stock + " | Ubicacion: " + ubicacion;
    }
}