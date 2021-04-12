package com.harshal.tempReport.repository;

import com.harshal.tempReport.model.ToDo;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoRepo {
    public List<ToDo> getAllTodos() {
        List<ToDo> todos = new ArrayList<ToDo>();
        todos.add(new ToDo("To Do 1", "Complete 1", new Date()));
        todos.add(new ToDo("To Do 2", "Complete 2", new Date()));
        todos.add(new ToDo("To Do 3", "Complete 3", new Date()));
        return todos;
    }

    public List<ToDo> getReport1(Date date) throws ParseException {
        List<ToDo> todos = new ArrayList<ToDo>();
        todos.add(new ToDo("To Do 1", "Complete 1", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-13 23:59:59")));
        todos.add(new ToDo("To Do 2", "Complete 2", new Date()));
        todos.add(new ToDo("To Do 3", "Complete 3", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-13 23:59:59")));
        return todos.stream().filter(todo -> todo.getDate().equals(date)).collect(Collectors.toList());
    }

}
