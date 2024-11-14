package br.com.fiap.EnergiaRenovavel.repository;

import br.com.fiap.EnergiaRenovavel.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
}
