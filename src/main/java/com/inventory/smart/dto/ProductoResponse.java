package com.inventory.smart.dto;

import com.inventory.smart.model.Producto;

/**
 * DTO de salida con los datos completos de un producto.
 *
 * @param id          identificador único
 * @param nombre      nombre del producto
 * @param descripcion descripción del producto
 * @param precio      precio unitario
 * @param stock       cantidad actual en stock
 * @param categoria   datos de la categoría asociada
 * @author Grupo DevGirls
 * @since 1.0
 */
public record ProductoResponse(
        Long id,
        String nombre,
        String descripcion,
        double precio,
        int stock,
        CategoriaResponse categoria
) {
    /**
     * Convierte una entidad {@link Producto} a su DTO de respuesta.
     *
     * @param producto entidad a convertir
     * @return DTO con los datos del producto
     */
    public static ProductoResponse fromEntity(Producto producto) {
        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                CategoriaResponse.fromEntity(producto.getCategoria())
        );
    }
}