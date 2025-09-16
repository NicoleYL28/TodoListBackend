package oocl.todolistbackend;

import oocl.todolistbackend.entity.TodoItem;
import oocl.todolistbackend.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoListBackendIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    public void setup(){
        todoRepository.clear();
    }

    @Test
    void should_return_todo_items_when_get_given_todo_items_data_in_db() throws Exception {
        TodoItem newTodo1 = TodoItem.builder().text("todo 1").build();
        TodoItem newTodo2 = TodoItem.builder().text("todo 2").build();

        todoRepository.insert(newTodo1);
        todoRepository.insert(newTodo2);

        mockMvc.perform(get("/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void should_return_todo_item_when_insert_given_valid_todo_item_data() throws Exception {
        String requestBody = """
                { "text": "newTodo"}
                """;

        mockMvc.perform(post("/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.text").value("newTodo"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void should_response_422_when_insert_given_invalid_todo_item_data() throws Exception {
        String requestBody = """
                { "done": false}
                """;

        mockMvc.perform(post("/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnprocessableEntity());
    }

}
