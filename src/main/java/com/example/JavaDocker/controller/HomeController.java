package com.example.JavaDocker.controller;

import com.example.JavaDocker.model.Tarefa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private List<Tarefa> tarefas = new ArrayList<>();
    private int tarefaIndexToEdit;
    private final Object lock = new Object(); // Objeto de trava para sincronização

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tarefas", tarefas);
        return "home/projeto-tarefas/index";
    }

    @GetMapping("/cadastrar-tarefa")
    public String cadastrarTarefaPage() {
        return "home/projeto-tarefas/cadastrar-tarefa";
    }

    @PostMapping("/cadastrar-tarefa")
    public String cadastrarTarefa(@RequestParam("titulo") String titulo) {
        if (!titulo.trim().isEmpty()) {
            Tarefa novaTarefa = new Tarefa(titulo);
            synchronized (lock) { // Bloco sincronizado para garantir operação atômica
                tarefas.add(novaTarefa);
            }
            novaTarefa.iniciarProcessamento();
        }
        return "redirect:/";
    }

    @GetMapping("/editar-tarefa/{index}")
    public String preencherFormularioEdicao(@PathVariable("index") int index, Model model) {
        if (index >= 0 && index < tarefas.size()) {
            model.addAttribute("titulo", tarefas.get(index).getTitulo());
            tarefaIndexToEdit = index;
            return "home/projeto-tarefas/editar-tarefa";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/editar-tarefa")
    public String editarTarefa(@RequestParam("titulo") String novoTitulo, Model model) {
        if (tarefaIndexToEdit >= 0 && tarefaIndexToEdit < tarefas.size() && !novoTitulo.trim().isEmpty()) {
            synchronized (lock) { // Bloco sincronizado para garantir operação atômica
                tarefas.get(tarefaIndexToEdit).setTitulo(novoTitulo);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/excluir-tarefa/{index}")
    public String preencherFormularioExclusao(@PathVariable("index") int index, Model model) {
        if (index >= 0 && index < tarefas.size()) {
            model.addAttribute("titulo", tarefas.get(index).getTitulo());
            tarefaIndexToEdit = index;
            return "home/projeto-tarefas/excluir-tarefa";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/excluir-tarefa")
    public String excluirTarefa() {
        if (tarefaIndexToEdit >= 0 && tarefaIndexToEdit < tarefas.size()) {
            synchronized (lock) { // Bloco sincronizado para garantir operação atômica
                tarefas.remove(tarefaIndexToEdit);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/visualizar-tarefa/{index}")
    public String preencherFormularioVisualizacao(@PathVariable("index") int index, Model model) {
        if (index >= 0 && index < tarefas.size()) {
            model.addAttribute("titulo", tarefas.get(index).getTitulo());
            return "home/projeto-tarefas/visualizar-tarefa";
        } else {
            return "redirect:/";
        }
    }
}


