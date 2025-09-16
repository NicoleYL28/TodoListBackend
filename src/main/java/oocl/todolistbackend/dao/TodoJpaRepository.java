package oocl.todolistbackend.dao;

import oocl.todolistbackend.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<TodoItem, Long> {
}
