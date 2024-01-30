package su.demo.taskman.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import su.demo.taskman.R
import su.demo.taskman.domain.entity.TaskItem
import java.util.Date

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {


  var taskItemList = listOf<TaskItem>()
    set(value) {
      val callback = TaskListDiff(taskItemList, value)
      val diffResult = DiffUtil.calculateDiff(callback)
      diffResult.dispatchUpdatesTo(this)
      field = value
    }

  private var count = 0


  var onDoneClickHandler: ((TaskItem) -> Unit)? = null
  var onDetailClickHandler: ((TaskItem) -> Unit)? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
    Log.w("CALLED onCreateViewHolder", "count: ${++count}")

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

    return TaskViewHolder(view)
  }

  override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
    val taskItem = taskItemList[position]

    holder.itemView.setOnClickListener {
      onDetailClickHandler?.invoke(taskItem)
    }

    holder.tvDone.setOnClickListener {
      Log.d("OnClick", "Done Button $taskItem")
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
    val item = taskItemList[position]

    return if (item.completed) {
      VIEW_TYPE_ENABLED
    } else {
      VIEW_TYPE_DISABLED
    }
  }

  override fun getItemCount(): Int {
    return taskItemList.size
  }

  class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle: TextView = view.findViewById(R.id.tv_task__title)
    val tvBody: TextView = view.findViewById(R.id.tv_task__body)
    val tvDone: TextView = view.findViewById(R.id.tv_task__done)
    val tvTime: TextView = view.findViewById(R.id.tv_task__time)
  }

  companion object {
    const val VIEW_TYPE_ENABLED = 1
    const val VIEW_TYPE_DISABLED = 2
    const val MAX_SIZE_POOL = 15
  }
}