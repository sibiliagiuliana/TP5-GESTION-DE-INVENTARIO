package com.inventory.smart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada para crear o actualizar un producto.
 *
 * @param nombre       nombre del producto (obligatorio)
 * @param descripcion  descripción opcional
 * @param precio       precio unitario (mayor o igual a 0)
 * @param stockInicial cantidad inicial en stock (mayor o igual a 0)
 * @param categoriaId  ID de la categoría a la que pertenece
 * @author Grupo DevGirls
 * @since 1.0
 */
public record ProductoRequest(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
        String nombre,

        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
        String descripcion,

        @PositiveOrZero(message = "El precio debe ser mayor o igual a 0")
        double precio,

        @PositiveOrZero(message = "El stock inicial debe ser mayor o igual a 0")
        int stockInicial,

        @NotNull(message = "La categoría es obligatoria")
        Long categoriaId
) {}