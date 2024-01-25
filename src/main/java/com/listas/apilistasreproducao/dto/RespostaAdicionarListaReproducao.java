package com.listas.apilistasreproducao.dto;

import com.listas.apilistasreproducao.model.ListaReproducao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RespostaAdicionarListaReproducao {

    private String url;
    private ListaReproducao listaReproducao;
}
