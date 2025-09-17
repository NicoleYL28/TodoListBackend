package oocl.todolistbackend.repository;

import oocl.todolistbackend.dao.TodoJpaRepository;
import oocl.todolistbackend.entity.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepositoryDBImpl implements TodoRepository{

    @Autowired
    public TodoJpaRepository todoJpaRepository;

    public TodoItem insert(TodoItem todoItem) {
        return todoJpaRepository.save(todoItem);
    }

    public List<TodoItem> getTodos() {
        return todoJpaRepository.findAll();
    }

    public TodoItem update(TodoItem updatedTodoItem) {
        return todoJpaRepository.save(updatedTodoItem);
    }

    public boolean getTodoById(long id) {
        return todoJpaRepository.existsById(id);
    }

    public void remove(long id) {
        todoJpaRepository.deleteById(id);
    }
}
