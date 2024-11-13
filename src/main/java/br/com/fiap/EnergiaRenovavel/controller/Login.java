package br.com.fiap.EnergiaRenovavel.controller;

import br.com.fiap.EnergiaRenovavel.model.Usuario;
import br.com.fiap.EnergiaRenovavel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
//    @PostMapping("/Login")
//    public String loginPost() {
//        return "redirect:/Index"; // Redireciona após o login, caso necessário
//    }
    @GetMapping("/Index")
    public ModelAndView index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // Obtém o email do usuário autenticado
        System.out.println("Email autenticado: " + email); // Depuração

        ModelAndView mv = new ModelAndView("index");

        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            String nome = usuario.get().getNome();
            mv.addObject("nome_usuario", nome);
            System.out.println("Nome do usuário: " + nome); // Depuração
        } else {
            System.out.println("Usuário não encontrado com o email: " + email);
        }

        return mv;
    }



}
