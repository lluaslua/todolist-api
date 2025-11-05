package io.github.lluaslua.todolist_api.repository;

import io.github.lluaslua.todolist_api.model.Tasks;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TasksRepository extends JpaRepository<Tasks, Long> {

    Optional<Tasks> findById(long id);

    @Transactional
    void deleteById(long id);
}
