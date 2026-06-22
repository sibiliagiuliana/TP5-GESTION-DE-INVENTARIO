package com.inventory.smart.dto;

import com.inventory.smart.model.Categoria;

/**
 * DTO de salida con los datos de una categoría.
 *
 * @param id          identificador único
 * @param nombre      nombre de la categoría
 * @param descripcion descripción de la categoría
 * @author Grupo DevGirls
 * @since 1.0
 */
public record CategoriaResponse(
        Long id,
        String nombre,
        String descripcion
) {
    /**
     * Convierte una entidad {@link Categoria} a su DTO de respuesta.
     *
     * @param categoria entidad a convertir
     * @return DTO con los datos de la categoría
     */
    public static CategoriaResponse fromEntity(Categoria categoria) {
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }
}