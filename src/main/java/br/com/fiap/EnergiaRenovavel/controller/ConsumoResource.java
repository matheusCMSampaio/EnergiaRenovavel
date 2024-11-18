package br.com.fiap.EnergiaRenovavel.controller;

import br.com.fiap.EnergiaRenovavel.model.ConsumoEnergia;
import br.com.fiap.EnergiaRenovavel.repository.ConsumoEnergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consumos")
public class ConsumoResource {
    @Autowired
    private ConsumoEnergiaRepository consumoEnergiaRepository;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ConsumoEnergia consumoEnergia) {
        ConsumoEnergia savedConsumo = consumoEnergiaRepository.save(consumoEnergia);
        return ResponseEntity.status(HttpStatus.CREATED).body("Consumo de energia registrado com sucesso!");
    }

    @GetMapping("/all")
    public List<ConsumoEnergia> findAll() {
        return consumoEnergiaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<ConsumoEnergia> consumoEnergia = consumoEnergiaRepository.findById(id);
        if (consumoEnergia.isPresent()) {
            return ResponseEntity.ok(consumoEnergia.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Consumo de energia não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean exists = consumoEnergiaRepository.existsById(id);
        if (exists) {
            consumoEnergiaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Consumo de energia deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consumo de energia não encontrado.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ConsumoEnergia consumoEnergia) {
        boolean exists = consumoEnergiaRepository.existsById(id);
        if (exists) {
            consumoEnergia.setId(id);
            ConsumoEnergia updatedConsumo = consumoEnergiaRepository.save(consumoEnergia);
            return ResponseEntity.ok(updatedConsumo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consumo de energia não encontrado.");
        }
    }
}
