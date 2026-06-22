package com.inventory.smart.service;

import com.inventory.smart.dto.MovimientoRequest;
import com.inventory.smart.dto.MovimientoResponse;

import java.util.List;

/**
 * Contrato del servicio de gestión de movimientos de inventario.
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public interface MovimientoService {

    /**
     * Registra un nuevo movimiento de inventario (entrada o salida).
     *
     * @param request datos del movimiento a registrar
     * @return el movimiento registrado con su ID y stock resultante
     * @throws com.inventory.smart.exception.ResourceNotFoundException si el producto no existe
     * @throws com.inventory.smart.exception.InsufficientStockException si el stock es insuficiente
     */
    MovimientoResponse registrar(MovimientoRequest request);

    /**
     * Retorna el historial de movimientos de un producto específico.
     *
     * @param productoId ID del producto a consultar
     * @return lista de movimientos del producto ordenados por fecha
     * @throws com.inventory.smart.exception.ResourceNotFoundException si el producto no existe
     */
    List<MovimientoResponse> findByProductoId(Long productoId);
}
