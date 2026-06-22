package com.inventory.smart.model;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representa un producto del inventario del depósito.
 *
 * <p>El stock se maneja con {@link AtomicInteger} para garantizar
 * thread-safety en operaciones concurrentes.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public class Producto {

    /** Identificador único del producto. */
    private Long id;

    /** Nombre del producto. */
    private String nombre;

    /** Descripción opcional del producto. */
    private String descripcion;

    /** Precio unitario del producto. */
    private double precio;

    /**
     * Stock actual del producto.
     * Se usa AtomicInteger para que múltiples requests
     * no pisen el valor al mismo tiempo (thread-safe).
     */
    private AtomicInteger stock;

    /**
     * Categoría a la que pertenece el producto.
     * Usamos composición: Producto TIENE UNA Categoria.
     */
    private Categoria categoria;

    /**
     * Constructor completo de Producto.
     *
     * @param id          identificador único
     * @param nombre      nombre del producto
     * @param descripcion descripción opcional
     * @param precio      precio unitario
     * @param stock       cantidad inicial en stock
     * @param categoria   categoría del producto
     */
    public Producto(Long id, String nombre, String descripcion,
                    double precio, int stock, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = new AtomicInteger(stock);
        this.categoria = categoria;
    }

    /**
     * Incrementa el stock del producto en la cantidad indicada.
     *
     * @param cantidad cantidad a incrementar (debe ser positiva)
     * @return el nuevo valor del stock después del incremento
     */
    public int incrementarStock(int cantidad) {
        return stock.addAndGet(cantidad);
    }

    /**
     * Decrementa el stock del producto en la cantidad indicada.
     *
     * @param cantidad cantidad a decrementar (debe ser positiva)
     * @return el nuevo valor del stock después del decremento
     */
    public int decrementarStock(int cantidad) {
        return stock.addAndGet(-cantidad);
    }

    /**
     * Obtiene el ID del producto.
     *
     * @return el identificador único
     */
    public Long getId() { return id; }

    /**
     * Establece el ID del producto.
     *
     * @param id el identificador único
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Obtiene el nombre del producto.
     *
     * @return el nombre
     */
    public String getNombre() { return nombre; }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre el nombre
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Obtiene la descripción del producto.
     *
     * @return la descripción
     */
    public String getDescripcion() { return descripcion; }

    /**
     * Establece la descripción del producto.
     *
     * @param descripcion la descripción
     */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    /**
     * Obtiene el precio unitario del producto.
     *
     * @return el precio
     */
    public double getPrecio() { return precio; }

    /**
     * Establece el precio unitario del producto.
     *
     * @param precio el precio
     */
    public void setPrecio(double precio) { this.precio = precio; }

    /**
     * Obtiene el stock actual del producto.
     *
     * @return la cantidad en stock
     */
    public int getStock() { return stock.get(); }

    /**
     * Establece el stock del producto.
     *
     * @param stock la cantidad en stock
     */
    public void setStock(int stock) { this.stock.set(stock); }

    /**
     * Obtiene la categoría del producto.
     *
     * @return la categoría
     */
    public Categoria getCategoria() { return categoria; }

    /**
     * Establece la categoría del producto.
     *
     * @param categoria la categoría
     */
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
