package su.demo.taskman.data.storage

import androidx.lifecycle.LiveData
import su.demo.taskman.domain.entity.TaskItem

interface TaskStorage {

  fun save(taskItem: TaskItem)

  fun remove(taskItem: TaskItem)

  fun getAll(): LiveData<List<TaskItem>>

  fun getOne(taskItem: TaskItem): TaskItem

  fun updateStatus(taskItem: TaskItem)

  fun saveList(taskList: List<TaskItem>)
}
