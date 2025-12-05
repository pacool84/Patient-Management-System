# Patient Service

Proyecto demo de Spring Boot que gestiona información simple de pacientes.

Descripción
- Aplicación Spring Boot (Java + Maven) con una capa de modelo, repositorio y servicio.
- Ideal para comenzar a documentar y preparar mejoras: endpoints REST, pruebas, persistencia y UI.

Estructura principal
- src/main/java/com/pm/patient_service: código fuente principal (clase de arranque: `PatientServiceApplication`).
- src/main/resources: recursos (configuración, datos iniciales `data.sql`, templates, static).

Requisitos
- JDK 17+ instalado (ajusta según el `pom.xml`).
- Maven (puedes usar el wrapper incluido `./mvnw`).

Cómo ejecutar (modo desarrollo)

1) Construir y ejecutar con el wrapper de Maven:

```bash
# en macOS / Linux
./mvnw clean package
./mvnw spring-boot:run
```

2) Ejecutar el jar generado:

```bash
./mvnw clean package
java -jar target/*.jar
```

3) Ejecutar con logging de debug (muestra el "condition evaluation report"):

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments=--debug
```

Comandos de tests

```bash
./mvnw test
```

Configuración recomendada de H2 (ejemplo)

A continuación hay un ejemplo de propiedades para usar H2 en memoria. Cada línea está comentada con su propósito para que puedas copiar/pegar en `src/main/resources/application.properties` o adaptarla.

# --- Ejemplo H2 (copia como están las claves, los comentarios explican cada línea) ---

# URL de conexión para H2 en memoria. `patientdb` es el nombre de la BD en memoria.
spring.datasource.url=jdbc:h2:mem:patientdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE

# Driver JDBC a usar. Para H2 es org.h2.Driver
spring.datasource.driverClassName=org.h2.Driver

# Usuario por defecto para H2 (sa es el usuario por defecto)
spring.datasource.username=sa

# Contraseña por defecto para H2 (vacía en este ejemplo)
spring.datasource.password=

# Dialecto de Hibernate para H2: le indica a JPA cómo generar SQL específico
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Habilita la consola web de H2 (útil en desarrollo)
spring.h2.console.enabled=true

# Ruta de la consola H2 (opcional)
spring.h2.console.path=/h2-console

# Controla el comportamiento de creación/actualización del esquema (dev: update)
spring.jpa.hibernate.ddl-auto=update

# --- Fin del ejemplo H2 ---

Nota: los comentarios anteriores están aquí en el README para referencia; si quieres que los agregue directamente a `application.properties` dímelo y los incorporo como comentarios en ese archivo.

Por qué aparece el error "Failed to configure a DataSource: 'url' attribute is not specified"
- Spring Boot intenta autoconfigurar una fuente de datos (DataSource). Si no encuentra una configuración (p.ej. `spring.datasource.url`) ni una base de datos embebida en el classpath, falla.
- Soluciones rápidas:
  - Agregar la dependencia de H2 en el `pom.xml` (scope runtime) y configurar las propiedades de H2 (ver ejemplo arriba).
  - O bien definir `spring.datasource.url`, `username`, `password` apuntando a tu base de datos real (Postgres, MySQL, etc.) y añadir la dependencia del driver JDBC correspondiente.

Dependencias útiles (Maven)
- Para H2 en `pom.xml`:

```xml
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
```

- Para habilitar la consola H2 en desarrollo no necesitas nada más, con la dependencia y las propiedades anteriores basta.

Sugerencias y próximos pasos
- Añadir un `.gitignore` que incluya `*.DS_Store` para macOS (evita subir archivos invisibles del sistema).
- Añadir ejemplos de endpoints en el README (curl/postman) una vez expongas los controladores REST.
- (Opcional) Añadir un script `run-dev.sh` o `Makefile` para comandos comunes.

Contacto / Autor
- Proyecto inicial generado localmente por el autor. Si quieres que haga cambios (agregar `.gitignore`, añadir comentarios directamente en `application.properties`, o arreglar la excepción del DataSource en el código), dime cuál y lo hago.

Licencia
- A definir (añade una licencia si quieres compartir este código públicamente).

