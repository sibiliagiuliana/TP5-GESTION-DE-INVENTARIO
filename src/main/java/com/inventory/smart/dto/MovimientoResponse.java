package com.inventory.smart.dto;

import com.inventory.smart.model.MovimientoInventario;
import com.inventory.smart.model.TipoMovimiento;

import java.time.LocalDateTime;

/**
 * DTO de salida con los datos de un movimiento de inventario.
 *
 * @param id              identificador único del movimiento
 * @param productoId      ID del producto afectado
 * @param tipo            tipo de movimiento (ENTRADA o SALIDA)
 * @param cantidad        unidades movidas
 * @param stockResultante stock del producto después del movimiento
 * @param motivo          razón del movimiento
 * @param fecha           fecha y hora del movimiento
 * @author Grupo DevGirls
 * @since 1.0
 */
public record MovimientoResponse(
        Long id,
        Long productoId,
        TipoMovimiento tipo,
        int cantidad,
        int stockResultante,
        String motivo,
        LocalDateTime fecha
) {
    /**
     * Convierte una entidad {@link MovimientoInventario} a su DTO de respuesta.
     *
     * @param movimiento entidad a convertir
     * @return DTO con los datos del movimiento
     */
    public static MovimientoResponse fromEntity(MovimientoInventario movimiento) {
        return new MovimientoResponse(
                movimiento.getId(),
                movimiento.getProductoId(),
                movimiento.getTipo(),
                movimiento.getCantidad(),
                movimiento.getStockResultante(),
                movimiento.getMotivo(),
                movimiento.getFecha()
        );
    }
}