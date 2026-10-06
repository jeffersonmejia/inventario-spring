# Decisiones de metadata y configuración

## `@ConfigurationProperties` en lugar de `@Value`

`AppInfoProperties` agrupa todos los datos de `app.info` en un único objeto tipado. Esto mantiene juntas las propiedades relacionadas, admite binding relajado y permite validar los valores al iniciar la aplicación. En este caso `@Email` valida el correo del desarrollador y `@NotBlank` evita metadata vacía.

## Sincronización de la versión con `pom.xml`

`app.info.version` usa `@project.version@`. El `spring-boot-starter-parent` configura el filtrado Maven de los archivos `application*.properties`, así que Maven sustituye ese token con `${project.version}` durante el procesamiento de recursos. La versión no se copia manualmente en cada perfil. El test del endpoint comprueba el valor resultante.

## Qué ocurre al quitar `@Component`

La clase está registrada como bean mediante `@Component`. Si se elimina esa anotación sin añadir otra forma de registro, el escaneo de componentes no crea `AppInfoProperties`; al iniciar, Spring falla porque no puede inyectar esa dependencia en `InfoController`. Lo comprobé quitando temporalmente la anotación y ejecutando `Proyecto1MavenApplicationTests`; el contexto falla con `No qualifying bean of type 'proyecto.config.AppInfoProperties' available`. La anotación se restauró después de la prueba.

## `/api/info` y `/actuator/info`

`GET /api/v1/info` (también disponible en `/api/info`) es el contrato público y estable de la aplicación. Devuelve metadata propia con DTO, incluidos el entorno y los datos del desarrollador.

`/actuator/info` pertenece a Spring Boot Actuator. Su contenido se construye desde las propiedades `info.*` y se orienta a diagnóstico y operación; no es el contrato de metadata definido por el proyecto. El endpoint `/actuator/configprops` permite explorar las propiedades enlazadas a `@ConfigurationProperties`; requiere autenticación y muestra valores solo a usuarios autenticados.

## Perfiles

`application-dev.properties` identifica el entorno `dev`; `application-prod.properties` identifica `prod`. Ambos heredan los campos comunes y la versión de `application.properties`.

La configuración actual `jef` / `jef123` es de desarrollo/pruebas. No debe reutilizarse como contraseña de producción.
