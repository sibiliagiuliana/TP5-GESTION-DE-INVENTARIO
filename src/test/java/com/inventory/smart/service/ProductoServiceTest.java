package com.inventory.smart.service;

import com.inventory.smart.dto.ProductoRequest;
import com.inventory.smart.dto.ProductoResponse;
import com.inventory.smart.exception.ResourceNotFoundException;
import com.inventory.smart.model.Categoria;
import com.inventory.smart.model.Producto;
import com.inventory.smart.repository.CategoriaRepository;
import com.inventory.smart.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests unitarios del servicio de productos.
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Categoria categoriaEjemplo;
    private Producto productoEjemplo;

    @BeforeEach
    void setUp() {
        categoriaEjemplo = new Categoria(1L, "Electrónicos", "Dispositivos electrónicos");
        productoEjemplo = new Producto(1L, "Notebook", "Laptop", 1500.0, 10, categoriaEjemplo);
    }

    @Test
    @DisplayName("findAll retorna lista de productos")
    void findAll_retornaListaDeProductos() {
        when(productoRepository.findAll()).thenReturn(List.of(productoEjemplo));

        List<ProductoResponse> resultado = productoService.findAll();

        assertEquals(1, resultado.size());
        assertEquals("Notebook", resultado.get(0).nombre());
    }

    @Test
    @DisplayName("findById retorna producto existente")
    void findById_retornaProductoExistente() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoEjemplo));

        ProductoResponse resultado = productoService.findById(1L);

        assertEquals(1L, resultado.id());
        assertEquals("Notebook", resultado.nombre());
    }

    @Test
    @DisplayName("findById lanza excepcion si no existe")
    void findById_lanzaExcepcionSiNoExiste() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> productoService.findById(99L));
    }

    @Test
    @DisplayName("crear guarda y retorna el producto")
    void crear_guardaYRetornaProducto() {
        ProductoRequest request = new ProductoRequest(
                "Notebook", "Laptop", 1500.0, 10, 1L);

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoriaEjemplo));
        when(productoRepository.save(any())).thenReturn(productoEjemplo);

        ProductoResponse resultado = productoService.crear(request);

        assertEquals("Notebook", resultado.nombre());
        verify(productoRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("crear lanza excepcion si la categoria no existe")
    void crear_lanzaExcepcionSiCategoriaNoExiste() {
        ProductoRequest request = new ProductoRequest(
                "Notebook", "Laptop", 1500.0, 10, 99L);

        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> productoService.crear(request));
    }

    @Test
    @DisplayName("eliminar borra el producto existente")
    void eliminar_borraProductoExistente() {
        when(productoRepository.existsById(1L)).thenReturn(true);

        productoService.eliminar(1L);

        verify(productoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("eliminar lanza excepcion si no existe")
    void eliminar_lanzaExcepcionSiNoExiste() {
        when(productoRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> productoService.eliminar(99L));
    }
}