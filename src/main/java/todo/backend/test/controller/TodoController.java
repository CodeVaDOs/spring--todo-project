package todo.backend.test.controller;

import org.springframework.web.bind.annotation.*;
import todo.backend.test.dto.request.RequestTodo;
import todo.backend.test.entity.Todo;
import todo.backend.test.service.TodoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/todos")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping
    public Todo createTodo(@RequestBody RequestTodo t) {
        return todoService.createTodo(t.toEntity(), t.getUserId());
    }

    @PutMapping("{id}")
    public Optional<Todo> updateTodo(@PathVariable("id") Long id, @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo);
    }
}
