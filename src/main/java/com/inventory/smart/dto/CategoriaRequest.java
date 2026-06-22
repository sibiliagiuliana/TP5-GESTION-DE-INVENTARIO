package com.inventory.smart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada para crear o actualizar una categoría.
 *
 * @param nombre      nombre de la categoría (obligatorio)
 * @param descripcion descripción opcional
 * @author Grupo DevGirls
 * @since 1.0
 */
public record CategoriaRequest(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
        String nombre,

        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
        String descripcion
) {}