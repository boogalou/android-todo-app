package su.demo.taskman.domain.usecase

import su.demo.taskman.domain.entity.TaskItem
import su.demo.taskman.domain.repositories.TaskRepository

class GetTaskUseCase(private val taskRepository: TaskRepository) {

  fun execute(taskItemId: TaskItem): TaskItem? {
    return taskRepository.getTask(taskItemId)
  }

}