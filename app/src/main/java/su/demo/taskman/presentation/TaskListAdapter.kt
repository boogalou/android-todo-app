package su.demo.taskman.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import su.demo.taskman.R
import su.demo.taskman.domain.entity.TaskItem
import java.util.Date

class TaskListAdapter : ListAdapter<TaskItem, TaskItemViewHolder>(TaskItemDiff()) {

  var onDoneClickHandler: ((TaskItem) -> Unit)? = null
  var onDetailClickHandler: ((TaskItem) -> Unit)? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {

    val layout = when (viewType) {
      VIEW_TYPE_ENABLED -> R.layout.item_layout_enable
      VIEW_TYPE_DISABLED -> R.layout.item_layout
      else -> throw RuntimeException("Unknown TYPE $viewType")
    }

    val view = LayoutInflater
      .from(parent.context)
      .inflate(
        layout,
        parent,
        false
      )

    return TaskItemViewHolder(view)
  }

  override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
    val taskItem = getItem(position)

    holder.itemView.setOnClickListener {
      onDetailClickHandler?.invoke(taskItem)
    }

    holder.tvDone.setOnClickListener {
      onDoneClickHandler?.invoke(taskItem)
    }

    holder.tvTitle.text = taskItem.title
    holder.tvBody.text = taskItem.body
    holder.tvTime.text = Date().toString()

    if (taskItem.completed) {
      holder.tvDone.setTextColor(0xFFffffff.toInt())
      holder.tvDone.setBackgroundResource(R.drawable.rounded_background_done)
    } else {
      holder.tvDone.setTextColor(0xFFAB94FF.toInt())
      holder.tvDone.setBackgroundResource(R.drawable.rounded_background_default)
    }
  }

  override fun getItemViewType(position: Int): Int {
    val item = getItem(position)

    return if (item.completed) {
      VIEW_TYPE_ENABLED
    } else {
      VIEW_TYPE_DISABLED
    }
  }
  companion object {
    const val VIEW_TYPE_ENABLED = 1
    const val VIEW_TYPE_DISABLED = 2
    const val MAX_SIZE_POOL = 15
  }
}