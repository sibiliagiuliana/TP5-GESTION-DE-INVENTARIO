package com.inventory.smart.controller;

import com.inventory.smart.dto.ProductoRequest;
import com.inventory.smart.dto.ProductoResponse;
import com.inventory.smart.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controller REST para la gestión de productos del inventario.
 *
 * <p>Expone endpoints CRUD, búsqueda y ordenamiento bajo /api/productos.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Gestión de productos del inventario")
public class ProductoController {

    /** Servicio de productos con la lógica de negocio. */
    private final ProductoService productoService;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param productoService servicio de productos
     */
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Lista todos los productos registrados.
     *
     * @return lista de productos con HTTP 200
     */
    @GetMapping
    @Operation(summary = "Listar todos los productos")
    public ResponseEntity<List<ProductoResponse>> listarTodos() {
        return ResponseEntity.ok(productoService.findAll());
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id identificador del producto
     * @return el producto encontrado con HTTP 200, o 404 si no existe
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID")
    public ResponseEntity<ProductoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    /**
     * Crea un nuevo producto en el inventario.
     *
     * @param request datos del producto a crear
     * @return el producto creado con HTTP 201 y header Location
     */
    @PostMapping
    @Operation(summary = "Crear un nuevo producto")
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest request) {
        ProductoResponse response = productoService.crear(request);
        URI location = URI.create("/api/productos/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id      identificador del producto a actualizar
     * @param request nuevos datos del producto
     * @return el producto actualizado con HTTP 200
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto existente")
    public ResponseEntity<ProductoResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(productoService.actualizar(id, request));
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id identificador del producto a eliminar
     * @return HTTP 204 si se eliminó, 404 si no existe
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Busca productos por nombre (case-insensitive).
     *
     * @param q texto a buscar en el nombre del producto
     * @return lista de productos que coinciden con HTTP 200
     */
    @GetMapping("/buscar")
    @Operation(summary = "Buscar productos por nombre")
    public ResponseEntity<List<ProductoResponse>> buscar(@RequestParam String q) {
        return ResponseEntity.ok(productoService.buscarPorNombre(q));
    }

    /**
     * Lista productos ordenados por un campo específico.
     *
     * @param campo campo por el que ordenar: nombre, precio o stock
     * @param orden dirección: asc o desc
     * @return lista de productos ordenados con HTTP 200
     */
    @GetMapping("/ordenados")
    @Operation(summary = "Listar productos ordenados por campo")
    public ResponseEntity<List<ProductoResponse>> listarOrdenados(
            @RequestParam(defaultValue = "nombre") String campo,
            @RequestParam(defaultValue = "asc") String orden) {
        return ResponseEntity.ok(productoService.listarOrdenados(campo, orden));
    }
}