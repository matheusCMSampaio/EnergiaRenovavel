package br.com.fiap.EnergiaRenovavel.controller;

import br.com.fiap.EnergiaRenovavel.model.Dispositivo;
import br.com.fiap.EnergiaRenovavel.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/dispositivo")
public class DispositivoThymeleaf {

    @Autowired
    private DispositivoRepository repository;

    // Listar dispositivos
    @GetMapping("/list")
    public ModelAndView listarDispositivos() {
        ModelAndView modelAndView = new ModelAndView("dispositivos-list");
        modelAndView.addObject("dispositivos", repository.findAll());
        modelAndView.addObject("title", "Lista de Dispositivos");
        return modelAndView;
    }

    // Adicionar novo dispositivo (Formulário de criação)
    @GetMapping("/new")
    public String novoDispositivoForm(Model model) {
        model.addAttribute("dispositivo", new Dispositivo());
        model.addAttribute("title", "Adicionar Novo Dispositivo");
        return "dispositivos-form";
    }

    // Salvar o dispositivo
    @PostMapping("/save")
    public String salvarDispositivo(@ModelAttribute Dispositivo dispositivo) {
        repository.save(dispositivo);
        return "redirect:/dispositivo/list";
    }

    // Editar dispositivo (Formulário de edição)
    @GetMapping("/edit/{id}")
    public String editarDispositivoForm(@PathVariable("id") Long id, Model model) {
        Dispositivo dispositivo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("dispositivo", dispositivo);
        model.addAttribute("title", "Editar Dispositivo");
        return "dispositivos-form";
    }

    // Atualizar dispositivo
    @PostMapping("/update/{id}")
    public String atualizarDispositivo(@PathVariable("id") Long id, @ModelAttribute Dispositivo dispositivo) {
        dispositivo.setId(id);
        repository.save(dispositivo);
        return "redirect:/dispositivo/list";
    }

    // Excluir dispositivo
    @GetMapping("/delete/{id}")
    public String excluirDispositivo(@PathVariable("id") Long id) {
        Dispositivo dispositivo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        repository.delete(dispositivo);
        return "redirect:/dispositivo/list";
    }
}
