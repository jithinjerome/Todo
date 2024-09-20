package com.todo.service;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.todo.model.Todo;
import com.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

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
