package com.inventory.smart.repository;
import com.inventory.smart.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementación en memoria del repositorio de productos.
 *
 * <p>Extiende {@link GenericInMemoryRepository} heredando el CRUD base
 * con {@link java.util.concurrent.ConcurrentHashMap} e implementa
 * las queries específicas de productos.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@Repository
public class InMemoryProductoRepository
        extends GenericInMemoryRepository<Producto, Long>
        implements ProductoRepository {

            /**
 * Constructor por defecto.
 */
public InMemoryProductoRepository() {}

    /**
     * {@inheritDoc}
     *
     * <p>Si el producto no tiene ID asignado, genera uno nuevo.
     * Si ya tiene ID, actualiza el existente. Complejidad: O(1).</p>
     */
    @Override
    public Producto save(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(idGenerator.getAndIncrement());
        }
        dataStore.put(producto.getId(), producto);
        return producto;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Filtra por categoría usando Stream. Complejidad: O(n).</p>
     */
    @Override
    public List<Producto> findByCategoria(Long categoriaId) {
        return dataStore.values().stream()
                .filter(p -> p.getCategoria().getId().equals(categoriaId))
                .toList();
    }

    /**
     * {@inheritDoc}
     *
     * <p>Búsqueda case-insensitive por nombre. Complejidad: O(n).</p>
     */
    @Override
    public List<Producto> buscarPorNombre(String texto) {
        String lower = texto.toLowerCase();
        return dataStore.values().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(lower))
                .toList();
    }
}