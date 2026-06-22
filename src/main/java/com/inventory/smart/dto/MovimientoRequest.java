package com.inventory.smart.dto;

import com.inventory.smart.model.TipoMovimiento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTO de entrada para registrar un movimiento de inventario.
 *
 * @param productoId ID del producto afectado
 * @param tipo       tipo de movimiento (ENTRADA o SALIDA)
 * @param cantidad   unidades a mover (debe ser positiva)
 * @param motivo     descripción o razón del movimiento
 * @author Grupo DevGirls
 * @since 1.0
 */
public record MovimientoRequest(

        @NotNull(message = "El producto es obligatorio")
        Long productoId,

        @NotNull(message = "El tipo de movimiento es obligatorio")
        TipoMovimiento tipo,

        @Positive(message = "La cantidad debe ser mayor a 0")
        int cantidad,

        String motivo
) {}