
package com.inventory.smart.model;

/**
 * Representa una categoría que agrupa productos del inventario.
 *
 * <p>Ejemplos: Electrónicos, Alimentos, Limpieza.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public class Categoria {

    /** Identificador único de la categoría. */
    private Long id;

    /** Nombre descriptivo de la categoría. */
    private String nombre;

    /** Descripción opcional de la categoría. */
    private String descripcion;

    /**
     * Constructor completo de Categoria.
     *
     * @param id          identificador único
     * @param nombre      nombre de la categoría
     * @param descripcion descripción opcional
     */
    public Categoria(Long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el ID de la categoría.
     *
     * @return el identificador único
     */
    public Long getId() { return id; }

    /**
     * Establece el ID de la categoría.
     *
     * @param id el identificador único
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Obtiene el nombre de la categoría.
     *
     * @return el nombre
     */
    public String getNombre() { return nombre; }

    /**
     * Establece el nombre de la categoría.
     *
     * @param nombre el nombre
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Obtiene la descripción de la categoría.
     *
     * @return la descripción
     */
    public String getDescripcion() { return descripcion; }

    /**
     * Establece la descripción de la categoría.
     *
     * @param descripcion la descripción
     */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}