package com.example.JavaDocker;

import com.example.JavaDocker.model.Tarefa;

public class TarefaThread extends Thread {
    private Tarefa tarefa;

    public TarefaThread(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    @Override
    public void run() {
     
        System.out.println("Iniciando processamento da tarefa: " + tarefa.getTitulo());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tarefa.concluir();
        System.out.println("Tarefa concluída: " + tarefa.getTitulo());
    }
}

