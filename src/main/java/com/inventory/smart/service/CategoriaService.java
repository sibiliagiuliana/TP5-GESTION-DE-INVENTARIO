package com.inventory.smart.service;

import com.inventory.smart.dto.CategoriaRequest;
import com.inventory.smart.dto.CategoriaResponse;

import java.util.List;

/**
 * Contrato del servicio de gestión de categorías.
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public interface CategoriaService {

    /**
     * Retorna todas las categorías registradas.
     *
     * @return lista de categorías
     */
    List<CategoriaResponse> findAll();

    /**
     * Busca una categoría por su ID.
     *
     * @param id identificador de la categoría
     * @return la categoría encontrada
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     */
    CategoriaResponse findById(Long id);

    /**
     * Crea una nueva categoría.
     *
     * @param request datos de la categoría a crear
     * @return la categoría creada con su ID asignado
     */
    CategoriaResponse crear(CategoriaRequest request);

    /**
     * Actualiza una categoría existente.
     *
     * @param id      identificador de la categoría a actualizar
     * @param request nuevos datos de la categoría
     * @return la categoría actualizada
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     */
    CategoriaResponse actualizar(Long id, CategoriaRequest request);

    /**
     * Elimina una categoría por su ID.
     *
     * @param id identificador de la categoría a eliminar
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     * @throws com.inventory.smart.exception.BusinessRuleException si tiene productos asociados
     */
    void eliminar(Long id);
}