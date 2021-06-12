package ru.karinkicks.tests.authorization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("Test")
public class AuthorizationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void securityAccessAllowedTest() throws Exception {
        String jsonRequest = "{\n" +
                "\t\"username\": \"admin\",\n" +
                "\t\"password\": \"admin\"\n" +
                "}";
        MvcResult result = mockMvc.perform(post("/auth")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();
        String token = result.getResponse().getContentAsString();
        token = token.replace("{\"token\":\"", "").replace("\"}", "");

        mockMvc.perform(get("/api/v1/orders").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void getMockCartSecurityTest() throws Exception {
        mockMvc.perform(get("/api/v1/cart"))
                .andExpect(status().isOk());
    }
}
