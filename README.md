# 📦 TP Gestión de Inventario Inteligente
**Trabajo Práctico N° 5 — Programación III**
Carrera: Ingeniería en Sistemas de Información

---

## 👤 Integrantes
- Hernández Domínguez Tatiana Ayelen - DNI: 45.563.479
- Ochoa Candela Maribel - DNI: 46.401.137
- Sibilia María Giuliana - DNI: 46.723.876

---

## 📋 Descripción del Proyecto
Microservicio REST desarrollado en Java 21 y Spring Boot para la gestión de inventario de un depósito inteligente. El sistema permite administrar productos y categorías, registrar movimientos de stock (entradas y salidas) y generar alertas automáticas cuando el stock baja de umbrales configurables. Aplica arquitectura en capas estricta, repositorios genéricos con `ConcurrentHashMap`, operaciones thread-safe con `AtomicInteger` y documentación interactiva con Swagger/OpenAPI.

---

## 🛠️ Tecnologías Utilizadas
- Lenguaje: Java 21
- Framework: Spring Boot 3.5.15
- Gestor de Dependencias: Apache Maven
- Almacenamiento: En memoria (ConcurrentHashMap)
- Documentación: Swagger / OpenAPI 3.0
- Testing: JUnit 5 + Mockito
- IDE: Visual Studio Code
- Control de versiones: Git y GitHub

---

## ✅ Funcionalidades

### 🏗️ Arquitectura en Capas
- Separación estricta Controller → Service → Repository
- Inyección de dependencias por constructor (sin @Autowired en campos)
- Manejo centralizado de excepciones con @RestControllerAdvice

### 🔧 Repositorios Genéricos
- Interfaz genérica `IGenericRepository<T, ID>` reutilizable
- Clase abstracta `GenericInMemoryRepository<T, ID>` con ConcurrentHashMap
- Repositorios concretos que extienden la clase abstracta sin duplicar código

### 📦 Gestión de Productos y Categorías
- CRUD completo de productos y categorías
- Búsqueda textual case-insensitive por nombre
- Ordenamiento parametrizado por nombre, precio o stock
- Validaciones con Jakarta Bean Validation (@NotBlank, @Positive, etc.)

### 🔄 Movimientos de Inventario
- Registro de entradas y salidas de stock
- Control de stock con `AtomicInteger` (thread-safe)
- Historial trazable de movimientos por producto
- Error 409 Conflict si el stock es insuficiente

### 🚨 Alertas de Stock
- Detección automática de productos con stock bajo
- Dos niveles de alerta: BAJO y CRITICO
- Umbrales configurables en application.yml

---

## 🌐 Endpoints Principales

### Productos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/productos` | Listar todos los productos |
| `GET` | `/api/productos/{id}` | Obtener producto por ID |
| `POST` | `/api/productos` | Crear un nuevo producto |
| `PUT` | `/api/productos/{id}` | Actualizar un producto |
| `DELETE` | `/api/productos/{id}` | Eliminar un producto |
| `GET` | `/api/productos/buscar?q=` | Buscar por nombre |
| `GET` | `/api/productos/ordenados?campo=&orden=` | Listar ordenados |

### Categorías
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/categorias` | Listar todas las categorías |
| `GET` | `/api/categorias/{id}` | Obtener categoría por ID |
| `POST` | `/api/categorias` | Crear una categoría |
| `PUT` | `/api/categorias/{id}` | Actualizar una categoría |
| `DELETE` | `/api/categorias/{id}` | Eliminar categoría (falla si tiene productos) |

### Movimientos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/movimientos` | Registrar entrada o salida de stock |
| `GET` | `/api/movimientos/producto/{id}` | Historial de movimientos de un producto |

### Alertas
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/alertas/stock-bajo` | Productos con stock bajo o crítico |

---

## 📁 Estructura del Proyecto

```
    src/main/java/com/inventory/smart/
    ├── model/          → entidades de dominio (Producto, Categoria, MovimientoInventario)
    ├── dto/            → objetos de transferencia (Request y Response)
    ├── repository/     → interfaz genérica, clase abstracta e implementaciones
    ├── service/        → lógica de negocio e interfaces de servicio
    ├── controller/     → endpoints REST con documentación Swagger
    ├── exception/      → excepciones personalizadas y handler global
    └── config/         → configuración de umbrales y datos iniciales

```

---

## Cómo ejecutar

```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8081`
Swagger UI en `http://localhost:8081/swagger-ui.html`

---

## Tests

```bash
mvn test
```

---

## 📊 Performance Report
Ver [PERFORMANCE.md](PERFORMANCE.md) para el análisis de complejidad algorítmica Big O y mediciones empíricas con 1k, 10k y 100k registros.
