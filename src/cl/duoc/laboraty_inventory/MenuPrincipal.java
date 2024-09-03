package cl.duoc.laboraty_inventory;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private Inventario inventario;
    private Scanner scanner;

    public MenuPrincipal(Inventario inventario) {
        this.inventario = inventario;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Eliminar Producto");
            System.out.println("3. Buscar Producto por Nombre o Descripción");
            System.out.println("4. Listar Todos los Productos");
            System.out.println("5. Generar Informe de Inventario");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    eliminarProducto();
                    break;
                case 3:
                    buscarProducto();
                    break;
                case 4:
                    listarProductos();
                    break;
                case 5:
                    generarInforme();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }

    private void agregarProducto() {
        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la descripción del producto: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Ingrese la cantidad en stock del producto: ");
        int cantidadEnStock = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Producto producto = new Producto(codigo, nombre, descripcion, precio, cantidadEnStock);
        inventario.agregarProducto(producto);
    }

    private void eliminarProducto() {
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine();
        inventario.eliminarProducto(codigo);
    }

    private void buscarProducto() {
        System.out.print("Ingrese el nombre o descripción del producto: ");
        String termino = scanner.nextLine();
        List<Producto> resultados = inventario.buscarProductoPorNombreODescripcion(termino);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos que coincidan con la búsqueda.");
        } else {
            System.out.println("Productos encontrados:");
            for (Producto producto : resultados) {
                System.out.println(producto.descripcionDetallada());
            }
        }
    }

    private void listarProductos() {
        List<Producto> productos = inventario.listarTodosLosProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            System.out.println("Lista de todos los productos:");
            for (Producto producto : productos) {
                System.out.println(producto.descripcionDetallada());
            }
        }
    }

    private void generarInforme() {
        String informe = inventario.generarInformeInventario();
        System.out.println(informe);
    }
}
