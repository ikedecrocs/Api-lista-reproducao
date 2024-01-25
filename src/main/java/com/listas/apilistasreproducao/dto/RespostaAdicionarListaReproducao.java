package com.listas.apilistasreproducao.dto;

import com.listas.apilistasreproducao.model.ListaReproducao;
import lombok.*;

// DTO para a resposta do cadastro de uma nova lista de reprodução, retornando a URL deste novo registro e suas dados.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RespostaAdicionarListaReproducao {

    private String url;
    private ListaReproducao listaReproducao;
}
