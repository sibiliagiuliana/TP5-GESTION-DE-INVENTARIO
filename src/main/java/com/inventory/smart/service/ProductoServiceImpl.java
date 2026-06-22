package com.inventory.smart.service;

import com.inventory.smart.dto.ProductoRequest;
import com.inventory.smart.dto.ProductoResponse;
import com.inventory.smart.exception.ResourceNotFoundException;
import com.inventory.smart.model.Categoria;
import com.inventory.smart.model.Producto;
import com.inventory.smart.repository.CategoriaRepository;
import com.inventory.smart.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Implementación del servicio de productos.
 *
 * <p>Contiene la lógica de negocio para gestionar productos,
 * búsquedas y ordenamiento parametrizado.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    /** Repositorio de productos. */
    private final ProductoRepository productoRepository;

    /** Repositorio de categorías para validar existencia al crear productos. */
    private final CategoriaRepository categoriaRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param productoRepository  repositorio de productos
     * @param categoriaRepository repositorio de categorías
     */
    public ProductoServiceImpl(ProductoRepository productoRepository,
                                CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductoResponse> findAll() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoResponse::fromEntity)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductoResponse findById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Producto no encontrado con ID: " + id));
        return ProductoResponse.fromEntity(producto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductoResponse crear(ProductoRequest request) {
        Categoria categoria = categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría no encontrada con ID: " + request.categoriaId()));
        Producto producto = new Producto(
                null,
                request.nombre(),
                request.descripcion(),
                request.precio(),
                request.stockInicial(),
                categoria
        );
        return ProductoResponse.fromEntity(productoRepository.save(producto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Producto no encontrado con ID: " + id));
        Categoria categoria = categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Categoría no encontrada con ID: " + request.categoriaId()));
        producto.setNombre(request.nombre());
        producto.setDescripcion(request.descripcion());
        producto.setPrecio(request.precio());
        producto.setCategoria(categoria);
        return ProductoResponse.fromEntity(productoRepository.save(producto));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductoResponse> buscarPorNombre(String texto) {
        return productoRepository.buscarPorNombre(texto)
                .stream()
                .map(ProductoResponse::fromEntity)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductoResponse> listarOrdenados(String campo, String orden) {
        List<Producto> productos = productoRepository.findAll();

        Comparator<Producto> comparator = switch (campo.toLowerCase()) {
            case "precio" -> Comparator.comparingDouble(Producto::getPrecio);
            case "stock"  -> Comparator.comparingInt(Producto::getStock);
            default       -> Comparator.comparing(Producto::getNombre);
        };

        if ("desc".equalsIgnoreCase(orden)) {
            comparator = comparator.reversed();
        }

        return productos.stream()
                .sorted(comparator)
                .map(ProductoResponse::fromEntity)
                .toList();
    }
}