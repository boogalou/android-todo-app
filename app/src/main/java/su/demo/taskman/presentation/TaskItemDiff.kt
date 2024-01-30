package su.demo.taskman.presentation

import androidx.recyclerview.widget.DiffUtil
import su.demo.taskman.domain.entity.TaskItem

class TaskItemDiff : DiffUtil.ItemCallback<TaskItem>() {
  override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
    return oldItem == newItem
  }

}