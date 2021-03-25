package com.harshal.tempReport.repository;

import com.harshal.tempReport.model.ToDo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoRepo {
    public List<ToDo> getAllTodos() {
        List<ToDo> todos = new ArrayList<ToDo>();
        todos.add(new ToDo("To Do 1", "Complete 1"));
        todos.add(new ToDo("To Do 2", "Complete 2"));
        todos.add(new ToDo("To Do 3", "Complete 3"));
        return todos;
    }
}
