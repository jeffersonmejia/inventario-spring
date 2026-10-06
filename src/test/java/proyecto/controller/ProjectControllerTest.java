package proyecto.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import proyecto.config.AppInfoProperties;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
@EnableConfigurationProperties(AppInfoProperties.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsProjectAndConfigurationAsPlainText() throws Exception {
        mockMvc.perform(get("/proyecto").header("Authorization", basicAuth()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Proyecto: Gestor de Inventario")))
                .andExpect(content().string(containsString("Desarrollador: Jefferson")))
                .andExpect(content().string(containsString("1. Servidor:\nPuerto: 8081\nFramework: Spring Boot\nContext path: /")))
                .andExpect(content().string(containsString("2. Logging:\nNivel: INFO\nSalida: Consola\nFormato: Texto")))
                .andExpect(content().string(containsString("3. Base de datos:\nMotor: H2\nModo: Base de datos embebida\nDriver: org.h2.Driver")))
                .andExpect(content().string(containsString("4. Seguridad:\nEstado: Autenticacion activa\nCredenciales: Usuario: jef; rol: USER\nAutenticacion: HTTP Basic")))
                .andExpect(content().string(containsString("5. Actuador:\nEstado: Habilitado\nDetalles: Endpoints HTTP: health, info, configprops\nEndpoint: /actuator/health")));
    }

    private String basicAuth() {
        return "Basic " + java.util.Base64.getEncoder().encodeToString("jef:jef123".getBytes());
    }
}
