public class Main {
    public static void main(String[] args) {

        System.out.println("=== SISTEMA LOGISTOCK ===");

        Producto p1 = new Producto("P001", "Agua", 5, "A1");
        Producto p2 = new Producto("P002", "Arroz", 20, "B2");
        Producto p3 = new Producto("P003", "Fideos", 3, "C1");

        DiccionarioProductos diccionario = new DiccionarioProductos(10);

        diccionario.insertar(p1);
        diccionario.insertar(p2);
        diccionario.insertar(p3);

        System.out.println("\nProductos registrados:");
        diccionario.mostrar();

        System.out.println("\nBusqueda por codigo P001:");
        Producto buscado = diccionario.buscar("P001");
        System.out.println(buscado);

        ColaPrioridadStock stockCritico = new ColaPrioridadStock(10);
        stockCritico.insertar(p1);
        stockCritico.insertar(p2);
        stockCritico.insertar(p3);

        System.out.println("\nProductos ordenados por stock critico:");
        stockCritico.mostrar();

        ColaPedidos pedidos = new ColaPedidos(5);

        pedidos.encolar(new Pedido(101, "Listo para despacho"));
        pedidos.encolar(new Pedido(102, "Listo para despacho"));
        pedidos.encolar(new Pedido(103, "Listo para despacho"));

        System.out.println("\nPedidos en cola:");
        pedidos.mostrar();

        System.out.println("\nSe despacha:");
        System.out.println(pedidos.desencolar());

        PilaMovimientos historial = new PilaMovimientos(10);

        Movimiento mov1 = new Movimiento(p1, 2, "EGRESO");
        p1.disminuirStock(2);
        historial.apilar(mov1);

        System.out.println("\nStock despues del movimiento:");
        System.out.println(p1);

        Movimiento ultimo = historial.desapilar();
        ultimo.revertir();

        System.out.println("\nStock despues de deshacer:");
        System.out.println(p1);
    }
}