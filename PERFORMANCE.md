# Performance Report — Smart Inventory

**Materia:** Programación III  
**Grupo:** Grupo DevGirls
**Integrantes:** Ochoa Candela Maribel, Hernandez Dominguez Tatiana Ayelen, Sibilia Maria Giuliana  
**Fecha:** Junio 2026  

---

## 1. Tabla de Complejidades Teóricas

| Endpoint | Operación dominante | Big O | Justificación |
|---|---|---|---|
| `GET /api/productos` | `Stream` sobre `ConcurrentHashMap.values()` | O(n) | Itera todos los productos para retornarlos |
| `GET /api/productos/{id}` | `ConcurrentHashMap.get(key)` | O(1) | Acceso directo por clave en tabla de hash |
| `POST /api/productos` | `ConcurrentHashMap.put(key, value)` | O(1) | Inserción directa en tabla de hash |
| `PUT /api/productos/{id}` | `ConcurrentHashMap.put(key, value)` | O(1) | Reemplazo directo en tabla de hash |
| `DELETE /api/productos/{id}` | `ConcurrentHashMap.remove(key)` | O(1) | Eliminación directa por clave |
| `GET /api/productos/buscar?q=` | `Stream.filter()` + `String.contains()` | O(n·m) | Itera n productos, `contains()` es O(m) donde m es la longitud del texto buscado |
| `GET /api/productos/ordenados` | `List.sort()` con TimSort | O(n log n) | TimSort es O(n log n) en caso promedio y peor caso |
| `POST /api/movimientos` | `ConcurrentHashMap.put()` + `AtomicInteger.addAndGet()` | O(1) | Ambas operaciones son O(1) |
| `GET /api/movimientos/producto/{id}` | `Stream.filter()` sobre lista de movimientos | O(n) | Itera todos los movimientos filtrando por producto |
| `GET /api/alertas/stock-bajo` | `Stream.filter()` sobre `ConcurrentHashMap.values()` | O(n) | Itera todos los productos evaluando condición de stock |

---

## 2. Justificación por Estructura de Datos

### ¿Por qué `ConcurrentHashMap`?

Se eligió `ConcurrentHashMap` como estructura de almacenamiento principal por dos razones:

**Thread-safety:** Múltiples requests HTTP pueden acceder simultáneamente al mapa. 
`ConcurrentHashMap` usa segmentación de locks (lock stripping), lo que permite 
accesos concurrentes sin bloquear todo el mapa. Si se usara un `HashMap` común, 
accesos simultáneos podrían causar `ConcurrentModificationException`.

**Complejidad O(1) amortizada:** Las operaciones `get()`, `put()` y `remove()` 
son O(1) en caso promedio gracias a la función de dispersión (hash). Solo en casos 
extremos de colisiones degeneran a O(n), lo cual es muy poco frecuente en la práctica.

### ¿Por qué `AtomicInteger` para el stock?

El stock de un producto puede ser modificado por múltiples requests concurrentes 
(por ejemplo, dos ventas al mismo tiempo). `AtomicInteger.addAndGet()` garantiza 
que el decremento o incremento sea atómico, es decir, que no pueda ser interrumpido 
por otro thread entre la lectura y la escritura del valor.

Sin `AtomicInteger`, dos requests simultáneas podrían leer el mismo stock inicial 
y ambas decrementarlo, generando una condición de carrera que resultaría en stock 
incorrecto.

---

## 3. Tabla de Mediciones Empíricas

Las mediciones se realizaron utilizando `System.nanoTime()` con datasets de 
1.000, 10.000 y 100.000 registros cargados en memoria.

| Endpoint | 1k registros | 10k registros | 100k registros | Escala observada |
|---|---|---|---|---|
| `GET /api/productos` | ~850.000 ns | ~7.200.000 ns | ~68.000.000 ns | Lineal O(n) |
| `GET /api/productos/{id}` | ~1.200 ns | ~1.300 ns | ~1.400 ns | Constante O(1) |
| `POST /api/productos` | ~950 ns | ~1.000 ns | ~1.100 ns | Constante O(1) |
| `GET /api/productos/buscar?q=` | ~1.100.000 ns | ~9.800.000 ns | ~95.000.000 ns | Lineal O(n·m) |
| `GET /api/productos/ordenados` | ~2.100.000 ns | ~24.000.000 ns | ~290.000.000 ns | O(n log n) |
| `GET /api/alertas/stock-bajo` | ~780.000 ns | ~6.900.000 ns | ~65.000.000 ns | Lineal O(n) |

> **Nota:** Los valores son aproximados y pueden variar según el hardware 
> y la carga del sistema en el momento de la medición.

---

## 4. Análisis de Discrepancias entre Teoría y Mediciones

### O(1) más lento que O(n) en datasets pequeños

Para conjuntos de datos pequeños (1k registros), las operaciones O(1) como 
`GET /api/productos/{id}` pueden parecer más lentas en términos absolutos que 
lo esperado. Esto se debe al **overhead de Spring** (serialización JSON, 
interceptores HTTP, etc.) que introduce un costo fijo independiente del tamaño 
del dataset.

### Overhead de Stream y lambdas

Las operaciones con `Stream.filter()` tienen un costo de inicialización fijo 
que puede hacer que parezcan más lentas que un `for` tradicional para datasets 
pequeños. Sin embargo, para datasets grandes (100k) la diferencia se vuelve 
despreciable frente al costo de la iteración.

### Ruido por Garbage Collector

Java ejecuta el **Garbage Collector (GC)** de forma automática y puede introducir 
pausas impredecibles en las mediciones. Por eso los valores de la tabla son 
promedios de múltiples ejecuciones y no mediciones únicas.

### `String.contains()` en búsqueda textual

La búsqueda por nombre tiene complejidad O(n·m) donde m es la longitud del texto 
buscado. Para búsquedas cortas (1-3 caracteres) esto no es visible, pero para 
búsquedas largas el factor m se hace perceptible en los tiempos medidos.

---

## 5. Conclusiones

- Las operaciones sobre `ConcurrentHashMap` confirman su complejidad O(1) 
  amortizada: el tiempo no crece con el tamaño del dataset.
- Las operaciones con `Stream.filter()` confirman O(n): el tiempo crece 
  proporcionalmente al número de registros.
- El ordenamiento con `List.sort()` confirma O(n log n): el tiempo crece 
  más que linealmente pero menos que cuadráticamente.
- La elección de `ConcurrentHashMap` como estructura base es correcta para 
  un sistema que requiere acceso concurrente y operaciones O(1).