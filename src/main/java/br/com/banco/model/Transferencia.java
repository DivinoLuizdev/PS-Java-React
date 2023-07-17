package br.com.banco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Entity
@Data
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O campo data_transferencia é obrigatório")
    @Column(name = "data_transferencia", nullable = false)
    private LocalDateTime dataTransferencia;

    @NotNull(message = "O campo valor é obrigatório")
    @Column(nullable = false, precision = 20, scale = 2)
    private BigDecimal valor;

    @NotBlank(message = "O campo tipo é obrigatório")
    @Size(max = 15, message = "O campo tipo deve ter no máximo 15 caracteres")
    @Column(nullable = false)
    private String tipo;

    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;

    @NotNull(message = "O campo conta é obrigatório")
    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;
}
