package su.demo.taskman.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import su.demo.taskman.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var viewModel: MainViewModel
  private  lateinit var taskListAdapter: TaskListAdapter



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
    setupRecyclerView()
    viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    viewModel.taskList.observe(this) { taskListAdapter.taskItemList = it }
  }

  private fun setupRecyclerView() {
    val rvTaskList = binding.rvList
    with(rvTaskList) {
      taskListAdapter = TaskListAdapter()
      adapter = taskListAdapter
      recycledViewPool.setMaxRecycledViews(
        TaskListAdapter.VIEW_TYPE_ENABLED,
        TaskListAdapter.MAX_SIZE_POOL
      )
      recycledViewPool.setMaxRecycledViews(
        TaskListAdapter.VIEW_TYPE_DISABLED,
        TaskListAdapter.MAX_SIZE_POOL
      )
    }

    setupDoneClickListener()

    setupDetailClickListener()

    setupCallbackSwipe(rvTaskList)
  }

  private fun setupCallbackSwipe(rvTaskList: RecyclerView) {
    val callback = object : ItemTouchHelper.SimpleCallback(
      0,
      ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
      override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
      ): Boolean {
        return false
      }

      override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val item = taskListAdapter.taskItemList[viewHolder.adapterPosition]
        viewModel.deleteTask(item)
      }
    }

    val itemTouchHelper = ItemTouchHelper(callback)
    itemTouchHelper.attachToRecyclerView(rvTaskList)
  }

  private fun setupDoneClickListener() {
    taskListAdapter.onDoneClickHandler = {
      viewModel.switchComplete(it)
    }
  }

  private fun setupDetailClickListener() {
    taskListAdapter.onDetailClickHandler = {
      Log.d("TASK_ITEM", "$it")
    }
  }

}