package br.com.banco.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Transferencia;
import br.com.banco.service.TransferenciaService;

@RestController
@RequestMapping("/api/transferencias")
@CrossOrigin(origins = "*")
public class TransferenciaController {

    private TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping
    public ResponseEntity<List<Transferencia>> getTransferencias(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> periodoInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> periodoFim,
            @RequestParam(required = false) Optional<String> nomeOperador) {
        List<Transferencia> transferencias = transferenciaService.getTransferenciasByFiltros(periodoInicio, periodoFim,
                nomeOperador);
        return ResponseEntity.ok(transferencias);
    }

    @GetMapping("/{id}")
 
    public ResponseEntity<Transferencia> getTransferenciaById(@PathVariable Integer id) {
        Transferencia transferencia = transferenciaService.getTransferenciaById(id);
        if (transferencia != null) {
            return ResponseEntity.ok(transferencia);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Transferencia> adicionarTransferencia(@RequestBody Transferencia transferencia) {
        Transferencia novaTransferencia = transferenciaService.adicionarTransferencia(transferencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransferencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transferencia> atualizarTransferencia(@PathVariable Integer id,
            @RequestBody Transferencia transferencia) {
        Transferencia transferenciaAtualizada = transferenciaService.atualizarTransferencia(id, transferencia);
        if (transferenciaAtualizada != null) {
            return ResponseEntity.ok(transferenciaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTransferencia(@PathVariable Integer id) {
        transferenciaService.excluirTransferencia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/saldo-total")
    public ResponseEntity<BigDecimal> calcularSaldoTotal() {
        List<Transferencia> transferencias = transferenciaService.getTransferenciasByFiltros(Optional.empty(),
                Optional.empty(), Optional.empty());
        BigDecimal saldoTotal = transferenciaService.calcularSaldoTotal(transferencias);
        return ResponseEntity.ok(saldoTotal);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Transferencia>> getTransferenciasByPeriodoAndNomeOperador(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> periodoInicio,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> periodoFim,
        @RequestParam(required = false) Optional<String> nomeOperador) {
    
        List<Transferencia> transferencias;
    
        if (!nomeOperador.isPresent() && (!periodoInicio.isPresent() || !periodoFim.isPresent())) {
            transferencias = transferenciaService.getTransferenciasByFiltros(periodoInicio, periodoFim, nomeOperador);
        } else if (!nomeOperador.isPresent() && periodoInicio.isPresent() && periodoFim.isPresent()) {
            transferencias = transferenciaService.findByDataTransferenciaBetween(periodoInicio.get(), periodoFim.get());
        } else if (nomeOperador.isPresent() && (!periodoInicio.isPresent() || !periodoFim.isPresent())) {
            transferencias = transferenciaService.findByNomeOperadorTransacao(nomeOperador.get());
        } else {
            transferencias = transferenciaService.findByDataTransferenciaBetweenAndNomeOperadorTransacao(
                periodoInicio.get(), periodoFim.get(), nomeOperador.get());
        }
    
        return ResponseEntity.ok(transferencias);
    }

}
