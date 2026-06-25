# LogiStock - Sistema de Gestión Logística
## Integrantes
•⁠  ⁠Florencia Sorondo Gardini
•⁠  ⁠Joaquin Leon De La Barra
•⁠  ⁠Bautista Fuster

## Alternativa elegida
Alternativa C: Centro Logístico de Distribución Avanzada

## Descripción del Proyecto
Sistema de gestión para un centro logístico que permite administrar el almacenamiento de productos, controlar el stock crítico, gestionar pedidos de expedición, registrar movimientos de inventario y calcular rutas óptimas dentro del depósito.

## Estructuras de Datos Utilizadas

⁠Diccionario (⁠ DiccionarioProductos ⁠): lo usamos para guardar y localizar productos por código. La idea es poder encontrar cualquier producto de forma directa, sin tener que recorrer toda la lista uno por uno.

Cola con prioridad (⁠ ColaPrioridadStock ⁠): nos permite tener siempre visible qué productos están más cerca de quedarse sin stock. Los que menos unidades tienen aparecen primero, así es fácil saber cuáles reponer urgente.

Cola FIFO (⁠ ColaPedidos ⁠): modela la línea de expedición. Los pedidos se despachan en el mismo orden en que llegan, tal como funciona una fila real en el depósito.

Pila (⁠ PilaMovimientos ⁠): registra cada movimiento de mercadería (ingresos y egresos). Al ser LIFO, permite deshacer el último movimiento si se cometió un error, recuperando el estado anterior del stock.

Grafo (⁠ GrafoDeposito ⁠): representa el depósito como una red de sectores conectados por pasillos. Sobre esta estructura aplicamos BFS para calcular la ruta más corta entre dos puntos del depósito.

## Estructura del Proyecto
El código está organizado en tres paquetes dentro de src/, según la responsabilidad de cada clase:

model: clases de datos simples — Producto, Pedido y Movimiento. No contienen lógica de estructuras de datos, solo representan la información.
tda: las cinco estructuras de datos propias del trabajo — DiccionarioProductos, ColaPrioridadStock, ColaPedidos, PilaMovimientos y GrafoDeposito. Todas implementadas con arreglos, sin usar colecciones nativas de Java.
view: contiene Main, que maneja el menú por consola y coordina la interacción entre los demás módulos

## Compilación y ejecución
Para correr el programa hay que abrir una terminal, ubicarse dentro de la carpeta src del proyecto, y ejecutar estos dos comandos:

```javac model/*.java tda/*.java view/*.java```
```java view.Main```
El primer comando (javac) compila todas las clases del proyecto. El segundo (java view.Main) ejecuta el programa, arrancando desde la clase Main, que es la que muestra el menú principal por consola.

Datos de prueba cargados
Productos
P001 - Agua mineral, stock 3, ubicado en Sector A1
P002 - Arroz 1kg, stock 25, ubicado en Sector B2
P003 - Fideos 500g, stock 8, ubicado en Sector C1
P004 - Aceite 900ml, stock 2, ubicado en Sector A2
P005 - Harina 1kg, stock 15, ubicado en Sector B1

Pedidos en cola
Pedido #1: P002 (Arroz 1kg) x5, estado "Listo para despacho"
Pedido #2: P005 (Harina 1kg) x10, estado "Listo para despacho"

Sectores del depósito
Entrada, Sector A1, Sector A2, Sector B1, Sector B2, Sector C1, Salida

Conexiones entre sectores
Entrada con Sector A1
Entrada con Sector B1
Sector A1 con Sector A2
Sector A1 con Sector B1
Sector A2 con Sector C1
Sector B1 con Sector B2
Sector B2 con Salida
Sector C1 con Salida 


## Funcionalidades Implementadas en la Segunda Etapa
1.⁠ ⁠Gestión de Productos: registro de nuevos productos, búsqueda por código y listado completo del inventario.
2.⁠ ⁠Control de Stock Crítico: visualización de productos ordenados por nivel de stock e identificación del producto que requiere reposición urgente.
3.⁠ ⁠Gestión de Pedidos: alta de pedidos con producto y cantidad, despacho FIFO con actualización automática de stock, y visualización de la cola.
4.⁠ Movimientos de Stock: registro de ingresos y egresos de mercadería con actualización de stock, y reversión del último movimiento registrado (deshacer).
5.⁠ ⁠Rutas del Depósito: modelado del depósito como grafo no dirigido, con alta de sectores, conexión entre pasillos y cálculo de la ruta más corta entre dos puntos usando BFS.

## Link del Repositorio
[https://github.com/florsorondo/tpoprogra2]

## Actividades Realizadas por Integrante
El desarrollo fue colaborativo. Lo siguiente refleja la responsabilidad principal de cada integrante.
- Florencia Sorondo Gardini: README.md, estructuras base iniciales (Producto.java, DiccionarioProductos.java, ColaPedidos.java, ColaPrioridadStock.java, Movimiento.java), Pedido.java, PilaMovimientos.java
- Joaquin Leon de la Barra: GrafoDeposito.java (modelado del depósito y BFS) y ajustes de integración
- Bautista Fuster: Main.java (menú de prueba e integración de módulos)

