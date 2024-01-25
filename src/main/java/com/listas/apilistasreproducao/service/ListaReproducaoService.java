package com.listas.apilistasreproducao.service;

import com.listas.apilistasreproducao.dto.RespostaAdicionarListaReproducao;
import com.listas.apilistasreproducao.model.ListaReproducao;
import com.listas.apilistasreproducao.repository.ListaReproducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListaReproducaoService {

    @Autowired
    ListaReproducaoRepository listaReproducaoRepository;

    public List<ListaReproducao> listarListasReproducao() {
        List<ListaReproducao> listasReproducao = new ArrayList<>();
        listaReproducaoRepository.findAll().forEach(listasReproducao::add);

        return listasReproducao;
    }

    public Optional<ListaReproducao> buscarListaReproducao(String nome) {
        Optional<ListaReproducao> listaReproducao = listaReproducaoRepository.findById(nome);

        if (listaReproducao.isEmpty()) {
            throw new IllegalArgumentException("Lista não encontrada");
        }

        return listaReproducao;
    }

    public RespostaAdicionarListaReproducao adicionarListaReproducao(ListaReproducao listaReproducao) {
        if (listaReproducao.getNome() == null) {
            throw new IllegalArgumentException("Lista inválida");
        }

        RespostaAdicionarListaReproducao respostaAdicionarListaReproducao = new RespostaAdicionarListaReproducao();
        ListaReproducao dadosListaReproducao = listaReproducaoRepository.save(listaReproducao);
        respostaAdicionarListaReproducao.setListaReproducao(dadosListaReproducao);
        respostaAdicionarListaReproducao.setUrl("/lists/" + respostaAdicionarListaReproducao.getListaReproducao().getNome());

        return respostaAdicionarListaReproducao;
    }

    public void excluirListaReproducao(String nome) {
        Optional<ListaReproducao> buscaListaReproducao = buscarListaReproducao(nome);

        if (buscaListaReproducao.isEmpty()) {
            throw new IllegalArgumentException("Lista não encontrada");
        }

        listaReproducaoRepository.deleteById(nome);
    }

}
