package su.demo.taskman.data.storage.db


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import su.demo.taskman.data.storage.TaskStorage
import su.demo.taskman.domain.entity.TaskItem
import kotlin.random.Random

class TaskItemStorage() : TaskStorage {


  private val taskListLD = MutableLiveData<List<TaskItem>>()

  private var taskList = mutableListOf<TaskItem>()
  private var autoIncrementId = 0

  init {
      for (i in 0 until 5)  {
        val item = TaskItem("Title $i", "Body $i", completed = Random.nextBoolean())
        save(item)
      }
  }

  override fun save(taskItem: TaskItem) {
    if (taskItem.id == TaskItem.UNDEFINED_ID) {
      taskItem.id = autoIncrementId++
    }

    taskList.add(taskItem)
    updateList()
  }

  override fun remove(taskItem: TaskItem) {
    taskList.remove(taskItem)
    updateList()
  }

  override fun updateStatus(taskItem: TaskItem) {
    taskList = taskList.map {task ->
      if (task.id == taskItem.id) {
        task.copy(completed = !task.completed)
      } else {
        task
      }
    }.toMutableList()
    updateList()
  }

  override fun getAll(): LiveData<List<TaskItem>> {
    return taskListLD
  }

  override fun getOne(taskItem: TaskItem): TaskItem {
   return taskList.find { it.id == taskItem.id }
     ?: throw RuntimeException("Element with id $taskItem was not found")
  }

  override fun saveList(modifiedTaskList: List<TaskItem>) {
    taskList = modifiedTaskList.toMutableList()
  }

  private fun updateList() {
    taskListLD.value = taskList.toList()
  }
}