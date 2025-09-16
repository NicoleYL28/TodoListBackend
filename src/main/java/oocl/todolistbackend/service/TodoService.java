package oocl.todolistbackend.service;

import oocl.todolistbackend.TodoItem;
import oocl.todolistbackend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoItem create(TodoItem todoItem) {
        todoRepository.insert(todoItem);
        return todoItem;
    }


    public List<TodoItem> getTodos() {
        return todoRepository.getTodos();
    }
}
