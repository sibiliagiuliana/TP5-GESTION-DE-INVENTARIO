package com.inventory.smart.repository;


import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica que define las operaciones CRUD básicas
 * para todos los repositorios del sistema.
 *
 * <p>Al ser genérica, puede ser reutilizada por cualquier entidad
 * sin duplicar código. T es el tipo de entidad e ID es el tipo
 * del identificador.</p>
 *
 * @param <T>  tipo de la entidad gestionada
 * @param <ID> tipo del identificador de la entidad
 * @author Grupo DevGirls
 * @since 1.0
 */
public interface IGenericRepository<T, ID> {

    /**
     * Retorna todas las entidades almacenadas.
     *
     * @return lista con todas las entidades
     */
    List<T> findAll();

    /**
     * Busca una entidad por su identificador único.
     *
     * @param id identificador de la entidad
     * @return un Optional con la entidad si existe, vacío si no
     */
    Optional<T> findById(ID id);

    /**
     * Guarda una entidad nueva o actualiza una existente.
     *
     * @param entity entidad a guardar
     * @return la entidad guardada con su ID asignado
     */
    T save(T entity);

    /**
     * Elimina una entidad por su identificador.
     *
     * @param id identificador de la entidad a eliminar
     */
    void deleteById(ID id);

    /**
     * Verifica si existe una entidad con el ID dado.
     *
     * @param id identificador a verificar
     * @return true si existe, false si no
     */
    boolean existsById(ID id);

    /**
     * Retorna la cantidad total de entidades almacenadas.
     *
     * @return cantidad de entidades
     */
    long count();
}