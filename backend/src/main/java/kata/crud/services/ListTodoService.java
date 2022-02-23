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

    public TodoListModel saveTodoList(TodoListModel todoLista){
        return todoListRepository.save(new TodoListModel(todoLista.getName()));
    }

    public List<TodoListModel> findAllTodoLists(){
        return todoListRepository.findAll();
    }
}
