package oocl.todolistbackend.controller;

import oocl.todolistbackend.TodoItem;
import oocl.todolistbackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;


    @GetMapping("/todos")
    public ResponseEntity<List<TodoItem>> getCompanies(){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodos());
    }




}
