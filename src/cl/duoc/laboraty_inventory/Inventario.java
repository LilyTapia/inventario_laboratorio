package cl.duoc.laboraty_inventory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventario {

    private HashMap<String, Producto> productos;
    private String rutaArchivoCSV;

    public Inventario(String rutaArchivoCSV) {
        productos = new HashMap<>();
        this.rutaArchivoCSV = rutaArchivoCSV;
        cargarProductosDesdeCSV(rutaArchivoCSV); // Cargar productos al iniciar
    }

    public void agregarProducto(Producto producto) {
        if (productos.containsKey(producto.getCodigo())) {
            System.out.println("El producto con código " + producto.getCodigo() + " ya existe.");
        } else {
            productos.put(producto.getCodigo(), producto);
            guardarProductosEnCSV(rutaArchivoCSV); // Guardar en CSV después de agregar
            System.out.println("Producto agregado exitosamente.");
        }
    }

    public void eliminarProducto(String codigo) {
        if (productos.containsKey(codigo)) {
            productos.remove(codigo);
            guardarProductosEnCSV(rutaArchivoCSV); // Guardar en CSV después de eliminar
            System.out.println("Producto eliminado exitosamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public List<Producto> buscarProductoPorNombreODescripcion(String termino) {
        List<Producto> resultados = new ArrayList<>();
        for (Producto producto : productos.values()) {
            if (producto.getNombre().equalsIgnoreCase(termino) || producto.getDescripcion().contains(termino)) {
                resultados.add(producto);
            }
        }
        return resultados;
    }

    public List<Producto> listarTodosLosProductos() {
        return new ArrayList<>(productos.values());
    }

    public void cargarProductosDesdeCSV(String rutaArchivo) {
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            br.readLine(); // Saltar la línea de encabezado
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                if (valores.length == 5) { // Verificar que haya 5 elementos
                    String codigo = valores[0];
                    String nombre = valores[1];
                    String descripcion = valores[2];
                    double precio = Double.parseDouble(valores[3]);
                    int cantidadEnStock = Integer.parseInt(valores[4]);
                    Producto producto = new Producto(codigo, nombre, descripcion, precio, cantidadEnStock);
                    productos.put(codigo, producto); // Cargar el producto en el inventario
                }
            }
            System.out.println("Productos cargados desde el archivo CSV.");
        } catch (IOException e) {
            System.out.println("Error al cargar productos desde el archivo CSV: " + e.getMessage());
        }
    }

    public void guardarProductosEnCSV(String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write("Codigo,Nombre,Descripcion,Precio,CantidadEnStock");
            bw.newLine();
            for (Producto producto : productos.values()) {
                bw.write(producto.getCodigo() + "," + producto.getNombre() + "," + producto.getDescripcion() + ","
                        + producto.getPrecio() + "," + producto.getCantidadEnStock());
                bw.newLine();
            }
            System.out.println("Productos guardados en el archivo CSV.");
        } catch (IOException e) {
            System.out.println("Error al guardar productos en el archivo CSV: " + e.getMessage());
        }

    }

    public String generarInformeInventario() {
        StringBuilder informe = new StringBuilder("Informe de Inventario:\n");
        for (Producto producto : productos.values()) {
            informe.append(producto.descripcionDetallada()).append("\n");
        }
        return informe.toString();
    }
}
