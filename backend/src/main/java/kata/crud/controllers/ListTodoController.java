package kata.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kata.crud.models.TodoListModel;
import kata.crud.services.ListTodoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/lists")
public class ListTodoController {

    @Autowired
    private ListTodoService todoListService;

    /**
     * Método para crear una nueva TodoList.
     * 
     * @UsageMethod localhost:8081/api/lists with POST
     * @param todoList datos que se asignarán a la lista a crear.
     * @return HTTP Response CREATED con la lista en caso de éxito, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @PostMapping
    public ResponseEntity<TodoListModel> createTodoList(@RequestBody TodoListModel todoList) {
        try {
            return new ResponseEntity<>(todoListService.saveTodoList(todoList), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para obtener todas las TodoLists existentes.
     * 
     * @UsageMethod localhost:8081/api/lists with GET
     * @return HTTP Response OK con la/s lista/s en caso de éxito, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @GetMapping
    public ResponseEntity<List<TodoListModel>> getAllTodoLists() {
        try {
            List<TodoListModel> todoLists = todoListService.findAllTodoLists();
            if (!todoLists.isEmpty()) {
                return new ResponseEntity<>(todoLists, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para obtener una TodoList mediante su ID.
     * 
     * @UsageMethod localhost:8081/api/lists/{id} with GET
     * @param id id de lista de la cuál se quiere obtener los datos.
     * @return HTTP Response OK con la lista en caso de éxito, NO_CONTENT en caso de
     *         que no haya una lista con esa id, y EXPECTATION_FAILED en caso de
     *         error.
     * @author Mathías Collazo
     **/
    @GetMapping("/{id}")
    public ResponseEntity<TodoListModel> getTodoList(@PathVariable("id") Long id) {
        try {
            TodoListModel todoList = todoListService.findTodoList(id);
            if (todoList != null) {
                return new ResponseEntity<>(todoList, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para actualizar una TodoList.
     * 
     * @UsageMethod localhost:8081/api/lists/{id} with PUT
     * @param id       id de lista de la cuál se quiere actualizar los datos.
     * @param todoList datos para actualizar la lista.
     * @return HTTP Response OK con la lista actualizada en caso de éxito, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodoList(@PathVariable("id") Long id, @RequestBody TodoListModel todoList) {
        try {
            TodoListModel todoListUpdated = todoListService.updateTodoList(id, todoList);
            return new ResponseEntity<>(todoListUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para eliminar todas las TodoLists.
     * 
     * @UsageMethod localhost:8081/api/lists with DELETE
     * @return HTTP Response OK con un mensaje en caso de éxito, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @DeleteMapping
    public ResponseEntity<String> deleteAllTodoLists() {
        try {
            todoListService.deleteAllTodoLists();
            return new ResponseEntity<>("Todas las listas de 'TODOs' han sido eliminadas.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para eliminar una TodoList mediante su ID.
     * 
     * @UsageMethod localhost:8081/api/lists/{id} with DELETE
     * @param id id de lista que se quiere eliminar.
     * @return HTTP Response OK con un mensaje en caso de éxito, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoList(@PathVariable("id") Long id) {
        try {
            todoListService.deleteTodoList(id);
            return new ResponseEntity<>("La lista de 'TODOs' con id '" + id + "' ha sido eliminada.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
