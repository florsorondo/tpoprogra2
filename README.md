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

## Funcionalidades Implementadas en la Segunda Etapa
1.⁠ ⁠Gestión de Productos: registro de nuevos productos, búsqueda por código y listado completo del inventario.
2.⁠ ⁠Control de Stock Crítico: visualización de productos ordenados por nivel de stock e identificación del producto que requiere reposición urgente.
3.⁠ ⁠Gestión de Pedidos: alta de pedidos con producto y cantidad, despacho FIFO con actualización automática de stock, y visualización de la cola.
4.⁠ Movimientos de Stock: registro de ingresos y egresos de mercadería con actualización de stock, y reversión del último movimiento registrado (deshacer).
5.⁠ ⁠Rutas del Depósito: modelado del depósito como grafo no dirigido, con alta de sectores, conexión entre pasillos y cálculo de la ruta más corta entre dos puntos usando BFS.


## Link del Repositorio
[https://github.com/florsorondo/tpoprogra2]

## Actividades Realizadas por Integrante
El desarrollo fue colaborativo. Lo siguiente refleja la responsabilidad principal de commit de cada integrante.
Florencia Sorondo Gardini: README.md, Pedido.java, PilaMovimientos.java
Joaquin Leon de la Barra: Grafodeposito.java
Bautista Fuster: Main.java