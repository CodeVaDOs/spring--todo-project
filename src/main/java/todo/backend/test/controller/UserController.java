package todo.backend.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import todo.backend.test.entity.User;
import todo.backend.test.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public Optional<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping
    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }
}
