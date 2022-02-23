package kata.crud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kata.crud.models.TodoListModel;

public interface TodoListRepository extends JpaRepository<TodoListModel, Long>{
    List<TodoListModel> findByName(String name);
}
