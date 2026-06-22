package com.inventory.smart.dto;

import com.inventory.smart.model.NivelAlerta;

/**
 * DTO de salida para productos en estado de alerta de stock.
 *
 * @param productoId   ID del producto en alerta
 * @param nombre       nombre del producto
 * @param stockActual  cantidad actual en stock
 * @param nivelAlerta  nivel de alerta (BAJO o CRITICO)
 * @author Grupo DevGirls
 * @since 1.0
 */
public record AlertaStockResponse(
        Long productoId,
        String nombre,
        int stockActual,
        NivelAlerta nivelAlerta
) {}