package com.inventory.smart.model;
import java.time.LocalDateTime;

/**
 * Representa un movimiento de inventario (entrada o salida de stock).
 *
 * <p>Cada vez que se incrementa o decrementa el stock de un producto,
 * se registra un movimiento para mantener un historial trazable.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public class MovimientoInventario {

    /** Identificador único del movimiento. */
    private Long id;

    /** Producto al que corresponde este movimiento. */
    private Long productoId;

    /** Tipo de movimiento: ENTRADA o SALIDA. */
    private TipoMovimiento tipo;

    /** Cantidad de unidades movidas. */
    private int cantidad;

    /** Stock resultante después del movimiento. */
    private int stockResultante;

    /** Motivo o descripción del movimiento. */
    private String motivo;

    /** Fecha y hora en que se registró el movimiento. */
    private LocalDateTime fecha;

    /**
     * Constructor completo de MovimientoInventario.
     *
     * @param id               identificador único
     * @param productoId       ID del producto involucrado
     * @param tipo             tipo de movimiento (ENTRADA o SALIDA)
     * @param cantidad         unidades movidas
     * @param stockResultante  stock del producto después del movimiento
     * @param motivo           descripción o razón del movimiento
     * @param fecha            fecha y hora del movimiento
     */
    public MovimientoInventario(Long id, Long productoId, TipoMovimiento tipo,
                                 int cantidad, int stockResultante,
                                 String motivo, LocalDateTime fecha) {
        this.id = id;
        this.productoId = productoId;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.stockResultante = stockResultante;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    /**
     * Obtiene el ID del movimiento.
     *
     * @return el identificador único
     */
    public Long getId() { return id; }

    /**
     * Establece el ID del movimiento.
     *
     * @param id el identificador único
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Obtiene el ID del producto asociado.
     *
     * @return el ID del producto
     */
    public Long getProductoId() { return productoId; }

    /**
     * Establece el ID del producto asociado.
     *
     * @param productoId el ID del producto
     */
    public void setProductoId(Long productoId) { this.productoId = productoId; }

    /**
     * Obtiene el tipo de movimiento.
     *
     * @return ENTRADA o SALIDA
     */
    public TipoMovimiento getTipo() { return tipo; }

    /**
     * Establece el tipo de movimiento.
     *
     * @param tipo ENTRADA o SALIDA
     */
    public void setTipo(TipoMovimiento tipo) { this.tipo = tipo; }

    /**
     * Obtiene la cantidad de unidades movidas.
     *
     * @return la cantidad
     */
    public int getCantidad() { return cantidad; }

    /**
     * Establece la cantidad de unidades movidas.
     *
     * @param cantidad la cantidad
     */
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    /**
     * Obtiene el stock resultante después del movimiento.
     *
     * @return el stock resultante
     */
    public int getStockResultante() { return stockResultante; }

    /**
     * Establece el stock resultante después del movimiento.
     *
     * @param stockResultante el stock resultante
     */
    public void setStockResultante(int stockResultante) { this.stockResultante = stockResultante; }

    /**
     * Obtiene el motivo del movimiento.
     *
     * @return el motivo
     */
    public String getMotivo() { return motivo; }

    /**
     * Establece el motivo del movimiento.
     *
     * @param motivo el motivo
     */
    public void setMotivo(String motivo) { this.motivo = motivo; }

    /**
     * Obtiene la fecha y hora del movimiento.
     *
     * @return la fecha y hora
     */
    public LocalDateTime getFecha() { return fecha; }

    /**
     * Establece la fecha y hora del movimiento.
     *
     * @param fecha la fecha y hora
     */
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}