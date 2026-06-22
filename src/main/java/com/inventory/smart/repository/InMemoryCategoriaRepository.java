package com.inventory.smart.repository;


import com.inventory.smart.model.Categoria;
import org.springframework.stereotype.Repository;

/**
 * Implementación en memoria del repositorio de categorías.
 *
 * <p>Extiende {@link GenericInMemoryRepository} heredando todo
 * el comportamiento CRUD base sin necesidad de queries extra.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@Repository
public class InMemoryCategoriaRepository
        extends GenericInMemoryRepository<Categoria, Long>
        implements CategoriaRepository {

/**
 * Constructor por defecto.
 */
public InMemoryCategoriaRepository() {}

    /**
     * {@inheritDoc}
     *
     * <p>Si la categoría no tiene ID, genera uno nuevo.
     * Si ya tiene ID, actualiza la existente. Complejidad: O(1).</p>
     */
    @Override
    public Categoria save(Categoria categoria) {
        if (categoria.getId() == null) {
            categoria.setId(idGenerator.getAndIncrement());
        }
        dataStore.put(categoria.getId(), categoria);
        return categoria;
    }
}