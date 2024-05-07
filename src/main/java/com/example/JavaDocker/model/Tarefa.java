package com.example.JavaDocker.model;

import com.example.JavaDocker.TarefaThread;

public class Tarefa {
    private String titulo;
    private boolean concluida;
    private TarefaThread thread;
    private final Object lock = new Object(); 
    
    public Tarefa(String titulo) {
        this.titulo = titulo;
        this.concluida = false;
    }

    public void concluir() {
        synchronized (lock) { 
            if (!concluida) {
                concluida = true;
                System.out.println("Tarefa '" + titulo + "' concluída.");
            } else {
                System.out.println("A tarefa '" + titulo + "' já foi concluída.");
            }
        }
    }

    public void iniciarProcessamento() {
        this.thread = new TarefaThread(this);
        thread.start();
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setTitulo(String novoTitulo) {
        synchronized (lock) { 
            this.titulo = novoTitulo;
        }
    }
}


