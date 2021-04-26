package todo.backend.test.service;

import org.springframework.stereotype.Service;
import todo.backend.test.entity.Todo;
import todo.backend.test.exception.UserNotFoundException;
import todo.backend.test.repository.TodoRepository;
import todo.backend.test.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private TodoRepository todoRepository;
    private UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public Todo createTodo(Todo t, Long userId) {
        userRepository.findById(userId)
                .map(u -> {
                    u.addTodo(t);
                    return userRepository.save(u);
                }).orElseThrow(() -> new UserNotFoundException(String.format("User with id: %d, not found", userId)));
        return todoRepository.save(t);
    }

    public void deleteTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }

    public Optional<Todo> updateTodo(Long todoId, Todo newTodo) {
        return todoRepository.findById(todoId)
                .map(oldTodo -> {
                    newTodo.setId(todoId);
                    return todoRepository.save(newTodo);
                });
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
}
