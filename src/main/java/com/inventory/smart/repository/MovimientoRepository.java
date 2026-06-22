package com.inventory.smart.repository;
import com.inventory.smart.model.MovimientoInventario;
import java.util.List;

/**
 * Interfaz del repositorio de movimientos de inventario.
 *
 * <p>Extiende {@link IGenericRepository} y agrega la consulta
 * de movimientos filtrados por producto.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public interface MovimientoRepository extends IGenericRepository<MovimientoInventario, Long> {

    /**
     * Retorna todos los movimientos asociados a un producto específico.
     *
     * @param productoId ID del producto a consultar
     * @return lista de movimientos del producto
     */
    List<MovimientoInventario> findByProductoId(Long productoId);
}
