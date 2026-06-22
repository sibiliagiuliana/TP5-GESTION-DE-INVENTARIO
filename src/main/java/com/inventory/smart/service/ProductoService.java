package com.inventory.smart.service;

import com.inventory.smart.dto.ProductoRequest;
import com.inventory.smart.dto.ProductoResponse;

import java.util.List;

/**
 * Contrato del servicio de gestión de productos.
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public interface ProductoService {

    /**
     * Retorna todos los productos registrados.
     *
     * @return lista de productos
     */
    List<ProductoResponse> findAll();

    /**
     * Busca un producto por su ID.
     *
     * @param id identificador del producto
     * @return el producto encontrado
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     */
    ProductoResponse findById(Long id);

    /**
     * Crea un nuevo producto.
     *
     * @param request datos del producto a crear
     * @return el producto creado con su ID asignado
     * @throws com.inventory.smart.exception.ResourceNotFoundException si la categoría no existe
     */
    ProductoResponse crear(ProductoRequest request);

    /**
     * Actualiza un producto existente.
     *
     * @param id      identificador del producto a actualizar
     * @param request nuevos datos del producto
     * @return el producto actualizado
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     */
    ProductoResponse actualizar(Long id, ProductoRequest request);

    /**
     * Elimina un producto por su ID.
     *
     * @param id identificador del producto a eliminar
     * @throws com.inventory.smart.exception.ResourceNotFoundException si no existe
     */
    void eliminar(Long id);

    /**
     * Busca productos cuyo nombre contiene el texto dado.
     *
     * @param texto texto a buscar (case-insensitive)
     * @return lista de productos que coinciden
     */
    List<ProductoResponse> buscarPorNombre(String texto);

    /**
     * Retorna productos ordenados por un campo específico.
     *
     * @param campo campo por el que ordenar (nombre, precio, stock)
     * @param orden dirección del orden (asc o desc)
     * @return lista de productos ordenados
     */
    List<ProductoResponse> listarOrdenados(String campo, String orden);
}
