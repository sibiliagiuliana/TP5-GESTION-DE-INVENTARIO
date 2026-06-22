package com.inventory.smart.service;

import com.inventory.smart.config.StockConfig;
import com.inventory.smart.dto.AlertaStockResponse;
import com.inventory.smart.model.NivelAlerta;
import com.inventory.smart.model.Producto;
import com.inventory.smart.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de alertas de stock.
 *
 * <p>Evalúa el nivel de alerta de cada producto comparando
 * su stock actual con los umbrales configurados en application.yml.</p>
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
@Service
public class AlertaServiceImpl implements AlertaService {

    /** Repositorio de productos para consultar el stock actual. */
    private final ProductoRepository productoRepository;

    /** Configuración de umbrales de stock leída desde application.yml. */
    private final StockConfig stockConfig;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param productoRepository repositorio de productos
     * @param stockConfig        configuración de umbrales de stock
     */
    public AlertaServiceImpl(ProductoRepository productoRepository,
                              StockConfig stockConfig) {
        this.productoRepository = productoRepository;
        this.stockConfig = stockConfig;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AlertaStockResponse> obtenerAlertasStockBajo() {
        return productoRepository.findAll()
                .stream()
                .filter(p -> p.getStock() < stockConfig.minimo())
                .map(this::construirAlerta)
                .toList();
    }

    /**
     * Construye una respuesta de alerta para un producto dado.
     *
     * @param producto producto a evaluar
     * @return respuesta con el nivel de alerta correspondiente
     */
    private AlertaStockResponse construirAlerta(Producto producto) {
        NivelAlerta nivel = producto.getStock() < stockConfig.critico()
                ? NivelAlerta.CRITICO
                : NivelAlerta.BAJO;

        return new AlertaStockResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getStock(),
                nivel
        );
    }
}
