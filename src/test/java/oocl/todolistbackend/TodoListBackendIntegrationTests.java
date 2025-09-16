package oocl.todolistbackend;

import oocl.todolistbackend.entity.TodoItem;
import oocl.todolistbackend.repository.TodoRepository;
import oocl.todolistbackend.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoListBackendIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

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

        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.text").value("newTodo"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void should_return_todo_item_when_insert_given_customized_id() throws Exception {
        String requestBody = """
                { "id": 1234,
                "text": "newTodo"}
                """;

        mockMvc.perform(post("/todos")
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

        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void should_return_item_when_update_given_valid_updated_todo_item_data() throws Exception {
        TodoItem newTodo1 = TodoItem.builder().text("todo 1").build();
        TodoItem returnedTodo = todoService.create(newTodo1);

        String updateRequestBody = """
                {
                "text": "update todo",
                "done": true
                }
                """;
        mockMvc.perform(put("/todos/{id}", returnedTodo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(returnedTodo.getId()))
                .andExpect(jsonPath("$.text").value("update todo"))
                .andExpect(jsonPath("$.done").value(true));

    }

    @Test
    void should_response_404_when_update_given_invalid_id() throws Exception {
        TodoItem newTodo1 = TodoItem.builder().text("todo 1").build();
        TodoItem returnedTodo = todoService.create(newTodo1);

        String updateRequestBody = """
                {
                "text": "update todo",
                "done": true
                }
                """;
        mockMvc.perform(put("/todos/{id}", 999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_response_422_when_update_given_empty_body() throws Exception {
        TodoItem newTodo1 = TodoItem.builder().text("todo 1").build();
        TodoItem returnedTodo = todoService.create(newTodo1);

        String updateRequestBody = """
                {}
                """;
        mockMvc.perform(put("/todos/{id}", returnedTodo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestBody))
                .andExpect(status().isUnprocessableEntity());
    }







}
