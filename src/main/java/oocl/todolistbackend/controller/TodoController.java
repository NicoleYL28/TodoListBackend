package oocl.todolistbackend.controller;

import oocl.todolistbackend.entity.TodoItem;
import oocl.todolistbackend.dto.TodoItemReq;
import oocl.todolistbackend.service.TodoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<List<TodoItem>> getCompanies(){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodos());
    }

    @PostMapping("/todo")
    public ResponseEntity<TodoItem> addTodo(@RequestBody TodoItemReq todoItemReq) {
        TodoItem todoItem = new TodoItem();
        BeanUtils.copyProperties(todoItemReq, todoItem);
        TodoItem result = todoService.create(todoItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }



}
