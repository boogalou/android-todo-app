package su.demo.taskman.presentation

import androidx.recyclerview.widget.DiffUtil
import su.demo.taskman.domain.entity.TaskItem

class TaskListDiff(
  private val previousList: List<TaskItem>,
  private val currentList: List<TaskItem>,
) : DiffUtil.Callback() {
  override fun getOldListSize(): Int {
    return previousList.size
  }

  override fun getNewListSize(): Int {
    return currentList.size
  }

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    val prevItem = previousList[oldItemPosition]
    val currentItem = currentList[newItemPosition]

    return prevItem.id == currentItem.id
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    val prevItem = previousList[oldItemPosition]
    val currentItem = currentList[newItemPosition]

    return prevItem == currentItem
  }

}