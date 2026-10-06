package proyecto.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void protectsHomeWithoutCredentials() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void acceptsConfiguredTestUser() throws Exception {
        String credentials = Base64.getEncoder().encodeToString(
                "jef:jef123".getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(get("/home").header("Authorization", "Basic " + credentials))
                .andExpect(status().isOk());
    }

    @Test
    void leavesHealthEndpointPublic() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk());
    }

    @Test
    void exposesApplicationInfoWithoutCredentials() throws Exception {
        mockMvc.perform(get("/api/info"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/info"))
                .andExpect(status().isOk());
    }
}
