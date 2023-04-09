package com.jaax.restfulapi.controller;

import com.jaax.restfulapi.entity.Local;
import com.jaax.restfulapi.service.LocalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LocalController.class)
class LocalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocalService localService;

    private Local local;

    @BeforeEach
    void setUp() {
         local = Local.builder()
                .id(1L)
                .name("Cinema")
                .floor("Fourth Floor")
                .code("Cin-040-4")
                .build();
    }

    @Test
    public void saveLocal() throws Exception{
        Local postLocal = Local.builder()
                .name("Cinema")
                .floor("Fourth Floor")
                .code("Cin-040-4")
                .build();
        Mockito.when(localService.saveLocal(postLocal)).thenReturn(local);
        mockMvc.perform(post("/saveLocal").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\":\"Cinema\",\n" +
                        "    \"floor\": \"Fourth Floor\",\n" +
                        "    \"code\":\"Cin-040-4\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void findLocalById() throws Exception{
        Mockito.when(localService.findLocalById(1L)).thenReturn(local);
        mockMvc.perform(get("/findLocalById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(local.getName()));
    }

}