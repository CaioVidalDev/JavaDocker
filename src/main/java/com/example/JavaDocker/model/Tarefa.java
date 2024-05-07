package com.example.JavaDocker.model;

import com.example.JavaDocker.TarefaThread;
import java.util.concurrent.ExecutorService;

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

    public void iniciarProcessamento(ExecutorService executor) {
        this.thread = new TarefaThread(this);
        executor.execute(thread);
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setTitulo(String novoTitulo) {
        synchronized (lock) {
            System.out.println("Título da tarefa '" + titulo + "' atualizado para '" + novoTitulo + "'.");
            this.titulo = novoTitulo;
        }
    }

    public void excluir() {
        synchronized (lock) {
            System.out.println("Tarefa '" + titulo + "' excluída.");
        }
    }

    public void visualizar() {
        synchronized (lock) {
            System.out.println("Visualizando tarefa: '" + titulo + "'.");
        }
    }

    public void listarAtualizado() {
        synchronized (lock) {
            System.out.println("Lista de tarefas atualizada.");
        }
    }
}
