package com.example.yieldmobile.data.model

data class TaskListScreenState(
    val tasks: MutableList<TaskSummary>,
    var currentPage: Int,
    var lastPage: Int,
) {
    val hasMore = currentPage < lastPage
}
