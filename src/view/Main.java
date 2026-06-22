import java.util.Scanner;

public class Main {

    static DiccionarioProductos diccionario = new DiccionarioProductos(50);
    static ColaPrioridadStock stockCritico = new ColaPrioridadStock(50);
    static ColaPedidos colaPedidos = new ColaPedidos(20);
    static PilaMovimientos historial = new PilaMovimientos(50);
    static GrafoDeposito deposito = new GrafoDeposito(20);
    static Scanner sc = new Scanner(System.in);
    static int proximoIdPedido = 1;

    public static void main(String[] args) {
        cargarDatosPrueba();

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero();
            switch (opcion) {
                case 1: menuProductos(); break;
                case 2: menuStockCritico(); break;
                case 3: menuPedidos(); break;
                case 4: menuMovimientos(); break;
                case 5: menuDeposito(); break;
                case 0: System.out.println("\nCerrando LogiStock. Hasta luego."); break;
                default: System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    

    static void cargarDatosPrueba() {
        System.out.println("=== SISTEMA LOGISTOCK ===");
        System.out.println("Cargando datos iniciales...");

        Producto p1 = new Producto("P001", "Agua mineral",  3,  "Sector A1");
        Producto p2 = new Producto("P002", "Arroz 1kg",     25, "Sector B2");
        Producto p3 = new Producto("P003", "Fideos 500g",   8,  "Sector C1");
        Producto p4 = new Producto("P004", "Aceite 900ml",  2,  "Sector A2");
        Producto p5 = new Producto("P005", "Harina 1kg",    15, "Sector B1");

        diccionario.insertar(p1); diccionario.insertar(p2); diccionario.insertar(p3);
        diccionario.insertar(p4); diccionario.insertar(p5);

        stockCritico.insertar(p1); stockCritico.insertar(p2); stockCritico.insertar(p3);
        stockCritico.insertar(p4); stockCritico.insertar(p5);

        colaPedidos.encolar(new Pedido(proximoIdPedido++, "P002", 5,  "Listo para despacho"));
        colaPedidos.encolar(new Pedido(proximoIdPedido++, "P005", 10, "Listo para despacho"));

        String[] sectoresNombres = {"Entrada", "Sector A1", "Sector A2", "Sector B1", "Sector B2", "Sector C1", "Salida"};
        for (String s : sectoresNombres) deposito.agregarSector(s);

        String[][] listaCon = {
            {"Entrada",   "Sector A1"}, {"Entrada",   "Sector B1"},
            {"Sector A1", "Sector A2"}, {"Sector A1", "Sector B1"},
            {"Sector A2", "Sector C1"}, {"Sector B1", "Sector B2"},
            {"Sector B2", "Salida"},    {"Sector C1", "Salida"}
        };
        for (String[] con : listaCon) deposito.agregarConexion(con[0], con[1]);

        System.out.println("5 productos, 2 pedidos y 7 sectores cargados.\n");
    }

    
    static void mostrarMenuPrincipal() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║      SISTEMA LOGISTOCK       ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║  1. Gestion de Productos     ║");
        System.out.println("║  2. Stock Critico            ║");
        System.out.println("║  3. Gestion de Pedidos       ║");
        System.out.println("║  4. Movimientos de Stock     ║");
        System.out.println("║  5. Rutas del Deposito       ║");
        System.out.println("║  0. Salir                    ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("Seleccione: ");
    }

    

    static void menuProductos() {
        int op;
        do {
            System.out.println("\n--- Gestion de Productos ---");
            System.out.println("1. Registrar producto");
            System.out.println("2. Buscar por codigo");
            System.out.println("3. Listar todos");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            op = leerEntero();
            switch (op) {
                case 1: registrarProducto(); break;
                case 2: buscarProducto(); break;
                case 3:
                    System.out.println("\nProductos en el sistema:");
                    diccionario.mostrar();
                    break;
                case 0: break;
                default: System.out.println("Opcion invalida.");
            }
        } while (op != 0);
    }

    static void registrarProducto() {
        System.out.print("Codigo: ");
        String codigo = sc.nextLine().trim().toUpperCase();
        if (diccionario.buscar(codigo) != null) {
            System.out.println("Ya existe un producto con ese codigo.");
            return;
        }
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Stock inicial: ");
        int stock = leerEntero();
        System.out.print("Ubicacion en el deposito: ");
        String ubicacion = sc.nextLine().trim();

        Producto nuevo = new Producto(codigo, nombre, stock, ubicacion);
        diccionario.insertar(nuevo);
        rebuildStockCritico();
        System.out.println("Registrado: " + nuevo);
    }

    static void buscarProducto() {
        System.out.print("Codigo: ");
        String codigo = sc.nextLine().trim().toUpperCase();
        Producto p = diccionario.buscar(codigo);
        if (p != null) {
            System.out.println("Encontrado -> " + p);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    

    static void menuStockCritico() {
        int op;
        do {
            System.out.println("\n--- Control de Stock Critico ---");
            System.out.println("1. Ver productos ordenados por stock");
            System.out.println("2. Identificar producto mas critico");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            op = leerEntero();
            switch (op) {
                case 1:
                    rebuildStockCritico();
                    System.out.println("\nProductos (menor stock primero):");
                    stockCritico.mostrar();
                    break;
                case 2:
                    rebuildStockCritico();
                    Producto critico = stockCritico.eliminarPrioritario();
                    if (critico != null) {
                        System.out.println("Producto mas critico: " + critico);
                        System.out.println("-> Requiere reposicion urgente.");
                    }
                    break;
                case 0: break;
                default: System.out.println("Opcion invalida.");
            }
        } while (op != 0);
    }

    

    static void menuPedidos() {
        int op;
        do {
            System.out.println("\n--- Gestion de Pedidos ---");
            System.out.println("1. Agregar pedido");
            System.out.println("2. Despachar siguiente pedido");
            System.out.println("3. Ver cola de pedidos");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            op = leerEntero();
            switch (op) {
                case 1: agregarPedido(); break;
                case 2: despacharPedido(); break;
                case 3:
                    System.out.println("\nCola de pedidos:");
                    colaPedidos.mostrar();
                    break;
                case 0: break;
                default: System.out.println("Opcion invalida.");
            }
        } while (op != 0);
    }

    static void agregarPedido() {
        System.out.print("Codigo del producto: ");
        String codigo = sc.nextLine().trim().toUpperCase();
        if (diccionario.buscar(codigo) == null) {
            System.out.println("Producto no encontrado en el sistema.");
            return;
        }
        System.out.print("Cantidad solicitada: ");
        int cantidad = leerEntero();
        if (cantidad <= 0) {
            System.out.println("Cantidad invalida.");
            return;
        }
        Pedido pedido = new Pedido(proximoIdPedido++, codigo, cantidad, "Listo para despacho");
        colaPedidos.encolar(pedido);
        System.out.println("Pedido registrado: " + pedido);
    }

    static void despacharPedido() {
        Pedido pedido = colaPedidos.desencolar();
        if (pedido == null) return;

        System.out.println("\nDespachando: " + pedido);
        Producto p = diccionario.buscar(pedido.codigoProducto);
        if (p == null) {
            System.out.println("Advertencia: producto " + pedido.codigoProducto + " no encontrado.");
            return;
        }
        if (p.stock < pedido.cantidad) {
            System.out.println("Stock insuficiente. Disponible de " + p.nombre + ": " + p.stock + " unidades.");
            return;
        }
        Movimiento mov = new Movimiento(p, pedido.cantidad, "EGRESO");
        p.disminuirStock(pedido.cantidad);
        historial.apilar(mov);
        rebuildStockCritico();
        System.out.println("Despacho completado. Stock actualizado: " + p);
    }

    

    static void menuMovimientos() {
        int op;
        do {
            System.out.println("\n--- Movimientos de Stock ---");
            System.out.println("1. Registrar ingreso de mercaderia");
            System.out.println("2. Registrar egreso de mercaderia");
            System.out.println("3. Deshacer ultimo movimiento");
            System.out.println("4. Ver historial");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            op = leerEntero();
            switch (op) {
                case 1: registrarMovimiento("INGRESO"); break;
                case 2: registrarMovimiento("EGRESO"); break;
                case 3: deshacerMovimiento(); break;
                case 4: historial.mostrar(); break;
                case 0: break;
                default: System.out.println("Opcion invalida.");
            }
        } while (op != 0);
    }

    static void registrarMovimiento(String tipo) {
        System.out.print("Codigo del producto: ");
        String codigo = sc.nextLine().trim().toUpperCase();
        Producto p = diccionario.buscar(codigo);
        if (p == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        System.out.print("Cantidad: ");
        int cantidad = leerEntero();
        if (cantidad <= 0) {
            System.out.println("Cantidad invalida.");
            return;
        }
        if (tipo.equals("EGRESO") && p.stock < cantidad) {
            System.out.println("Stock insuficiente. Disponible: " + p.stock);
            return;
        }

        Movimiento mov = new Movimiento(p, cantidad, tipo);
        if (tipo.equals("INGRESO")) {
            p.aumentarStock(cantidad);
        } else {
            p.disminuirStock(cantidad);
        }
        historial.apilar(mov);
        rebuildStockCritico();
        System.out.println(tipo + " registrado. " + p);
    }

    static void deshacerMovimiento() {
        if (historial.estaVacia()) {
            System.out.println("No hay movimientos para deshacer.");
            return;
        }
        Movimiento ultimo = historial.desapilar();
        System.out.println("Deshaciendo: " + ultimo);
        ultimo.revertir();
        rebuildStockCritico();
        System.out.println("Revertido. Stock actual: " + ultimo.producto);
    }

    

    static void menuDeposito() {
        int op;
        do {
            System.out.println("\n--- Rutas del Deposito ---");
            System.out.println("1. Agregar sector");
            System.out.println("2. Agregar conexion entre sectores");
            System.out.println("3. Calcular ruta entre sectores");
            System.out.println("4. Ver mapa del deposito");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");
            op = leerEntero();
            switch (op) {
                case 1:
                    System.out.print("Nombre del sector: ");
                    String sector = sc.nextLine().trim();
                    if (deposito.agregarSector(sector)) {
                        System.out.println("Sector '" + sector + "' agregado.");
                    } else {
                        System.out.println("No se pudo agregar (ya existe o deposito lleno).");
                    }
                    break;
                case 2:
                    System.out.print("Sector 1: ");
                    String s1 = sc.nextLine().trim();
                    System.out.print("Sector 2: ");
                    String s2 = sc.nextLine().trim();
                    if (deposito.agregarConexion(s1, s2)) {
                        System.out.println("Conexion agregada entre '" + s1 + "' y '" + s2 + "'.");
                    } else {
                        System.out.println("No se pudo conectar. Verificar que ambos sectores existen.");
                    }
                    break;
                case 3:
                    System.out.print("Sector origen: ");
                    String origen = sc.nextLine().trim();
                    System.out.print("Sector destino: ");
                    String destino = sc.nextLine().trim();
                    System.out.println("Ruta: " + deposito.calcularRuta(origen, destino));
                    break;
                case 4:
                    System.out.println("\nMapa del deposito:");
                    deposito.mostrar();
                    break;
                case 0: break;
                default: System.out.println("Opcion invalida.");
            }
        } while (op != 0);
    }

    

    static void rebuildStockCritico() {
        stockCritico = new ColaPrioridadStock(50);
        for (Producto p : diccionario.obtenerTodos()) {
            stockCritico.insertar(p);
        }
    }

    static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero valido: ");
            }
        }
    }
}