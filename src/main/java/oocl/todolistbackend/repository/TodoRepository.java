package oocl.todolistbackend.repository;

import oocl.todolistbackend.entity.TodoItem;
import oocl.todolistbackend.dao.TodoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository {

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    public TodoItem insert(TodoItem todoItem) {
        return todoJpaRepository.save(todoItem);
    }

    public List<TodoItem> getTodos() {
        return todoJpaRepository.findAll();
    }

    public TodoItem update(TodoItem updatedTodoItem) {
        return todoJpaRepository.save(updatedTodoItem);
    }

    public void clear() {
        todoJpaRepository.deleteAll();
    }
}
