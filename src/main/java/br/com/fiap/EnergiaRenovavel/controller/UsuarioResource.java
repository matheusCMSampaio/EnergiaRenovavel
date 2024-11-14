package br.com.fiap.EnergiaRenovavel.controller;

import br.com.fiap.EnergiaRenovavel.model.Usuario;
import br.com.fiap.EnergiaRenovavel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
public class UsuarioResource {

    @Autowired
    UsuarioRepository user;
    @Autowired
    private PasswordEncoder pass;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Usuario usuario) {
        usuario.setSenha(pass.encode(usuario.getSenha()));
        Usuario save = user.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario cadastrado com sucesso!");
    }
    @GetMapping(value = "/all")
    public List<Usuario> findAll() {
        return user.findAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Usuario> usuario = user.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado.");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean exists = user.existsById(id);
        if (exists) {
            user.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        boolean exists = user.existsById(id);
        if (exists) {
            usuario.setIdUsuario(id);
            Usuario updatedUsuario = user.save(usuario);
            return ResponseEntity.ok(updatedUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
    }

    @PostMapping("/usuario")
    public ResponseEntity<?> findByEmail(@RequestBody Usuario usuario) {
        String email = usuario.getEmail();
        Optional<Usuario> usuarioEmail = user.findByEmail(email);

        if (usuarioEmail.isPresent()) {
            if (pass.matches(usuario.getSenha(), usuarioEmail.get().getSenha())) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha incorretos!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

}
