package com.inventory.smart.model;

/**
 * Niveles posibles de alerta de stock de un producto.
 *
 * @author Grupo DevGirls
 * @since 1.0
 */
public enum NivelAlerta {

    /** El stock está en niveles normales. */
    NORMAL,

    /** El stock bajó del mínimo configurado. */
    BAJO,

    /** El stock bajó del umbral crítico configurado. */
    CRITICO
}

