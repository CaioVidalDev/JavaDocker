package com.example.JavaDocker.model;

import com.example.JavaDocker.TarefaThread;
import java.util.concurrent.ExecutorService;

public class Tarefa {
    private String titulo;
    private boolean concluida;
    private TarefaThread thread;
    private final Object lock = new Object(); // Objeto de trava para sincronização

    public Tarefa(String titulo) {
        this.titulo = titulo;
        this.concluida = false;
    }

    public void concluir() {
        synchronized (lock) { // Bloco sincronizado para garantir operação atômica
            if (!concluida) {
                concluida = true;
                System.out.println("Tarefa '" + titulo + "' concluída.");
            } else {
                System.out.println("A tarefa '" + titulo + "' já foi concluída.");
            }
        }
    }

    public void iniciarProcessamento(ExecutorService executor) {
        this.thread = new TarefaThread(this);
        executor.execute(thread); // Executa a tarefa no pool de threads
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setTitulo(String novoTitulo) {
        synchronized (lock) { // Bloco sincronizado para garantir operação atômica
            this.titulo = novoTitulo;
        }
    }
}


