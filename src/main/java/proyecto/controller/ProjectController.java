package proyecto.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto.config.AppInfoProperties;

@RestController
public class ProjectController {

    private final String name;
    private final String description;
    private final String version;
    private final String developerName;
    private final String developerEmail;
    private final String developerRole;
    private final String serverPort;
    private final String serverFramework;
    private final String serverContextPath;
    private final String loggingLevel;
    private final String loggingFile;
    private final String loggingFormat;
    private final String databaseEngine;
    private final String databaseMode;
    private final String databaseDriver;
    private final String securityStatus;
    private final String securityCredentials;
    private final String securityAuthentication;
    private final String actuatorStatus;
    private final String actuatorDetails;
    private final String actuatorEndpoint;

    public ProjectController(
            AppInfoProperties appInfo,
            @Value("${project.configuration.server.port}") String serverPort,
            @Value("${project.configuration.server.framework}") String serverFramework,
            @Value("${project.configuration.server.context-path}") String serverContextPath,
            @Value("${project.configuration.logging.level}") String loggingLevel,
            @Value("${project.configuration.logging.file}") String loggingFile,
            @Value("${project.configuration.logging.format}") String loggingFormat,
            @Value("${project.configuration.database.engine}") String databaseEngine,
            @Value("${project.configuration.database.mode}") String databaseMode,
            @Value("${project.configuration.database.driver}") String databaseDriver,
            @Value("${project.configuration.security.status}") String securityStatus,
            @Value("${project.configuration.security.credentials}") String securityCredentials,
            @Value("${project.configuration.security.authentication}") String securityAuthentication,
            @Value("${project.configuration.actuator.status}") String actuatorStatus,
            @Value("${project.configuration.actuator.details}") String actuatorDetails,
            @Value("${project.configuration.actuator.endpoint}") String actuatorEndpoint) {
        this.name = appInfo.getName();
        this.description = appInfo.getDescription();
        this.version = appInfo.getVersion();
        this.developerName = appInfo.getDeveloper().getName();
        this.developerEmail = appInfo.getDeveloper().getEmail();
        this.developerRole = appInfo.getDeveloper().getRole();
        this.serverPort = serverPort;
        this.serverFramework = serverFramework;
        this.serverContextPath = serverContextPath;
        this.loggingLevel = loggingLevel;
        this.loggingFile = loggingFile;
        this.loggingFormat = loggingFormat;
        this.databaseEngine = databaseEngine;
        this.databaseMode = databaseMode;
        this.databaseDriver = databaseDriver;
        this.securityStatus = securityStatus;
        this.securityCredentials = securityCredentials;
        this.securityAuthentication = securityAuthentication;
        this.actuatorStatus = actuatorStatus;
        this.actuatorDetails = actuatorDetails;
        this.actuatorEndpoint = actuatorEndpoint;
    }

    @GetMapping("/proyecto")
    public String projectInfo() {
        return "Proyecto: " + name + "\n"
                + "Descripcion: " + description + "\n"
                + "Version: " + version + "\n"
                + "Desarrollador: " + developerName + "\n"
                + "Correo: " + developerEmail + "\n"
                + "Rol: " + developerRole
                + "\n\n1. Servidor:\n"
                + "Puerto: " + serverPort + "\n"
                + "Framework: " + serverFramework + "\n"
                + "Context path: " + serverContextPath
                + "\n\n2. Logging:\n"
                + "Nivel: " + loggingLevel + "\n"
                + "Salida: " + loggingFile + "\n"
                + "Formato: " + loggingFormat
                + "\n\n3. Base de datos:\n"
                + "Motor: " + databaseEngine + "\n"
                + "Modo: " + databaseMode + "\n"
                + "Driver: " + databaseDriver
                + "\n\n4. Seguridad:\n"
                + "Estado: " + securityStatus + "\n"
                + "Credenciales: " + securityCredentials + "\n"
                + "Autenticacion: " + securityAuthentication
                + "\n\n5. Actuador:\n"
                + "Estado: " + actuatorStatus + "\n"
                + "Detalles: " + actuatorDetails + "\n"
                + "Endpoint: " + actuatorEndpoint;
    }
}
