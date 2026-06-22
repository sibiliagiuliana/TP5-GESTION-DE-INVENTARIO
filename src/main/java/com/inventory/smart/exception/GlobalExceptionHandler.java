package com.inventory.smart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones del sistema.
 *
 * <p>Centraliza el manejo de errores para que todos los controllers
 * devuelvan respuestas HTTP consistentes sin repetir código.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
 * Constructor por defecto de GlobalExceptionHandler.
 */
public GlobalExceptionHandler() {}

    /**
     * Maneja recursos no encontrados → HTTP 404.
     *
     * @param ex excepción capturada
     * @return respuesta con código 404 y detalle del error
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Recurso no encontrado");
        body.put("mensaje", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    /**
     * Maneja stock insuficiente → HTTP 409.
     *
     * @param ex excepción capturada
     * @return respuesta con código 409 y detalle del stock disponible
     */
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Map<String, Object>> handleInsufficientStock(InsufficientStockException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Stock insuficiente");
        body.put("mensaje", ex.getMessage());
        body.put("productoId", ex.getProductoId());
        body.put("stockDisponible", ex.getStockDisponible());
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    /**
     * Maneja violaciones de reglas de negocio → HTTP 409.
     *
     * @param ex excepción capturada
     * @return respuesta con código 409 y detalle del error
     */
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessRule(BusinessRuleException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Regla de negocio violada");
        body.put("mensaje", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

   /**
 * Maneja errores de validación de campos → HTTP 400.
 *
 * <p>Se activa cuando un campo anotado con Valid, NotBlank, NotNull
 * o Positive no cumple su restriccion.</p>
 *
 * @param ex excepción capturada
 * @return respuesta con código 400 y lista de errores por campo
 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(e -> errores.put(e.getField(), e.getDefaultMessage()));

        body.put("error", "Error de validación");
        body.put("campos", errores);
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}