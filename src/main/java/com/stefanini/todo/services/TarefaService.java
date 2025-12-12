package com.stefanini.todo.services;

import com.stefanini.todo.models.Tarefa;
import com.stefanini.todo.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Transactional
    public Tarefa criarTarefa(Tarefa tarefa) {

        if (tarefa.getTitulo() == null || tarefa.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("O título da tarefa é obrigatório");
        }

        if (tarefa.getTitulo().length() > 100) {
            throw new IllegalArgumentException("O título não pode ter mais de 100 caracteres");
        }

        if (tarefa.getDescricao() != null && tarefa.getDescricao().length() > 500) {
            throw new IllegalArgumentException("A descrição não pode ter mais de 500 caracteres");
        }

        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTodasTarefas() {
        return tarefaRepository.findAllByOrderByDataCriacaoDesc();
    }

    public Tarefa buscarTarefaPorId(Integer id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada com ID: " + id));
    }

    @Transactional
    public Tarefa atualizarTarefa(Integer id, Tarefa tarefaAtualizada) {
        Tarefa tarefaExistente = buscarTarefaPorId(id);

        if (tarefaAtualizada.getTitulo() != null) {
            if (tarefaAtualizada.getTitulo().trim().isEmpty()) {
                throw new IllegalArgumentException("O título da tarefa não pode ser vazio");
            }
            if (tarefaAtualizada.getTitulo().length() > 100) {
                throw new IllegalArgumentException("O título não pode ter mais de 100 caracteres");
            }
            tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
        }

        if (tarefaAtualizada.getDescricao() != null) {
            if (tarefaAtualizada.getDescricao().length() > 500) {
                throw new IllegalArgumentException("A descrição não pode ter mais de 500 caracteres");
            }
            tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
        }

        if (tarefaAtualizada.getStatus() != null) {
            try {
                Tarefa.StatusTarefa status = tarefaAtualizada.getStatus();

                Tarefa.StatusTarefa.valueOf(status.name());

                tarefaExistente.setStatus(status);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Status inválido. Valores permitidos: PENDENTE, EM_ANDAMENTO, CONCLUIDA");
            }
        }

        return tarefaRepository.save(tarefaExistente);
    }

    @Transactional
    public void excluirTarefa(Integer id) {
        if (!tarefaRepository.existsById(id)) {
            throw new IllegalArgumentException("Tarefa não encontrada com ID: " + id);
        }
        tarefaRepository.deleteById(id);
    }

    public List<Tarefa> buscarTarefasPorTitulo(String titulo) {
        return tarefaRepository.findByTituloContainingIgnoreCase(titulo);
    }
}