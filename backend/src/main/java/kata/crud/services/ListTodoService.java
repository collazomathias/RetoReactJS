package kata.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kata.crud.models.TodoListModel;
import kata.crud.repositories.TodoListRepository;

@Service
public class ListTodoService {

    @Autowired
    private TodoListRepository todoListRepository;

    public TodoListModel saveTodoList(TodoListModel todoList){
        TodoListModel _todoList = todoListRepository.save(new TodoListModel(todoList.getName()));
        return _todoList;
    }

    public List<TodoListModel> findAllTodoLists(){
        List<TodoListModel> todoLists = todoListRepository.findAll();
        return todoLists;
    }

    public TodoListModel findTodoList(Long id){
        TodoListModel _todoList = todoListRepository.findById(id).orElseThrow();
        return _todoList;
    }

    public TodoListModel updateTodoList(Long id, TodoListModel todoList){
        TodoListModel _todoList = todoListRepository.findById(id).orElseThrow();
        if(todoList.getName() != null) _todoList.setName(todoList.getName());
        _todoList.setStatus(todoList.isStatus());
        TodoListModel _todoListUpdated = todoListRepository.save(_todoList);
        return _todoListUpdated;
    }

    public void deleteAllTodoLists(){
        todoListRepository.deleteAll();
    }

    public void deleteTodoList(Long id){
        todoListRepository.deleteById(id);
    }
    
}
