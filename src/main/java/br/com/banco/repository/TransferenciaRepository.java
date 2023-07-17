package br.com.banco.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {

    List<Transferencia> findByDataTransferenciaBetween(LocalDateTime periodoInicio, LocalDateTime periodoFim);

    List<Transferencia> findByNomeOperadorTransacao(String nomeOperador);

    List<Transferencia> findByDataTransferenciaBetweenAndNomeOperadorTransacao(LocalDateTime periodoInicio, LocalDateTime periodoFim, String nomeOperador);

}
