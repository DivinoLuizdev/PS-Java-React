package br.com.banco.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;

@Service
public class TransferenciaService {

    private TransferenciaRepository transferenciaRepository;

    @Autowired
    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public void excluirTransferencia(Integer id) {
        transferenciaRepository.deleteById(id);
    }

    public Transferencia salvarTransferencia(Transferencia transferencia) {
        return transferenciaRepository.save(transferencia);
    }

    public Transferencia getTransferenciaById(Integer id) {
        return transferenciaRepository.findById(id).orElse(null);
    }

    public Transferencia adicionarTransferencia(Transferencia transferencia) {
        return transferenciaRepository.save(transferencia);
    }

    public List<Transferencia> getTransferenciasByFiltros(Optional<LocalDateTime> periodoInicio,
            Optional<LocalDateTime> periodoFim, Optional<String> nomeOperador) {
        if (periodoInicio.isPresent() && periodoFim.isPresent() && nomeOperador.isPresent()) {
            return transferenciaRepository.findByDataTransferenciaBetweenAndNomeOperadorTransacao(
                    periodoInicio.get(), periodoFim.get(), nomeOperador.get());
        } else if (periodoInicio.isPresent() && periodoFim.isPresent()) {
            return transferenciaRepository.findByDataTransferenciaBetween(periodoInicio.get(), periodoFim.get());
        } else if (nomeOperador.isPresent()) {
            return transferenciaRepository.findByNomeOperadorTransacao(nomeOperador.get());
        } else {
            return transferenciaRepository.findAll();
        }
    }

    public BigDecimal calcularSaldoTotal(List<Transferencia> transferencias) {
        BigDecimal saldoTotal = BigDecimal.ZERO;
        for (Transferencia transferencia : transferencias) {
            BigDecimal valor = transferencia.getValor();
            saldoTotal = saldoTotal.add(valor);
        }
        return saldoTotal.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calcularSaldoTotalNoPeriodo(List<Transferencia> transferencias,
            Optional<LocalDateTime> periodoInicio, Optional<LocalDateTime> periodoFim) {
        BigDecimal saldoTotal = BigDecimal.ZERO;
        for (Transferencia transferencia : transferencias) {
            LocalDateTime dataTransferencia = transferencia.getDataTransferencia();
            if (isBetween(dataTransferencia, periodoInicio, periodoFim)) {
                BigDecimal valor = transferencia.getValor();
                if ("SAQUE".equals(transferencia.getTipo())) {
                    saldoTotal = saldoTotal.subtract(valor);
                } else {
                    saldoTotal = saldoTotal.add(valor);
                }
            }
        }
        return saldoTotal.setScale(2, RoundingMode.HALF_UP);
    }

    private boolean isBetween(LocalDateTime date, Optional<LocalDateTime> periodStart,
            Optional<LocalDateTime> periodEnd) {
        if (periodStart.isPresent() && periodEnd.isPresent()) {
            return date.isAfter(periodStart.get()) && date.isBefore(periodEnd.get());
        } else if (periodStart.isPresent()) {
            return date.isAfter(periodStart.get());
        } else if (periodEnd.isPresent()) {
            return date.isBefore(periodEnd.get());
        }
        return true;
    }

    public Transferencia atualizarTransferencia(Integer id, Transferencia transferencia) {
        Transferencia transferenciaExistente = transferenciaRepository.findById(id).orElse(null);
        if (transferenciaExistente != null) {
            transferencia.setId(id);
            return transferenciaRepository.save(transferencia);
        }
        return null;
    }

    public List<Transferencia> findByNomeOperadorTransacao(String nomeOperador) {
        return transferenciaRepository.findByNomeOperadorTransacao(nomeOperador);
    }

    public List<Transferencia> findByDataTransferenciaBetween(LocalDateTime periodoInicio, LocalDateTime periodoFim) {
        return transferenciaRepository.findByDataTransferenciaBetween(periodoInicio, periodoFim);
    }

    public List<Transferencia> findByDataTransferenciaBetweenAndNomeOperadorTransacao(
            LocalDateTime periodoInicio, LocalDateTime periodoFim, String nomeOperador) {
        return transferenciaRepository.findByDataTransferenciaBetweenAndNomeOperadorTransacao(
                periodoInicio, periodoFim, nomeOperador);
    }
}
