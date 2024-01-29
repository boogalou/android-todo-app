package su.demo.taskman.domain.usecase

import su.demo.taskman.domain.entity.TaskItem
import su.demo.taskman.domain.repositories.TaskRepository

class UpdateTaskStatusUseCase(private val taskRepository: TaskRepository) {

  fun execute(taskItemId: TaskItem) {
    taskRepository.updateTaskStatus(taskItemId)
  }

}