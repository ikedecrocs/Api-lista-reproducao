package com.listas.apilistasreproducao.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

// Model para a lista de reprodução
@Table
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "nome")
public class ListaReproducao {

    @Id
    private String nome;
    private String descricao;

    @ElementCollection
    private List<Musica> musicas;

}
