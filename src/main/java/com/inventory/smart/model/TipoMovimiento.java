package com.inventory.smart.model;

/**
 * Tipos posibles de movimiento de inventario.
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public enum TipoMovimiento {

    /** Incremento de stock (compra, devolución, etc.). */
    ENTRADA,

    /** Decremento de stock (venta, pérdida, etc.). */
    SALIDA
}
