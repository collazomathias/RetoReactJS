package kata.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kata.crud.models.TodoListModel;
import kata.crud.services.ListTodoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ListTodoController {

    @Autowired
    private ListTodoService todoListService;

    @PostMapping
    public ResponseEntity<TodoListModel> createTodoList(@RequestBody TodoListModel todoList){
        try{
            return new ResponseEntity<>(todoListService.saveTodoList(todoList), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping
    public ResponseEntity<List<TodoListModel>> getTodoLists(){
        try{
            List<TodoListModel> todoLists = todoListService.findAllTodoLists();
            if(!todoLists.isEmpty()){
                return new ResponseEntity<>(todoLists, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
