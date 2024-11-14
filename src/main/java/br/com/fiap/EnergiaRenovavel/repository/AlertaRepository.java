package br.com.fiap.EnergiaRenovavel.repository;

import br.com.fiap.EnergiaRenovavel.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}
