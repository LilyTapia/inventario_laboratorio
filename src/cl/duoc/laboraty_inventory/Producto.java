package cl.duoc.laboraty_inventory;

public class Producto {

    private String codigo;      // ID único del producto
    private String nombre;      // Nombre del producto
    private String descripcion; // Descripción del producto
    private double precio;      // Precio del producto
    private int cantidadEnStock; // Cantidad disponible en inventario

    // Constructor
    public Producto(String codigo, String nombre, String descripcion, double precio, int cantidadEnStock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    // Método para obtener una descripción detallada del producto
    public String descripcionDetallada() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", Descripción: " + descripcion
                + ", Precio: $" + precio + ", Cantidad en Stock: " + cantidadEnStock;
    }
}
