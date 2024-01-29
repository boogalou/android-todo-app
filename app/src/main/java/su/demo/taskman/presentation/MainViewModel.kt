package su.demo.taskman.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import su.demo.taskman.data.repositories.TaskRepositoryImpl
import su.demo.taskman.data.storage.db.TaskItemStorage
import su.demo.taskman.domain.entity.TaskItem
import su.demo.taskman.domain.usecase.DeleteTaskUseCase
import su.demo.taskman.domain.usecase.GetTaskListUseCase
import su.demo.taskman.domain.usecase.UpdateTaskStatusUseCase

class MainViewModel : ViewModel() {


  private val repository = TaskRepositoryImpl(TaskItemStorage())


  private val getTaskListUseCase = GetTaskListUseCase(repository)
  private val deleteTaskUseCase = DeleteTaskUseCase(repository)
  private val updateTaskStatusUseCase = UpdateTaskStatusUseCase(repository)

  val taskList = getTaskListUseCase.execute()

  fun deleteTask(taskItem: TaskItem) {
    deleteTaskUseCase.execute(taskItem)
  }

  fun switchComplete(taskItem: TaskItem) {
    updateTaskStatusUseCase.execute(taskItem)
  }
}