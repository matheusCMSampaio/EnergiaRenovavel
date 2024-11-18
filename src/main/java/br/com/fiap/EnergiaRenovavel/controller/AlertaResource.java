package br.com.fiap.EnergiaRenovavel.controller;

import br.com.fiap.EnergiaRenovavel.model.Alerta;
import br.com.fiap.EnergiaRenovavel.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alertas")
public class AlertaResource {

    @Autowired
    private AlertaRepository alertaRepository;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Alerta alerta) {
        Alerta savedAlerta = alertaRepository.save(alerta);
        return ResponseEntity.status(HttpStatus.CREATED).body("Alerta registrado com sucesso!");
    }

    @GetMapping("/all")
    public List<Alerta> findAll() {
        return alertaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Alerta> alerta = alertaRepository.findById(id);
        if (alerta.isPresent()) {
            return ResponseEntity.ok(alerta.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Alerta não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean exists = alertaRepository.existsById(id);
        if (exists) {
            alertaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Alerta deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alerta não encontrado.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Alerta alerta) {
        boolean exists = alertaRepository.existsById(id);
        if (exists) {
            alerta.setId(id);
            Alerta updatedAlerta = alertaRepository.save(alerta);
            return ResponseEntity.ok(updatedAlerta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alerta não encontrado.");
        }
    }
}
