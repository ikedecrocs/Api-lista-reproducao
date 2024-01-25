package com.listas.apilistasreproducao.controller;

import com.listas.apilistasreproducao.dto.RespostaAdicionarListaReproducao;
import com.listas.apilistasreproducao.model.ListaReproducao;
import com.listas.apilistasreproducao.repository.ListaReproducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lists")
public class ListaReproducaoController {

    @Autowired
    ListaReproducaoRepository listaReproducaoRepository;

    @GetMapping
    public ResponseEntity<List<ListaReproducao>> listarListasReproducao() {
        try {
            List<ListaReproducao> listasReproducao = new ArrayList<>();
            listaReproducaoRepository.findAll().forEach(listasReproducao::add);

            return new ResponseEntity<>(listasReproducao, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{nome}")
    public ResponseEntity<ListaReproducao> procurarListaReproducao(@PathVariable String nome) {
        Optional<ListaReproducao> listaReproducao = listaReproducaoRepository.findById(nome);

        if (listaReproducao.isPresent()) {
            return new ResponseEntity<>(listaReproducao.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity adicionarListaReproducao(@RequestBody ListaReproducao listaReproducao) {
        if (listaReproducao.getNome() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        RespostaAdicionarListaReproducao respostaAdicionarListaReproducao = new RespostaAdicionarListaReproducao();
        ListaReproducao dadosListaReproducao = listaReproducaoRepository.save(listaReproducao);
        respostaAdicionarListaReproducao.setListaReproducao(dadosListaReproducao);
        respostaAdicionarListaReproducao.setUrl("/lists/" + respostaAdicionarListaReproducao.getListaReproducao().getNome());

        return new ResponseEntity<>(respostaAdicionarListaReproducao, HttpStatus.CREATED);
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity excluirListaReproducao(@PathVariable String nome) {
        Optional<ListaReproducao> buscaListaReproducao = listaReproducaoRepository.findById(nome);

        if (buscaListaReproducao.isPresent()) {
            listaReproducaoRepository.deleteById(nome);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
