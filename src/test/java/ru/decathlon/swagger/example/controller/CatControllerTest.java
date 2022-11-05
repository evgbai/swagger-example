package ru.decathlon.swagger.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.decathlon.swagger.example.model.Cat;
import ru.decathlon.swagger.example.service.CatService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatController.class)
class CatControllerTest {

    @Autowired
    private MockMvc mock;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private CatService service;

    @Test
    @DisplayName("[GET: /api/v1]")
    void shouldReturnCatList() throws Exception {
        List<Cat> cats = List.of(new Cat(1L, "Gizmo", 3));
        when(service.getCats()).thenReturn(cats);
        mock.perform(get("/api/v1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(mapper.writeValueAsString(cats)));
    }

    @Test
    @DisplayName("[GET: /api/v1/{id}]")
    void shouldReturnOneCatById() throws Exception {
        Cat cat = new Cat(1L, "Gizmo", 3);
        when(service.getCatById(1L)).thenReturn(cat);
        this.mock.perform(get("/api/v1/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", 1).exists())
                .andExpect(content().json(mapper.writeValueAsString(cat)));
    }

    @Test
    @DisplayName("[POST: /api/v1]")
    void shouldReturnCreatedStatusWhenCatAdded() throws Exception {
        Cat cat = new Cat(1L, "Gizmo", 3);
        this.mock.perform(post("/api/v1")
                        .content(mapper.writeValueAsString(cat))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("[DELETE: /api/v1/{id}]")
    void shouldReturnGoneStatusWhenCatDeleted() throws Exception {
        this.mock.perform(delete("/api/v1/{id}", 1))
                .andExpect(status().isNoContent());
    }
}