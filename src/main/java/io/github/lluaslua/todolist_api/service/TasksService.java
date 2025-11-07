package io.github.lluaslua.todolist_api.service;

import io.github.lluaslua.todolist_api.model.Tasks;
import io.github.lluaslua.todolist_api.repository.TasksRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TasksService {

    private final TasksRepository tasksRepository;

    private void saveTask(Tasks task) {
        tasksRepository.save(task);
    }

    public Tasks findById(long id){
        return tasksRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found with id " + id)
        );
    }

    public void deleteTaskById(long id) {
        tasksRepository.deleteById(id);
    }

    public void updateTaskById(Long id, Tasks task){
        Tasks taskEntity = tasksRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
        Tasks updatedTask = Tasks.builder()
            .id(taskEntity.getId())
            .taskName(task.getTaskName() != null ? taskEntity.getTaskName() : taskEntity.getTaskName())
            .taskPriority(taskEntity.getTaskPriority() != null ? taskEntity.getTaskPriority() : taskEntity.getTaskName())
            .build();
        tasksRepository.saveAndFlush(updatedTask);
    }


}
