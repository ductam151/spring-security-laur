package dev.ducku.securitye21_e3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Securitye21E3ApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser
    @DisplayName("expectStatusForbiddenIfNotHaveAuthority")
    void test1() throws Exception {
        mockMvc.perform(post("/hello")
                        .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(authorities = "read")
    @DisplayName("expectStatusOkIfHaveAuthority")
    void test2() throws Exception {
        mockMvc.perform(post("/hello")
                .with(csrf()))
                .andExpect(status().isOk());
    }

}
