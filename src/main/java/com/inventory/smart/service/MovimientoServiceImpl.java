package com.inventory.smart.service;

import com.inventory.smart.dto.MovimientoRequest;
import com.inventory.smart.dto.MovimientoResponse;
import com.inventory.smart.exception.InsufficientStockException;
import com.inventory.smart.exception.ResourceNotFoundException;
import com.inventory.smart.model.MovimientoInventario;
import com.inventory.smart.model.Producto;
import com.inventory.smart.model.TipoMovimiento;
import com.inventory.smart.repository.MovimientoRepository;
import com.inventory.smart.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementación del servicio de movimientos de inventario.
 *
 * <p>Gestiona las entradas y salidas de stock de forma atómica
 * usando {@link java.util.concurrent.atomic.AtomicInteger}.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@Service
public class MovimientoServiceImpl implements MovimientoService {

    /** Repositorio de movimientos. */
    private final MovimientoRepository movimientoRepository;

    /** Repositorio de productos para actualizar el stock. */
    private final ProductoRepository productoRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param movimientoRepository repositorio de movimientos
     * @param productoRepository   repositorio de productos
     */
    public MovimientoServiceImpl(MovimientoRepository movimientoRepository,
                                  ProductoRepository productoRepository) {
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovimientoResponse registrar(MovimientoRequest request) {

        // Verificar que el producto existe
        Producto producto = productoRepository.findById(request.productoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Producto no encontrado con ID: " + request.productoId()));

        int stockResultante;

        if (request.tipo() == TipoMovimiento.SALIDA) {
            // Verificar stock suficiente antes de decrementar
            if (producto.getStock() < request.cantidad()) {
                throw new InsufficientStockException(
                        "No se pueden retirar " + request.cantidad() +
                        " unidades. Stock disponible: " + producto.getStock(),
                        producto.getId(),
                        producto.getStock()
                );
            }
            stockResultante = producto.decrementarStock(request.cantidad());
        } else {
            stockResultante = producto.incrementarStock(request.cantidad());
        }

        // Registrar el movimiento en el historial
        MovimientoInventario movimiento = new MovimientoInventario(
                null,
                producto.getId(),
                request.tipo(),
                request.cantidad(),
                stockResultante,
                request.motivo(),
                LocalDateTime.now()
        );

        return MovimientoResponse.fromEntity(movimientoRepository.save(movimiento));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MovimientoResponse> findByProductoId(Long productoId) {
        if (!productoRepository.existsById(productoId)) {
            throw new ResourceNotFoundException(
                    "Producto no encontrado con ID: " + productoId);
        }
        return movimientoRepository.findByProductoId(productoId)
                .stream()
                .map(MovimientoResponse::fromEntity)
                .toList();
    }
}
