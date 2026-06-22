package com.inventory.smart.exception;

/**
 * Excepción lanzada cuando se intenta retirar más stock del disponible.
 *
 * <p>Se lanza con HTTP 409 Conflict cuando la cantidad solicitada
 * de salida supera el stock actual del producto.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public class InsufficientStockException extends RuntimeException {

    /** ID del producto con stock insuficiente. */
    private final Long productoId;

    /** Stock disponible al momento del error. */
    private final int stockDisponible;

    /**
     * Constructor con información detallada del error.
     *
     * @param mensaje          descripción del error
     * @param productoId       ID del producto afectado
     * @param stockDisponible  stock disponible al momento del error
     */
    public InsufficientStockException(String mensaje, Long productoId, int stockDisponible) {
        super(mensaje);
        this.productoId = productoId;
        this.stockDisponible = stockDisponible;
    }

    /**
     * Obtiene el ID del producto con stock insuficiente.
     *
     * @return el ID del producto
     */
    public Long getProductoId() { return productoId; }

    /**
     * Obtiene el stock disponible al momento del error.
     *
     * @return el stock disponible
     */
    public int getStockDisponible() { return stockDisponible; }
}