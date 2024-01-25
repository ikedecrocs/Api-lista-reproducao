package com.listas.apilistasreproducao.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Musica {

    private String titulo;
    private String artista;
    private String album;
    private String ano;
    private String genero;

}
