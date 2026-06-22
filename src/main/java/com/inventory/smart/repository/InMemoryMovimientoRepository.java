package com.inventory.smart.repository;

import com.inventory.smart.model.MovimientoInventario;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementación en memoria del repositorio de movimientos de inventario.
 *
 * <p>Extiende {@link GenericInMemoryRepository} y agrega la consulta
 * de movimientos filtrados por producto.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@Repository
public class InMemoryMovimientoRepository 
        extends GenericInMemoryRepository<MovimientoInventario, Long>
        implements MovimientoRepository {
/**
 * Constructor por defecto.
 */
public InMemoryMovimientoRepository() {}

    /**
     * {@inheritDoc}
     *
     * <p>Si el movimiento no tiene ID, genera uno nuevo. Complejidad: O(1).</p>
     */
    @Override
    public MovimientoInventario save(MovimientoInventario movimiento) {
        if (movimiento.getId() == null) {
            movimiento.setId(idGenerator.getAndIncrement());
        }
        dataStore.put(movimiento.getId(), movimiento);
        return movimiento;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Filtra movimientos por producto usando Stream. Complejidad: O(n).</p>
     */
    @Override
    public List<MovimientoInventario> findByProductoId(Long productoId) {
        return dataStore.values().stream()
                .filter(m -> m.getProductoId().equals(productoId))
                .toList();
    }
}