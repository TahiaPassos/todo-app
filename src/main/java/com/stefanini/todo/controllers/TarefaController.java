package com.stefanini.todo.controllers;


import com.stefanini.todo.models.Tarefa;
import com.stefanini.todo.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @RestController
    @RequestMapping("/api/tarefas")
    @CrossOrigin(origins = "*")
    public class TarefaController {

        @Autowired
        private TarefaService tarefaService;

        @PostMapping
        public ResponseEntity<?> criarTarefa(@RequestBody Tarefa tarefa) {
            try {
                Tarefa novaTarefa = tarefaService.criarTarefa(tarefa);
                return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping
        public ResponseEntity<List<Tarefa>> listarTodasTarefas() {
            List<Tarefa> tarefas = tarefaService.listarTodasTarefas();
            return ResponseEntity.ok(tarefas);
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> buscarTarefaPorId(@PathVariable Integer id) {
            try {
                Tarefa tarefa = tarefaService.buscarTarefaPorId(id);
                return ResponseEntity.ok(tarefa);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }

        @GetMapping("/status/{status}")
        public ResponseEntity<List<Tarefa>> listarTarefasPorStatus(@PathVariable Tarefa.StatusTarefa status) {
            List<Tarefa> tarefas = tarefaService.listarTarefasPorStatus(status);
            return ResponseEntity.ok(tarefas);
        }

        @GetMapping("/buscar")
        public ResponseEntity<List<Tarefa>> buscarTarefasPorTitulo(@RequestParam String titulo) {
            List<Tarefa> tarefas = tarefaService.buscarTarefasPorTitulo(titulo);
            return ResponseEntity.ok(tarefas);
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> atualizarTarefa(@PathVariable Integer id, @RequestBody Tarefa tarefa) {
            try {
                Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, tarefa);
                return ResponseEntity.ok(tarefaAtualizada);
            } catch (IllegalArgumentException e) {
                if (e.getMessage().contains("não encontrada")) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
                }
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> excluirTarefa(@PathVariable Integer id) {
            try {
                tarefaService.excluirTarefa(id);
                return ResponseEntity.noContent().build();
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }
    }