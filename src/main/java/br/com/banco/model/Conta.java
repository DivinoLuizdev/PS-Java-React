package br.com.banco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Integer id;

    @NotBlank(message = "O campo nome_responsavel é obrigatório")
    @Size(max = 50, message = "O campo nome_responsavel deve ter no máximo 50 caracteres")
    @Column(name = "nome_responsavel", nullable = false)
    private String nomeResponsavel;
}
