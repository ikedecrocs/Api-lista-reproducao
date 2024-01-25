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

// Controller para os endpoints relacionados a listas de reprodução (/lists)
@RestController
@RequestMapping("/lists")
public class ListaReproducaoController {

    @Autowired
    ListaReproducaoService listaReproducaoService;

    // Enpoint para a listagem completa das listas de reprodução dentro do sistema.
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<ListaReproducao>> listarListasReproducao() {
        try {
            List<ListaReproducao> listasReproducao = listaReproducaoService.listarListasReproducao();
            return new ResponseEntity<>(listasReproducao, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para a recuperar de um registro pelo seu nome. Em caso de falha na busca pelo nome, um erro 404 será disparado.
    @CrossOrigin
    @GetMapping("/{nome}")
    public ResponseEntity<ListaReproducao> procurarListaReproducao(@PathVariable String nome) {
        try {
            Optional<ListaReproducao> listaReproducao = listaReproducaoService.buscarListaReproducao(nome);
            return new ResponseEntity<>(listaReproducao.get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para cadastro de novas listas de reprodução. Em caso de nome inválido (null), um erro 400 será disparado
    @CrossOrigin
    @PostMapping
    public ResponseEntity adicionarListaReproducao(@RequestBody ListaReproducao listaReproducao) {
        try {
            RespostaAdicionarListaReproducao RespostaListaReproducao = listaReproducaoService.adicionarListaReproducao(listaReproducao);
            return new ResponseEntity<>(RespostaListaReproducao, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para a exclusão de um registro. Em caso de falha na busca pelo nome, um erro 404 será disparado.
    @CrossOrigin
    @DeleteMapping("/{nome}")
    public ResponseEntity excluirListaReproducao(@PathVariable String nome) {
        try {
            listaReproducaoService.excluirListaReproducao(nome);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
