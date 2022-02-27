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

import kata.crud.models.TodoModel;
import kata.crud.services.TodoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    /**
     * Método para crear un nuevo TODO.
     * 
     * @UsageMethod localhost:8081/api/todos with POST
     * @param todo datos que se asignarán al todo a crear.
     * @return HTTP Response CREATED con el todo en caso de éxito, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @PostMapping
    public ResponseEntity<TodoModel> createTodo(@RequestBody TodoModel todo) {
        try {
            return new ResponseEntity<>(todoService.saveTodo(todo), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para obtener todos los TODOs existentes.
     * 
     * @UsageMethod localhost:8081/api/todos with GET
     * @return HTTP Response OK con la/s lista/s en caso de éxito, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @GetMapping
    public ResponseEntity<List<TodoModel>> getAllTodos() {
        try {
            List<TodoModel> todos = todoService.findAllTodos();
            if (!todos.isEmpty()) {
                return new ResponseEntity<>(todos, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para obtener todos los TodoList mediante su IdTodoList.
     * 
     * @UsageMethod localhost:8081/api/todos/list/{idTodoList} with GET
     * @param idTodoList id de lista de la cuál se quieren obtener los TODOs.
     * @return HTTP Response OK con los TODOs en caso de éxito, NO_CONTENT en caso
     *         de que no haya ningún TODO en la lista con esa id, y
     *         EXPECTATION_FAILED
     *         en caso de error.
     * @author Mathías Collazo
     **/
    @GetMapping("/list/{idTodoList}")
    public ResponseEntity<List<TodoModel>> getAllTodosOfList(@PathVariable("idTodoList") Long idTodoList) {
        try {
            List<TodoModel> todosOfList = todoService.findAllTodosOfList(idTodoList);
            if (!todosOfList.isEmpty()) {
                return new ResponseEntity<>(todosOfList, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para obtener un TODO mediante su ID.
     * 
     * @UsageMethod localhost:8081/api/todos/{id} with GET
     * @param id id del TODO del cuál se quiere obtener los datos.
     * @return HTTP Response OK con el TODO en caso de éxito, NO_CONTENT en caso de
     *         que no haya un TODO con esa id, y EXPECTATION_FAILED en caso de
     *         error.
     * @author Mathías Collazo
     **/
    @GetMapping("/{id}")
    public ResponseEntity<TodoModel> getTodo(@PathVariable("id") Long id) {
        try {
            TodoModel todo = todoService.findTodo(id);
            if (todo != null) {
                return new ResponseEntity<>(todo, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para actualizar un TODO.
     * 
     * @UsageMethod localhost:8081/api/todos/{id} with PUT
     * @param id   id del TODO del cuál se quiere actualizar los datos.
     * @param todo datos para actualizar el TODO.
     * @return HTTP Response OK con el TODO actualizado en caso de éxito, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable("id") Long id, @RequestBody TodoModel todo) {
        try {
            TodoModel todoUpdated = todoService.updateTodo(id, todo);
            return new ResponseEntity<>(todoUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para actualizar el estado "Complete" de un TODO.
     * 
     * @UsageMethod localhost:8081/api/lists/{id} with PUT
     * @param id id del TODO del cuál se quiere actualizar el estado.
     * @return HTTP Response OK con el TODO actualizado en caso de éxito, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @PutMapping("/status/{id}")
    public ResponseEntity<TodoModel> changeStatusTodo(@PathVariable("id") Long id) {
        try {
            TodoModel todoUpdated = todoService.changeStatusTodo(id);
            return new ResponseEntity<>(todoUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para eliminar todos las TODOs.
     * 
     * @UsageMethod localhost:8081/api/todos with DELETE
     * @return HTTP Response OK con un mensaje en caso de éxito, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @DeleteMapping
    public ResponseEntity<String> deleteAllTodos() {
        try {
            todoService.deleteAllTodos();
            return new ResponseEntity<>("Todos los 'TODOs' han sido eliminados.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para eliminar todos las TODOs de una lista.
     * 
     * @UsageMethod localhost:8081/api/todos with DELETE
     * @param idTodoList id de la lista de la cuál se quieren eliminar todos los
     *                   TODOs.
     * @return HTTP Response OK con un mensaje en caso de éxito, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @DeleteMapping("/list/{idTodoList}")
    public ResponseEntity<String> deleteAllTodosOfList(@PathVariable("idTodoList") Long idTodoList) {
        try {
            todoService.deleteAllTodosOfList(idTodoList);
            return new ResponseEntity<>("Todos los 'TODOs' de la lista '" + idTodoList + "' han sido eliminados.",
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Método para eliminar un TODO mediante su id.
     * 
     * @UsageMethod localhost:8081/api/todos/{id} with DELETE
     * @param id id del TODO que se quiere eliminar.
     * @return HTTP Response OK con un mensaje en caso de éxito, y
     *         EXPECTATION_FAILED en caso de error.
     * @author Mathías Collazo
     **/
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        try {
            todoService.deleteTodo(id);
            return new ResponseEntity<>("El 'TODO' con id '" + id + "' ha sido eliminado.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
