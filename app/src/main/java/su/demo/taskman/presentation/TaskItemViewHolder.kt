package su.demo.taskman.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import su.demo.taskman.R

class TaskItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
  val tvTitle: TextView = view.findViewById(R.id.tv_task__title)
  val tvBody: TextView = view.findViewById(R.id.tv_task__body)
  val tvDone: TextView = view.findViewById(R.id.tv_task__done)
  val tvTime: TextView = view.findViewById(R.id.tv_task__time)
}