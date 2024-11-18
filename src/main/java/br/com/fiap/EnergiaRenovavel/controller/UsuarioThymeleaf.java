package br.com.fiap.EnergiaRenovavel.controller;

import br.com.fiap.EnergiaRenovavel.mensageria.ProdutorKafka;
import br.com.fiap.EnergiaRenovavel.model.Usuario;
import br.com.fiap.EnergiaRenovavel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/user")
public class UsuarioThymeleaf {

    @Autowired
    private UsuarioRepository user;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProdutorKafka produtorKafka;


    @GetMapping("/new")
    public ModelAndView usuarioForm() {
        ModelAndView modelAndView = new ModelAndView("usuario-form");
        modelAndView.addObject("usuario", new Usuario());
        modelAndView.addObject("title", "Adicionar/Editar Usuário");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }


    @PostMapping("/save")
    public ModelAndView saveUsuario(@ModelAttribute Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        user.save(usuario);

        String msg = "Usuário "+ usuario.getNome();
        produtorKafka.enviarMensagem(msg);
        ModelAndView mv = new ModelAndView("redirect:/user/list");
        return mv;
    }



    @GetMapping("/list")
    public ModelAndView listUsuarios() {
        ModelAndView modelAndView = new ModelAndView("usuario-list");
        modelAndView.addObject("usuarios", user.findAll());
        modelAndView.addObject("title", "Lista de Usuários");
        return modelAndView;
    }





    @GetMapping("/edit/{id}")
    public ModelAndView editUsuario(@PathVariable("id") Long id) {
        Usuario usuario = user.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário inválido: " + id));
        ModelAndView modelAndView = new ModelAndView("usuario-form");
        modelAndView.addObject("usuario", usuario);
        modelAndView.addObject("title", "Editar Usuário");
        return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deleteUsuario(@PathVariable("id") Long id) {
        user.deleteById(id);
        ModelAndView mView = new ModelAndView("redirect:/user/list");
        return mView;
    }
}
