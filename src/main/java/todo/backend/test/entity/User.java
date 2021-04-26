package todo.backend.test.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Todo> todos = new ArrayList<>();

    public User addTodo(Todo t) {
        t.setUser(this);
        this.todos.add(t);
        return this;
    }

    public User removeTodo(Todo t) {
        t.setUser(null);
        this.todos.remove(t);
        return this;
    }

    public void removeAccountById(Long todoId) {
        this.todos.removeIf(t -> t.getId().equals(todoId));
    }
}
