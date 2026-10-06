package proyecto.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final String serverPort;
    private final String serverFramework;
    private final String serverContextPath;
    private final String loggingLevel;
    private final String loggingOutput;
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

    public HelloController(
            @Value("${project.configuration.server.port}") String serverPort,
            @Value("${project.configuration.server.framework}") String serverFramework,
            @Value("${project.configuration.server.context-path}") String serverContextPath,
            @Value("${project.configuration.logging.level}") String loggingLevel,
            @Value("${project.configuration.logging.file}") String loggingOutput,
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
        this.serverPort = serverPort;
        this.serverFramework = serverFramework;
        this.serverContextPath = serverContextPath;
        this.loggingLevel = loggingLevel;
        this.loggingOutput = loggingOutput;
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

    @GetMapping(value = "/home", produces = MediaType.TEXT_HTML_VALUE)
    public String home(
            @RequestParam(value = "name", defaultValue = "world") String name,
            @RequestParam(value = "lang", defaultValue = "es") String lang) {

        String greeting = HtmlUtils.htmlEscape(switch (lang.toLowerCase()) {
            case "en":
                yield String.format("Hello, %s", name);
            case "pt":
                yield String.format("Olá, %s", name);
            case "es":
            default:
                yield String.format("Hola, %s", name);
        });

        return """
                <!doctype html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Informacion del proyecto</title>
                    <style>
                        body { margin: 0; padding: 40px 20px; background: #f7f7f7; color: #1d1b20; font: 16px Roboto, Arial, sans-serif; }
                        main { max-width: 900px; margin: 0 auto; }
                        h1 { margin: 0 0 24px; font-size: 28px; font-weight: 500; }
                        .grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 16px; }
                        section { padding: 20px; border: none; border-radius: 16px; background: #fff; }
                        h2 { margin: 0 0 16px; font-size: 18px; font-weight: 500; }
                        dl { margin: 0; }
                        dl div { display: flex; justify-content: space-between; gap: 16px; padding: 10px 0; border-top: 1px solid #eeeeef; }
                        dt { color: #5f5e62; }
                        dd { margin: 0; text-align: right; font-weight: 400; }
                    </style>
                </head>
                <body>
                    <main>
                        <h1>%s</h1>
                        <div class="grid">
                            <section><h2>1. Servidor</h2><dl>
                                <div><dt>Puerto</dt><dd>%s</dd></div>
                                <div><dt>Framework</dt><dd>%s</dd></div>
                                <div><dt>Contexto</dt><dd>%s</dd></div>
                            </dl></section>
                            <section><h2>2. Logging</h2><dl>
                                <div><dt>Nivel</dt><dd>%s</dd></div>
                                <div><dt>Salida</dt><dd>%s</dd></div>
                                <div><dt>Formato</dt><dd>%s</dd></div>
                            </dl></section>
                            <section><h2>3. Base de datos</h2><dl>
                                <div><dt>Motor</dt><dd>%s</dd></div>
                                <div><dt>Modo</dt><dd>%s</dd></div>
                                <div><dt>Driver</dt><dd>%s</dd></div>
                            </dl></section>
                            <section><h2>4. Seguridad</h2><dl>
                                <div><dt>Estado</dt><dd>%s</dd></div>
                                <div><dt>Credenciales</dt><dd>%s</dd></div>
                                <div><dt>Autenticacion</dt><dd>%s</dd></div>
                            </dl></section>
                            <section><h2>5. Actuador</h2><dl>
                                <div><dt>Estado</dt><dd>%s</dd></div>
                                <div><dt>Detalles</dt><dd>%s</dd></div>
                                <div><dt>Endpoint</dt><dd>%s</dd></div>
                            </dl></section>
                        </div>
                    </main>
                </body>
                </html>
                """.formatted(
                greeting,
                escape(serverPort), escape(serverFramework), escape(serverContextPath),
                escape(loggingLevel), escape(loggingOutput), escape(loggingFormat),
                escape(databaseEngine), escape(databaseMode), escape(databaseDriver),
                escape(securityStatus), escape(securityCredentials), escape(securityAuthentication),
                escape(actuatorStatus), escape(actuatorDetails), escape(actuatorEndpoint));
    }

    private String escape(String value) {
        return HtmlUtils.htmlEscape(value);
    }
}
