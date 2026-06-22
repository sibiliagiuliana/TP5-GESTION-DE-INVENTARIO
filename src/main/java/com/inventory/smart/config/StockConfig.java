package com.inventory.smart.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuración de umbrales de alerta de stock.
 *
 * <p>Lee automáticamente los valores definidos en application.yml
 * bajo el prefijo "inventario.stock".</p>
 *
 * <p>Ejemplo en application.yml:
 * <pre>
 * inventario:
 *   stock:
 *     minimo: 10
 *     critico: 3
 * </pre>
 * </p>
 *
 * @param minimo  stock mínimo, por debajo genera alerta BAJO
 * @param critico stock crítico, por debajo genera alerta CRITICO
 * @author Grupo DevGirls
 * @since 1.0
 */
@ConfigurationProperties(prefix = "inventario.stock")
public record StockConfig(int minimo, int critico) {}
