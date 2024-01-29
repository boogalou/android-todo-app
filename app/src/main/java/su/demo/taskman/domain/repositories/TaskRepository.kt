package su.demo.taskman.domain.repositories

import androidx.lifecycle.LiveData
import su.demo.taskman.domain.entity.TaskItem

interface TaskRepository {

  fun addTask(taskItem: TaskItem)

  fun deleteTask(taskItem: TaskItem)

  fun getTask(taskItem: TaskItem): TaskItem

  fun getTasksList(): LiveData<List<TaskItem>>

  fun updateTaskStatus(taskItem: TaskItem)

}