# Peluqueria Pelardo - JavaFX JDBC Application

Sistema de gestión de citas para peluquería desarrollado con JavaFX y conexión a base de datos PostgreSQL.

## Características

- Autenticación de usuarios
- Registro de nuevos usuarios
- CRUD de citas con validaciones
- Interfaz gráfica con JavaFX
- Conexión a base de datos PostgreSQL
- Manejo de errores y logging

## Tecnologías

- **Java 25**
- **JavaFX**
- **Maven**
- **PostgreSQL** (jdbc driver)

## Estructura del Proyecto

- `src/main/java/com/example/pelardo/jdbc/`: Código fuente principal
  - `models/`: Modelos de datos (Usuario, CitaTbl)
  - `repositories/`: Acceso a base de datos (LoginRepository, CitasRepository, ConnectionDB)
  - `services/`: Lógica de negocio (LoginServices, CitasService)
  - `screens/`: Interfaces de usuario (LoginScreen, MainScreen, RegisterScreen)
- `src/test/java/com/example/pelardo/jdbc/`: Tests unitarios
- `src/main/resources/db.properties`: Configuración de la base de datos

## Configuración

### Base de Datos

1. Asegúrate de tener PostgreSQL instalado y corriendo
2. Crea la base de datos para la aplicación
3. Configura el archivo `src/main/resources/db.properties` con tus credenciales:

```properties
db.url=jdbc:postgresql://host:port/database
db.username=tu_usuario
db.password=tu_contraseña
```

### Ejecución

**Usando Maven:**

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn javafx:run
```

**Usando IDE (IntelliJ IDEA):**

1. Abre el proyecto en IntelliJ
2. Configura la configuración de ejecución (Run Configuration)
3. Asegúrate de que el módulo FX esté seleccionado
4. Ejecuta la aplicación
