package todo.backend.test.dto.request;

import lombok.Data;
import todo.backend.test.entity.Todo;

@Data
public class RequestTodo {
    private String title;
    private Boolean isCompleted = false;
    private String description;
    private Long userId;

    public Todo toEntity() {
        return Todo.builder()
                .title(this.title)
                .isCompleted(this.isCompleted)
                .description(this.description)
                .build();
    }
}
