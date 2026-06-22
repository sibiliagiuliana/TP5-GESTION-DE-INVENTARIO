package com.inventory.smart.repository;
import com.inventory.smart.model.Categoria;

/**
 * Interfaz del repositorio de categorías.
 *
 * <p>Extiende {@link IGenericRepository} heredando las operaciones
 * CRUD base. No necesita queries adicionales.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public interface CategoriaRepository extends IGenericRepository<Categoria, Long> {
    // Hereda todo el CRUD de IGenericRepository
}