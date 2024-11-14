package br.com.fiap.EnergiaRenovavel.repository;

import br.com.fiap.EnergiaRenovavel.model.ConsumoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumoEnergiaRepository extends JpaRepository<ConsumoEnergia, Long> {
}
