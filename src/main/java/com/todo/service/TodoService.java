package com.todo.service;

import java.util.List;
import java.util.Optional;

import com.todo.model.User;
import com.todo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.todo.model.Todo;
import com.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public ResponseEntity<?> registerUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(email,password);
        if(userOptional.isPresent())
        {
            return new ResponseEntity<>("User login successfull",HttpStatus.OK);
        }
        return new ResponseEntity<>("Login failed",HttpStatus.BAD_REQUEST);
    }

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo){
        return todoRepository.save(todo);
    }

    @Transactional
    public Todo updateTodo(Long id, Todo todo) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if(existingTodo.isPresent()){
            Todo updatedTodo = existingTodo.get();
            updatedTodo.setTitle(todo.getTitle());
            updatedTodo.setCompleted(todo.isCompleted());
            return todoRepository.save(updatedTodo);
        }
        throw new RuntimeException("Todo not found with id "+id);

    }

    public void deleteTodoById(Long Id){
        todoRepository.deleteById(Id);
    }



}
