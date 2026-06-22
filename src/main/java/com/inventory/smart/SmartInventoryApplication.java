package com.inventory.smart;

import com.inventory.smart.config.StockConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Clase principal del microservicio de inventario inteligente.
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@SpringBootApplication
@EnableConfigurationProperties(StockConfig.class)
public class SmartInventoryApplication {

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(SmartInventoryApplication.class, args);
    }
}
