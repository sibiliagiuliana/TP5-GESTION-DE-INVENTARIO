package com.inventory.smart.exception;

/**
 * Excepción lanzada cuando se viola una regla de negocio del sistema.
 *
 * <p>Ejemplos: eliminar una categoría con productos asociados,
 * o crear un producto con stock negativo.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public class BusinessRuleException extends RuntimeException {

    /**
     * Constructor con mensaje descriptivo de la regla violada.
     *
     * @param mensaje descripción de la regla de negocio violada
     */
    public BusinessRuleException(String mensaje) {
        super(mensaje);
    }
}
