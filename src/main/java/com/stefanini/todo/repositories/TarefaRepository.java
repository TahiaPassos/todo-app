package com.stefanini.todo.repositories;


import com.stefanini.todo.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    List<Tarefa> findByStatus(Tarefa.StatusTarefa status);

    List<Tarefa> findByTituloContainingIgnoreCase(String titulo);

    List<Tarefa> findAllByOrderByDataCriacaoDesc();
}