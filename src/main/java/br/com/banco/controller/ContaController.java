package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.banco.model.Conta;
import br.com.banco.service.ContaService;



@RestController
@RequestMapping("/api/contas")

@CrossOrigin(origins = "*")
public class ContaController {

    private ContaService contaService;

    @Autowired
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
        public ResponseEntity<List<Conta>> buscarTodasContas() {
        List<Conta> contas = contaService.buscarTodasContas();
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{id}") 
    public ResponseEntity<Conta> buscarContaPorId(@PathVariable Integer id) {
        Conta conta = contaService.buscarContaPorId(id);
        if (conta != null) {
            return ResponseEntity.ok(conta);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping   
    public ResponseEntity<Conta> criarConta(@RequestBody Conta conta) {
        Conta novaConta = contaService.criarConta(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @PutMapping("/{id}")   
    public ResponseEntity<Conta> atualizarConta(@PathVariable Integer id, @RequestBody Conta conta) {
        Conta contaAtualizada = contaService.atualizarConta(id, conta);
        if (contaAtualizada != null) {
            return ResponseEntity.ok(contaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Conta> atualizarContaParcialmente(@PathVariable Integer id, @RequestBody Conta conta) {
        Conta contaAtualizada = contaService.atualizarContaParcialmente(id, conta);
        if (contaAtualizada != null) {
            return ResponseEntity.ok(contaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable Integer id) {
        contaService.deletarConta(id);
        return ResponseEntity.noContent().build();
    }

}
