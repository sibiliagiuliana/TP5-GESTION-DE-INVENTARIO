package com.inventory.smart.controller;

import com.inventory.smart.dto.MovimientoRequest;
import com.inventory.smart.dto.MovimientoResponse;
import com.inventory.smart.service.MovimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controller REST para el registro y consulta de movimientos de inventario.
 *
 * <p>Expone endpoints para registrar entradas/salidas y consultar
 * el historial de movimientos bajo /api/movimientos.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@RestController
@RequestMapping("/api/movimientos")
@Tag(name = "Movimientos", description = "Registro de entradas y salidas de stock")
public class MovimientoController {

    /** Servicio de movimientos con la lógica de negocio. */
    private final MovimientoService movimientoService;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param movimientoService servicio de movimientos
     */
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    /**
     * Registra un nuevo movimiento de inventario.
     *
     * @param request datos del movimiento (productoId, tipo, cantidad, motivo)
     * @return el movimiento registrado con HTTP 201
     */
    @PostMapping
    @Operation(summary = "Registrar un movimiento de inventario")
    public ResponseEntity<MovimientoResponse> registrar(
            @Valid @RequestBody MovimientoRequest request) {
        MovimientoResponse response = movimientoService.registrar(request);
        URI location = URI.create("/api/movimientos/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    /**
     * Obtiene el historial de movimientos de un producto.
     *
     * @param id identificador del producto
     * @return lista de movimientos del producto con HTTP 200
     */
    @GetMapping("/producto/{id}")
    @Operation(summary = "Historial de movimientos de un producto")
    public ResponseEntity<List<MovimientoResponse>> obtenerPorProducto(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.findByProductoId(id));
    }
}
