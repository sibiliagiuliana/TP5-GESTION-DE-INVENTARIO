package com.inventory.smart.repository;
import com.inventory.smart.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementación abstracta de {@link IGenericRepository} que usa
 * un {@link ConcurrentHashMap} como almacenamiento en memoria.
 *
 * <p>Provee el comportamiento CRUD base reutilizable para todos
 * los repositorios concretos del sistema. Las subclases solo
 * necesitan extender esta clase y agregar queries específicas.</p>
 *
 * <p>Se usa {@link ConcurrentHashMap} porque es thread-safe: permite
 * que múltiples requests HTTP accedan al mapa al mismo tiempo sin
 * causar condiciones de carrera.</p>
 *
 * @param <T>  tipo de la entidad gestionada
 * @param <ID> tipo del identificador (se asume Long en la práctica)
 * @author Grupo DevGirls
 * @since 1.0
 */
public abstract class GenericInMemoryRepository<T, ID> implements IGenericRepository<T, ID> {

    /**
     * Almacenamiento en memoria de las entidades.
     * ConcurrentHashMap es thread-safe sin bloquear todo el mapa.
     */
    protected final ConcurrentHashMap<ID, T> dataStore = new ConcurrentHashMap<>();

    /**
     * Generador de IDs autoincremental y thread-safe.
     * Inicia en 1 y se incrementa con cada entidad nueva.
     */
    protected final AtomicLong idGenerator = new AtomicLong(1);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll() {
        return new ArrayList<>(dataStore.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(dataStore.get(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(ID id) {
        if (!dataStore.containsKey(id)) {
            throw new ResourceNotFoundException("Entidad no encontrada con ID: " + id);
        }
        dataStore.remove(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsById(ID id) {
        return dataStore.containsKey(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long count() {
        return dataStore.size();
    }
}