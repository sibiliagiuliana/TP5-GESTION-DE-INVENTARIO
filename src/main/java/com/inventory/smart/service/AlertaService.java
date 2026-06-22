package com.inventory.smart.service;

import com.inventory.smart.dto.AlertaStockResponse;

import java.util.List;

/**
 * Contrato del servicio de alertas de stock.
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public interface AlertaService {

    /**
     * Retorna todos los productos con stock por debajo del mínimo configurado.
     *
     * <p>Cada producto incluye su nivel de alerta (BAJO o CRITICO)
     * según los umbrales definidos en application.yml.</p>
     *
     * @return lista de productos en estado de alerta
     */
    List<AlertaStockResponse> obtenerAlertasStockBajo();
}
