package proyecto.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeUsesDefaultNameAndLanguage() throws Exception {
        mockMvc.perform(get("/home").header("Authorization", basicAuth()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(containsString("Hola, world")))
                .andExpect(content().string(containsString("<h2>1. Servidor</h2>")))
                .andExpect(content().string(containsString("<dt>Puerto</dt><dd>8081</dd>")))
                .andExpect(content().string(containsString("<h2>2. Logging</h2>")))
                .andExpect(content().string(containsString("<dt>Formato</dt><dd>Texto</dd>")))
                .andExpect(content().string(containsString("<h2>3. Base de datos</h2>")))
                .andExpect(content().string(containsString("<dt>Driver</dt><dd>org.h2.Driver</dd>")))
                .andExpect(content().string(containsString("<h2>4. Seguridad</h2>")))
                .andExpect(content().string(containsString("<dt>Estado</dt><dd>Autenticacion activa</dd>")))
                .andExpect(content().string(containsString("<dt>Credenciales</dt><dd>Usuario: jef; rol: USER</dd>")))
                .andExpect(content().string(containsString("<dt>Autenticacion</dt><dd>HTTP Basic</dd>")))
                .andExpect(content().string(containsString("<h2>5. Actuador</h2>")))
                .andExpect(content().string(containsString("<dt>Endpoint</dt><dd>/actuator/health</dd>")));
    }

    @Test
    void homeSupportsEnglish() throws Exception {
        mockMvc.perform(get("/home").param("name", "Ana").param("lang", "en").header("Authorization", basicAuth()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Ana")));
    }

    @Test
    void homeSupportsPortuguese() throws Exception {
        mockMvc.perform(get("/home").param("name", "Ana").param("lang", "pt").header("Authorization", basicAuth()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ol&aacute;, Ana")));
    }

    @Test
    void homeUsesSpanishForUnknownLanguage() throws Exception {
        mockMvc.perform(get("/home").param("name", "Ana").param("lang", "fr").header("Authorization", basicAuth()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hola, Ana")));
    }

    @Test
    void homeAcceptsUppercaseLanguage() throws Exception {
        mockMvc.perform(get("/home").param("name", "Ana").param("lang", "EN").header("Authorization", basicAuth()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Ana")));
    }

    private String basicAuth() {
        return "Basic " + java.util.Base64.getEncoder().encodeToString("jef:jef123".getBytes());
    }
}
