package br.com.fiap.EnergiaRenovavel.controller;

import br.com.fiap.EnergiaRenovavel.model.Dispositivo;
import br.com.fiap.EnergiaRenovavel.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/dispositivo")
public class DispositivoResource {

    @Autowired
    DispositivoRepository dispositivoRepository;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Dispositivo dispositivo) {
        Dispositivo savedDispositivo = dispositivoRepository.save(dispositivo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dispositivo cadastrado com sucesso!");
    }


    @GetMapping(value = "/all")
    public List<Dispositivo> findAll() {
        return dispositivoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Dispositivo> dispositivo = dispositivoRepository.findById(id);
        if (dispositivo.isPresent()) {
            return ResponseEntity.ok(dispositivo.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Dispositivo não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean exists = dispositivoRepository.existsById(id);
        if (exists) {
            dispositivoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Dispositivo deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Dispositivo dispositivo) {
        boolean exists = dispositivoRepository.existsById(id);
        if (exists) {
            dispositivo.setId(id);
            Dispositivo updatedDispositivo = dispositivoRepository.save(dispositivo);
            return ResponseEntity.ok(updatedDispositivo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dispositivo não encontrado.");
        }
    }
}
