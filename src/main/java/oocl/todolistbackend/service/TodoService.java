package oocl.todolistbackend.service;

import oocl.todolistbackend.entity.TodoItem;
import oocl.todolistbackend.exceptions.InvalidRequestBodyException;
import oocl.todolistbackend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoItem create(TodoItem todoItem) {
        if(todoItem.getText().trim().isEmpty() || todoItem.getText() == null){
            throw new InvalidRequestBodyException();
        }
        todoRepository.insert(todoItem);
        return todoItem;
    }


    public List<TodoItem> getTodos() {
        return todoRepository.getTodos();
    }
}
