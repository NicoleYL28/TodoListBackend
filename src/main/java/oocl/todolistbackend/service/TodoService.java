package oocl.todolistbackend.service;

import ch.qos.logback.core.util.StringUtil;
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
        if(updatedTodoItem.getDone() == null || StringUtil.isNullOrEmpty(updatedTodoItem.getText())){
            throw new UpdateItemIsEmptyException();
        }
        updatedTodoItem.setId(id);
        return todoRepository.update(updatedTodoItem);
    }

    public void delete(long id) {
        if(!todoRepository.getTodoById(id)){
            throw new TodoItemNotFoundException();
        }
        todoRepository.remove(id);
    }
}
