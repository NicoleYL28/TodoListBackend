package oocl.todolistbackend.exception_handler;

import oocl.todolistbackend.exceptions.InvalidRequestBodyException;
import oocl.todolistbackend.exceptions.TodoItemNotFoundException;
import oocl.todolistbackend.exceptions.UpdateItemIsEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(InvalidRequestBodyException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public void handleInvalidRequestBodyException(Exception e){}

    @ExceptionHandler(TodoItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoItemNotFoundException(Exception e){}

    @ExceptionHandler(UpdateItemIsEmptyException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public void handleUpdateItemIsEmptyException(Exception e){}

}
