package com.inventory.smart.repository;
import com.inventory.smart.model.Producto;
import java.util.List;

/**
 * Interfaz del repositorio de productos.
 *
 * <p>Extiende {@link IGenericRepository} heredando las operaciones CRUD
 * base y agrega queries específicas de productos.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public interface ProductoRepository extends IGenericRepository<Producto, Long> {

    /**
     * Busca todos los productos que pertenecen a una categoría.
     *
     * @param categoriaId ID de la categoría a filtrar
     * @return lista de productos de esa categoría
     */
    List<Producto> findByCategoria(Long categoriaId);

    /**
     * Busca productos cuyo nombre contiene el texto dado
     * (búsqueda case-insensitive).
     *
     * @param texto texto a buscar en el nombre
     * @return lista de productos que coinciden
     */
    List<Producto> buscarPorNombre(String texto);
}
