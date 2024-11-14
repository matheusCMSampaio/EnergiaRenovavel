package br.com.fiap.EnergiaRenovavel.controller;

import br.com.fiap.EnergiaRenovavel.model.Usuario;
import br.com.fiap.EnergiaRenovavel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class Login {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/Login")
    public String login(){
        return "Login";
    }

    @GetMapping("/index")
    public ModelAndView index() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        System.out.println("Email autenticado: " + email);

        ModelAndView mv = new ModelAndView("/index");

        // Busca o usuário pelo email
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isPresent()) {
            // Adiciona o nome do usuário no ModelAndView
            mv.addObject("nome", usuario.get().getNome());
            System.out.println("Nome do usuário: " + usuario.get().getNome());
        } else {
            System.out.println("Usuário não encontrado com o email: " + email);

            mv.addObject("mensagemErro", "Usuário não encontrado.");
        }

        return mv;
    }




}
