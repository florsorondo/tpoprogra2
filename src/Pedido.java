public class Pedido {
    int id;
    String estado;

    public Pedido(int id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public String toString() {
        return "Pedido #" + id + " | Estado: " + estado;
    }
}