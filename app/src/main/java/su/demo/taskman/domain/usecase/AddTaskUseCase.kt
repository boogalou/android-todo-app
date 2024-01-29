package su.demo.taskman.domain.usecase

import su.demo.taskman.domain.entity.TaskItem
import su.demo.taskman.domain.repositories.TaskRepository

class AddTaskUseCase(private val taskRepository: TaskRepository
) {

  fun execute(taskItem: TaskItem) {
    taskRepository.addTask(taskItem)
  }

}