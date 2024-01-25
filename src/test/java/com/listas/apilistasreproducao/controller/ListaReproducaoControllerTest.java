package com.listas.apilistasreproducao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.listas.apilistasreproducao.dto.RespostaAdicionarListaReproducao;
import com.listas.apilistasreproducao.model.ListaReproducao;
import com.listas.apilistasreproducao.model.Musica;
import com.listas.apilistasreproducao.repository.ListaReproducaoRepository;
import com.listas.apilistasreproducao.service.ListaReproducaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ListaReproducaoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ListaReproducaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListaReproducaoService listaReproducaoService;

    @MockBean
    private ListaReproducaoRepository listaReproducaoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private ListaReproducao listaReproducao;
    private RespostaAdicionarListaReproducao respostaListaReproducao;

    @BeforeEach
    public void init() {
        Musica musica = Musica.builder()
                .titulo("NDA")
                .artista("Billie Eilish")
                .album("Happier than ever")
                .ano("2021")
                .genero("Pop")
                .build();
        List<Musica> musicas = new ArrayList<>();
        musicas.add(musica);
        listaReproducao = ListaReproducao.builder()
                .nome("Lista")
                .descricao("Lista de músicas do Spotify")
                .musicas(musicas)
                .build();
        respostaListaReproducao = RespostaAdicionarListaReproducao.builder()
                .url("/lists/Lista")
                .listaReproducao(listaReproducao)
                .build();

    }

    @Test
    void ListaReproducaoController_ListarListaReproducao_ReturnOk() throws Exception {
        mockMvc.perform(get("/lists"))
                .andExpect(status().isOk());
    }

    @Test
    void ListaReproducaoController_ProcurarListaReproducao_ReturnOk() throws Exception {
        Mockito.when(listaReproducaoService.buscarListaReproducao("Lista")).thenReturn(Optional.ofNullable(listaReproducao));
        mockMvc.perform(get("/lists/Lista"))
                .andExpect(status().isOk());
    }

    @Test
    void ListaReproducaoController_AdicionarListaReproducao_ReturnCreated() throws Exception {
        mockMvc.perform(post("/lists")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(listaReproducao)))
                .andExpect(status().isCreated());
    }

    @Test
    void ListaReproducaoController_ExcluirListaReproducao_ReturnNoContent() throws Exception {
        Mockito.when(listaReproducaoService.buscarListaReproducao("Lista")).thenReturn(Optional.ofNullable(listaReproducao));
        mockMvc.perform(delete("/lists/Lista"))
                .andExpect(status().isNoContent());
    }

    @Test
    void ListaReproducaoController_AdicionarListaReproducao_ReturnBadRequest() throws Exception {
        Mockito.when(listaReproducaoService.adicionarListaReproducao(listaReproducao)).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(post("/lists")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(listaReproducao)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void ListaReproducaoController_ProcurarListaReproducao_ReturnNotFound() throws Exception {
        Mockito.when(listaReproducaoService.buscarListaReproducao("Lista")).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(get("/lists/Lista"))
                .andExpect(status().isNotFound());
    }

    @Test
    void ListaReproducaoController_ExcluirListaReproducao_ReturnNotFound() throws Exception {
        Mockito.doThrow(IllegalArgumentException.class).when(listaReproducaoService).excluirListaReproducao(anyString());
        mockMvc.perform(delete("/lists/Lista"))
                .andExpect(status().isNotFound());
    }
}