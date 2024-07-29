package dev.ducku.securitye21;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Securitye21ApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }


    @Test
    @DisplayName("getDemoOk")
    void test1() throws Exception {
        mockMvc.perform(get("/demo")
                        .with(httpBasic("bill","12345")))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("postHelloOk")
    void test2() throws Exception {
        mockMvc.perform(post("/hello")
                        .with(httpBasic("john","12345"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

}
