package com.example.greatlearning.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


@WebMvcTest
public class IndexControllerTest extends BaseControllerTest{

    @Test
    @DisplayName(value = "Default url should redirect to swagger link")
    public void shouldRedirectToSwagger() throws Exception {
       mockMvc.perform(get("/")).andExpect(redirectedUrl("/swagger-ui.html"));
    }

    @Test
    @DisplayName(value = "Check login form exists")
    public void testLoginForm() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk()).andReturn();
    }


}
