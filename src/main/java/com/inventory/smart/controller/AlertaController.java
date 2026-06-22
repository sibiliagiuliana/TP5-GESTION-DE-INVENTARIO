package com.inventory.smart.controller;

import com.inventory.smart.dto.AlertaStockResponse;
import com.inventory.smart.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller REST para la consulta de alertas de stock.
 *
 * <p>Expone endpoints para consultar productos con stock
 * por debajo de los umbrales configurados.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@RestController
@RequestMapping("/api/alertas")
@Tag(name = "Alertas", description = "Consulta de alertas de stock bajo")
public class AlertaController {

    /** Servicio de alertas con la lógica de evaluación de stock. */
    private final AlertaService alertaService;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param alertaService servicio de alertas
     */
    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    /**
     * Lista todos los productos con stock por debajo del mínimo configurado.
     *
     * @return lista de productos en alerta con su nivel (BAJO o CRITICO)
     */
    @GetMapping("/stock-bajo")
    @Operation(summary = "Listar productos con stock bajo o crítico")
    public ResponseEntity<List<AlertaStockResponse>> obtenerAlertasStockBajo() {
        return ResponseEntity.ok(alertaService.obtenerAlertasStockBajo());
    }
}