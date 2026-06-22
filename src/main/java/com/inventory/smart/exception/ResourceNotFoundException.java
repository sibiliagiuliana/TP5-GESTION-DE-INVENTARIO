package com.inventory.smart.exception;

/**
 * Excepción lanzada cuando no se encuentra un recurso por su ID.
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor con mensaje descriptivo.
     *
     * @param mensaje descripción del recurso no encontrado
     */
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
