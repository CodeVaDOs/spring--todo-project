package todo.backend.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todo.backend.test.entity.User;
import todo.backend.test.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> createUser(User u) {
        Optional<User> userByName = userRepository.findByEmail(u.getEmail());
        return userByName.isPresent()
                ? Optional.empty()
                : Optional.of(userRepository.save(u));
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
