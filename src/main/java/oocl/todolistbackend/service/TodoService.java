package oocl.todolistbackend.service;

import oocl.todolistbackend.entity.TodoItem;
import oocl.todolistbackend.exceptions.InvalidRequestBodyException;
import oocl.todolistbackend.exceptions.TodoItemNotFoundException;
import oocl.todolistbackend.exceptions.UpdateItemIsEmptyException;
import oocl.todolistbackend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoItem create(TodoItem todoItem) {
        if(todoItem.getText() == null || todoItem.getText().trim().isEmpty()){
            throw new InvalidRequestBodyException();
        }
        return todoRepository.insert(todoItem);
    }


    public List<TodoItem> getTodos() {
        return todoRepository.getTodos();
    }

    public TodoItem update(long id, TodoItem updatedTodoItem) {
        if(!todoRepository.getTodoById(id)){
            throw new TodoItemNotFoundException();
        }
        if(updatedTodoItem.getText() == null || updatedTodoItem.getText().trim().isEmpty()){
            throw new UpdateItemIsEmptyException();
        }
        updatedTodoItem.setId(id);
        return todoRepository.update(updatedTodoItem);
    }

    public void delete(long id) {

        todoRepository.remove(id);
    }
}
