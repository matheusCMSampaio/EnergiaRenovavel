package br.com.fiap.EnergiaRenovavel.controller;

import br.com.fiap.EnergiaRenovavel.model.LocalMonitoramento;
import br.com.fiap.EnergiaRenovavel.repository.LocalMonitoramentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locais")
public class LocalMonitoramentoResource {

    @Autowired
    private LocalMonitoramentoRepository localMonitoramentoRepository;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody LocalMonitoramento localMonitoramento) {
        LocalMonitoramento savedLocal = localMonitoramentoRepository.save(localMonitoramento);
        return ResponseEntity.status(HttpStatus.CREATED).body("Local de monitoramento registrado com sucesso!");
    }

    @GetMapping("/all")
    public List<LocalMonitoramento> findAll() {
        return localMonitoramentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<LocalMonitoramento> localMonitoramento = localMonitoramentoRepository.findById(id);
        if (localMonitoramento.isPresent()) {
            return ResponseEntity.ok(localMonitoramento.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Local de monitoramento não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean exists = localMonitoramentoRepository.existsById(id);
        if (exists) {
            localMonitoramentoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Local de monitoramento deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Local de monitoramento não encontrado.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody LocalMonitoramento localMonitoramento) {
        boolean exists = localMonitoramentoRepository.existsById(id);
        if (exists) {
            localMonitoramento.setId(id);
            LocalMonitoramento updatedLocal = localMonitoramentoRepository.save(localMonitoramento);
            return ResponseEntity.ok(updatedLocal);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Local de monitoramento não encontrado.");
        }
    }
}
