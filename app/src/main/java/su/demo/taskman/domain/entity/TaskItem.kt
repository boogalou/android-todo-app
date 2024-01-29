package su.demo.taskman.domain.entity

data class TaskItem(
  val title: String,
  val body: String,
  var completed: Boolean,
  var id: Int = UNDEFINED_ID,
) {
  companion object {
    const val UNDEFINED_ID = -1
  }
}
