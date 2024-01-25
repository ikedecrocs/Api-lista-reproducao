package com.listas.apilistasreproducao.repository;

import com.listas.apilistasreproducao.model.ListaReproducao;
import com.listas.apilistasreproducao.model.Musica;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ListaReproducaoRepositoryTest {

    @Autowired
    private ListaReproducaoRepository listaReproducaoRepository;

    @Test
    public void ListaReproducaoRepository_Save_CreateListaReproducao() {
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
        ListaReproducao listaSalva = listaReproducaoRepository.save(listaReproducao);

        //assert
        Assertions.assertNotNull(listaSalva);
        Assertions.assertNotNull(listaSalva.getNome());
    }

    @Test
    public void ListaReproducaoRepository_FindAll_ReturnsListListasReproducao() {
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
        ListaReproducao listaReproducao1 = ListaReproducao.builder()
                .nome("Lista")
                .descricao("Lista de músicas do Spotify")
                .musicas(musicas)
                .build();
        ListaReproducao listaReproducao2 = ListaReproducao.builder()
                .nome("Lista 1")
                .descricao("Lista de músicas do spotify")
                .musicas(musicas)
                .build();

        //act
        listaReproducaoRepository.save(listaReproducao1);
        listaReproducaoRepository.save(listaReproducao2);

        List<ListaReproducao> listaListasReproducao = listaReproducaoRepository.findAll();

        //assert
        Assertions.assertNotNull(listaListasReproducao);
        Assertions.assertEquals(2, listaListasReproducao.size());
    }

    @Test
    public void ListaReproducaoRepository_FindById_ReturnsListaReproducao() {
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
        listaReproducaoRepository.save(listaReproducao);

        Optional<ListaReproducao> listaSalva = listaReproducaoRepository.findById(listaReproducao.getNome());

        //assert
        Assertions.assertTrue(listaSalva.isPresent());
        Assertions.assertEquals(listaReproducao.getNome(), listaSalva.get().getNome());
    }

    @Test
    public void ListaReproducaoRepository_DeleteById_ReturnsNull() {
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
        listaReproducaoRepository.save(listaReproducao);
        listaReproducaoRepository.deleteById(listaReproducao.getNome());
        Optional<ListaReproducao> listaSalva = listaReproducaoRepository.findById(listaReproducao.getNome());

        //assert
        Assertions.assertTrue(listaSalva.isEmpty());
    }
}