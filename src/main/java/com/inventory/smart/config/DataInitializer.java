package com.inventory.smart.config;

import com.inventory.smart.model.Categoria;
import com.inventory.smart.model.Producto;
import com.inventory.smart.repository.CategoriaRepository;
import com.inventory.smart.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Inicializador de datos de prueba para el sistema de inventario.
 *
 * <p>Se ejecuta automáticamente al iniciar la aplicación y carga
 * categorías y productos de ejemplo para facilitar las pruebas.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@Component
public class DataInitializer implements CommandLineRunner {

    /** Repositorio de categorías para persistir los datos iniciales. */
    private final CategoriaRepository categoriaRepository;

    /** Repositorio de productos para persistir los datos iniciales. */
    private final ProductoRepository productoRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param categoriaRepository repositorio de categorías
     * @param productoRepository  repositorio de productos
     */
    public DataInitializer(CategoriaRepository categoriaRepository,
                           ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    /**
     * Carga los datos iniciales al arrancar la aplicación.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    @Override
    public void run(String... args) {

        // Crear categorías
        Categoria electronica = categoriaRepository.save(
                new Categoria(null, "Electrónicos", "Dispositivos electrónicos y tecnología")
        );
        Categoria alimentos = categoriaRepository.save(
                new Categoria(null, "Alimentos", "Productos alimenticios y bebidas")
        );
        Categoria limpieza = categoriaRepository.save(
                new Categoria(null, "Limpieza", "Productos de limpieza e higiene")
        );

        // Crear productos con distintos niveles de stock
        productoRepository.save(new Producto(null, "Notebook Dell XPS 15",
                "Laptop de alto rendimiento", 1599.99, 25, electronica));

        productoRepository.save(new Producto(null, "Mouse Inalámbrico",
                "Mouse ergonómico sin cable", 29.99, 8, electronica));

        productoRepository.save(new Producto(null, "Arroz Integral 1kg",
                "Arroz integral orgánico", 2.50, 2, alimentos));

        productoRepository.save(new Producto(null, "Detergente 500ml",
                "Detergente concentrado para vajilla", 3.99, 50, limpieza));

        productoRepository.save(new Producto(null, "Auriculares Bluetooth",
                "Auriculares inalámbricos con cancelación de ruido", 89.99, 15, electronica));

        System.out.println("✅ Datos iniciales cargados correctamente");
    }
}