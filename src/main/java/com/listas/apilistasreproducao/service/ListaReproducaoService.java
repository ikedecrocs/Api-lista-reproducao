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

    // Método para listagem completa dos registros de lista de reprodução
    public List<ListaReproducao> listarListasReproducao() {
        List<ListaReproducao> listasReproducao = new ArrayList<>();
        listaReproducaoRepository.findAll().forEach(listasReproducao::add);

        return listasReproducao;
    }

    // Método para busca de uma lista de reprodução por nome. Em caso de falha na busca, uma exceção é gerada.
    public Optional<ListaReproducao> buscarListaReproducao(String nome) {
        Optional<ListaReproducao> listaReproducao = listaReproducaoRepository.findById(nome);

        if (listaReproducao.isEmpty()) {
            throw new IllegalArgumentException("Lista não encontrada");
        }

        return listaReproducao;
    }

    // Método para o cadastro de uma nova lista de reprodução. Em caso de nome inválido, uma exceção é gerada.
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

    // Método para exclusão de uma lista de reprodução pelo seu nome. Caso nenhum registro seja encontrado com este nome,
    // uma exceção é gerada.
    public void excluirListaReproducao(String nome) {
        Optional<ListaReproducao> buscaListaReproducao = buscarListaReproducao(nome);

        if (buscaListaReproducao.isEmpty()) {
            throw new IllegalArgumentException("Lista não encontrada");
        }

        listaReproducaoRepository.deleteById(nome);
    }

}
