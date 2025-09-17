package oocl.todolistbackend.repository;

import oocl.todolistbackend.entity.TodoItem;
import oocl.todolistbackend.dao.TodoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TodoRepository {

    public TodoItem insert(TodoItem todoItem);

    public List<TodoItem> getTodos();

    public TodoItem update(TodoItem updatedTodoItem);

    public boolean getTodoById(long id);

    public void remove(long id);
}
