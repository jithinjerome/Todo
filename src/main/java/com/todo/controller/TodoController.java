package com.todo.controller;

import com.todo.model.Todo;
import com.todo.model.User;
import com.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping(path = "/register")
    public User registerUser(@RequestBody User user){
        return todoService.registerUser(user);
    }

    @GetMapping(path = "/getTodo")
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping(path = "/addTodo")
    public Todo creaTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Todo updatedTodo = todoService.updateTodo(id,todo);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable Long id) {
        todoService.deleteTodoById(id);
    }
}