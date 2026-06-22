package com.inventory.smart.service;

import com.inventory.smart.dto.CategoriaRequest;
import com.inventory.smart.dto.CategoriaResponse;
import com.inventory.smart.exception.BusinessRuleException;
import com.inventory.smart.exception.ResourceNotFoundException;
import com.inventory.smart.model.Categoria;
import com.inventory.smart.repository.CategoriaRepository;
import com.inventory.smart.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de categorías.
 *
 * <p>Contiene la lógica de negocio para gestionar categorías,
 * incluyendo la validación de integridad referencial al eliminar.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    /** Repositorio de categorías. */
    private final CategoriaRepository categoriaRepository;

    /** Repositorio de productos para validar integridad referencial. */
    private final ProductoRepository productoRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param categoriaRepository repositorio de categorías
     * @param productoRepository  repositorio de productos
     */
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository,
                                 ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CategoriaResponse> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaResponse::fromEntity)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoriaResponse findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría no encontrada con ID: " + id));
        return CategoriaResponse.fromEntity(categoria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoriaResponse crear(CategoriaRequest request) {
        Categoria categoria = new Categoria(null, request.nombre(), request.descripcion());
        return CategoriaResponse.fromEntity(categoriaRepository.save(categoria));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CategoriaResponse actualizar(Long id, CategoriaRequest request) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría no encontrada con ID: " + id));
        categoria.setNombre(request.nombre());
        categoria.setDescripcion(request.descripcion());
        return CategoriaResponse.fromEntity(categoriaRepository.save(categoria));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoría no encontrada con ID: " + id);
        }
        boolean tieneProductos = !productoRepository.findByCategoria(id).isEmpty();
        if (tieneProductos) {
            throw new BusinessRuleException(
                    "No se puede eliminar la categoría porque tiene productos asociados");
        }
        categoriaRepository.deleteById(id);
    }
}