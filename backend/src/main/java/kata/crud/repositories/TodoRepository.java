package kata.crud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kata.crud.models.TodoModel;

public interface TodoRepository extends JpaRepository<TodoModel, Long> {
    List<TodoModel> findByName(String name);
}
