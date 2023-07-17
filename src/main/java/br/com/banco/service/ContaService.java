package br.com.banco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.Conta;
import br.com.banco.repository.ContaRepository;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public List<Conta> buscarTodasContas() {
        return contaRepository.findAll();
    }

    public Conta buscarContaPorId(Integer id) {
        Optional<Conta> optionalConta = contaRepository.findById(id);
        return optionalConta.orElse(null);
    }
    
    public Conta criarConta(Conta conta) {
        return contaRepository.save(conta);
    }
    
    public Conta atualizarConta(Integer id, Conta conta) {
        Conta contaExistente = buscarContaPorId(id);
        if (contaExistente != null) {
            contaExistente.setNomeResponsavel(conta.getNomeResponsavel());
            return contaRepository.save(contaExistente);
        }
        return null;
    }
    
    public Conta atualizarContaParcialmente(Integer id, Conta conta) {
        Conta contaExistente = buscarContaPorId(id);
        if (contaExistente != null) {
            if (conta.getNomeResponsavel() != null) {
                contaExistente.setNomeResponsavel(conta.getNomeResponsavel());
            }
            return contaRepository.save(contaExistente);
        }
        return null;
    }
    
    public void deletarConta(Integer id) {
        contaRepository.deleteById(id);
    }
    
    

}
