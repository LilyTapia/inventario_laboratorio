package cl.duoc.laboraty_inventory;

public class ClDuocLaboraty_inventory {
    public static void main(String[] args) {
        // Proporciona la ruta del archivo CSV donde se guardarán los productos
        String rutaArchivoCSV = "src/csv/materiales_laboratorio.csv";
        // Crear una instancia de Inventario
        Inventario inventario = new Inventario(rutaArchivoCSV);

        // Inicializar el menú principal
        MenuPrincipal menu = new MenuPrincipal(inventario);
        // Mostrar el menú principal
        menu.mostrarMenu();
    }
}