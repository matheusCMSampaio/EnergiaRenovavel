package br.com.fiap.EnergiaRenovavel.repository;

import br.com.fiap.EnergiaRenovavel.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
