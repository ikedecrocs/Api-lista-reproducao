package com.listas.apilistasreproducao.controller;

import com.listas.apilistasreproducao.dto.RespostaAdicionarListaReproducao;
import com.listas.apilistasreproducao.model.ListaReproducao;
import com.listas.apilistasreproducao.service.ListaReproducaoService;
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
    ListaReproducaoService listaReproducaoService;

    @GetMapping
    public ResponseEntity<List<ListaReproducao>> listarListasReproducao() {
        try {
            List<ListaReproducao> listasReproducao = listaReproducaoService.listarListasReproducao();

            return new ResponseEntity<>(listasReproducao, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{nome}")
    public ResponseEntity<ListaReproducao> procurarListaReproducao(@PathVariable String nome) {
        Optional<ListaReproducao> listaReproducao = listaReproducaoService.buscarListaReproducao(nome);

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

        RespostaAdicionarListaReproducao RespostaListaReproducao = listaReproducaoService.adicionarListaReproducao(listaReproducao);

        return new ResponseEntity<>(RespostaListaReproducao, HttpStatus.CREATED);
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity excluirListaReproducao(@PathVariable String nome) {
        Optional<ListaReproducao> buscaListaReproducao = listaReproducaoService.buscarListaReproducao(nome);

        if (buscaListaReproducao.isPresent()) {
            listaReproducaoService.excluirListaReproducao(nome);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
