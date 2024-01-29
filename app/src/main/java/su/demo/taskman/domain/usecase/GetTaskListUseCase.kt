package su.demo.taskman.domain.usecase

import androidx.lifecycle.LiveData
import su.demo.taskman.domain.entity.TaskItem
import su.demo.taskman.domain.repositories.TaskRepository

class GetTaskListUseCase(private val taskRepository: TaskRepository) {

  fun execute(): LiveData<List<TaskItem>> {
    return taskRepository.getTasksList()
  }
}