package com.listas.apilistasreproducao.service;

import com.listas.apilistasreproducao.dto.RespostaAdicionarListaReproducao;
import com.listas.apilistasreproducao.model.ListaReproducao;
import com.listas.apilistasreproducao.model.Musica;
import com.listas.apilistasreproducao.repository.ListaReproducaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListaReproducaoServiceTest {

    @Mock
    private ListaReproducaoRepository listaReproducaoRepository;

    @InjectMocks
    private ListaReproducaoService listaReproducaoService;

    @Test
    void ListaReproducaoService_ListarListaReproducao_ReturnsListListasReproducao() {
        //arrange
        List<ListaReproducao> listasListaReproducao = Mockito.mock(List.class);

        //act
        when(listaReproducaoRepository.findAll()).thenReturn(listasListaReproducao);
        List<ListaReproducao> listasSalvas = listaReproducaoService.listarListasReproducao();

        //assert
        Assertions.assertNotNull(listasSalvas);
    }

    @Test
    void ListaReproducaoService_BuscarListaReproducao_ReturnsListaReproducao() {
        //arrange
        Musica musica = Musica.builder()
                .titulo("NDA")
                .artista("Billie Eilish")
                .album("Happier than ever")
                .ano("2021")
                .genero("Pop")
                .build();
        List<Musica> musicas = new ArrayList<>();
        musicas.add(musica);
        ListaReproducao listaReproducao = ListaReproducao.builder()
                .nome("Lista")
                .descricao("Lista de músicas do Spotify")
                .musicas(musicas)
                .build();

        //act
        when(listaReproducaoRepository.findById("Lista")).thenReturn(Optional.ofNullable(listaReproducao));
        Optional<ListaReproducao> respostaSalva = listaReproducaoService.buscarListaReproducao("Lista");

        //assert
        Assertions.assertNotNull(respostaSalva);
    }

    @Test
    void ListaReproducaoService_AdicionarListaReproducao_ReturnsListaReproducao() {
        //arrange
        Musica musica = Musica.builder()
                .titulo("NDA")
                .artista("Billie Eilish")
                .album("Happier than ever")
                .ano("2021")
                .genero("Pop")
                .build();
        List<Musica> musicas = new ArrayList<>();
        musicas.add(musica);
        ListaReproducao listaReproducao = ListaReproducao.builder()
                .nome("Lista")
                .descricao("Lista de músicas do Spotify")
                .musicas(musicas)
                .build();

        //act
        when(listaReproducaoRepository.save(Mockito.any(ListaReproducao.class))).thenReturn(listaReproducao);
        RespostaAdicionarListaReproducao respostaSalva = listaReproducaoService.adicionarListaReproducao(listaReproducao);

        //assert
        Assertions.assertNotNull(respostaSalva);
    }

    @Test
    void ListaReproducaoService_ExcluirListaReproducao_ReturnsNull() {
        //arrange
        Musica musica = Musica.builder()
                .titulo("NDA")
                .artista("Billie Eilish")
                .album("Happier than ever")
                .ano("2021")
                .genero("Pop")
                .build();
        List<Musica> musicas = new ArrayList<>();
        musicas.add(musica);
        ListaReproducao listaReproducao = ListaReproducao.builder()
                .nome("Lista")
                .descricao("Lista de músicas do Spotify")
                .musicas(musicas)
                .build();

        //act
        Mockito.lenient().when(listaReproducaoRepository.findById("Lista")).thenReturn(Optional.ofNullable(listaReproducao));

        //assert
        assertAll(() -> listaReproducaoService.excluirListaReproducao("Lista"));
    }
}