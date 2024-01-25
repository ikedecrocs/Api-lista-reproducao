package com.listas.apilistasreproducao.dto;

import com.listas.apilistasreproducao.model.ListaReproducao;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RespostaAdicionarListaReproducao {

    private String url;
    private ListaReproducao listaReproducao;
}
