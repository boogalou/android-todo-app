package su.demo.taskman.domain.usecase

import su.demo.taskman.domain.entity.TaskItem
import su.demo.taskman.domain.repositories.TaskRepository

class DeleteTaskUseCase(private val taskRepository: TaskRepository) {

  fun execute(taskItem: TaskItem) {
    taskRepository.deleteTask(taskItem)
  }

}