package br.com.fiap.EnergiaRenovavel.controller;

import br.com.fiap.EnergiaRenovavel.model.Usuario;
import br.com.fiap.EnergiaRenovavel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public List<Usuario> findAll() {
        return user.findAll();
    }
}
