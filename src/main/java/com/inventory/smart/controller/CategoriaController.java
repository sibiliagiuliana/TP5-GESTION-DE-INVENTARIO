package com.inventory.smart.controller;

import com.inventory.smart.dto.CategoriaRequest;
import com.inventory.smart.dto.CategoriaResponse;
import com.inventory.smart.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controller REST para la gestión de categorías.
 *
 * <p>Expone los endpoints CRUD de categorías bajo /api/categorias.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorías", description = "Gestión de categorías de productos")
public class CategoriaController {

    /** Servicio de categorías con la lógica de negocio. */
    private final CategoriaService categoriaService;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param categoriaService servicio de categorías
     */
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * Lista todas las categorías registradas.
     *
     * @return lista de categorías con HTTP 200
     */
    @GetMapping
    @Operation(summary = "Listar todas las categorías")
    public ResponseEntity<List<CategoriaResponse>> listarTodas() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    /**
     * Obtiene una categoría por su ID.
     *
     * @param id identificador de la categoría
     * @return la categoría encontrada con HTTP 200, o 404 si no existe
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoría por ID")
    public ResponseEntity<CategoriaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    /**
     * Crea una nueva categoría.
     *
     * @param request datos de la categoría a crear
     * @return la categoría creada con HTTP 201 y header Location
     */
    @PostMapping
    @Operation(summary = "Crear una nueva categoría")
    public ResponseEntity<CategoriaResponse> crear(@Valid @RequestBody CategoriaRequest request) {
        CategoriaResponse response = categoriaService.crear(request);
        URI location = URI.create("/api/categorias/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    /**
     * Actualiza una categoría existente.
     *
     * @param id      identificador de la categoría a actualizar
     * @param request nuevos datos de la categoría
     * @return la categoría actualizada con HTTP 200
     */
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoría existente")
    public ResponseEntity<CategoriaResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(categoriaService.actualizar(id, request));
    }

    /**
     * Elimina una categoría por su ID.
     *
     * @param id identificador de la categoría a eliminar
     * @return HTTP 204 si se eliminó, 404 si no existe, 409 si tiene productos
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoría")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
