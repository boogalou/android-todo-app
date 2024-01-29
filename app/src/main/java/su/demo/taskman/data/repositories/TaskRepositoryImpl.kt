package su.demo.taskman.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import su.demo.taskman.data.storage.TaskStorage
import su.demo.taskman.domain.entity.TaskItem
import su.demo.taskman.domain.repositories.TaskRepository

class TaskRepositoryImpl(private val taskStorage: TaskStorage) : TaskRepository {


  override fun addTask(taskItem: TaskItem) {
    taskStorage.save(taskItem)
  }

  override fun deleteTask(taskItem: TaskItem) {
    taskStorage.remove(taskItem)
  }

  override fun getTask(taskItemId: TaskItem): TaskItem {
    return taskStorage.getOne(taskItemId)
  }

  override fun getTasksList(): LiveData<List<TaskItem>> {
    return taskStorage.getAll()
  }

  override fun updateTaskStatus(taskItem: TaskItem) {
    return taskStorage.updateStatus(taskItem)
  }
}