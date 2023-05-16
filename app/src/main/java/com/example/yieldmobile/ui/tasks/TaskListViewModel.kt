package com.example.yieldmobile.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.yieldmobile.data.TaskRepository
import com.example.yieldmobile.data.model.TaskListScreenState
import com.example.yieldmobile.data.model.TaskSummary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {
    private val _dispatcher = Dispatchers.IO

    private var _stateLiveData = MutableLiveData(TaskListScreenState(mutableListOf(), 0, 1))
    val stateLiveData: LiveData<TaskListScreenState> = _stateLiveData

    suspend fun loadTasks(): Int {
        val state = stateLiveData.value!!
        if (!state.hasMore) return 0
        state.currentPage += 1
        return withContext(_dispatcher) {
            val response = taskRepository.getPaginatedItems(state.currentPage)
            val addedTasks = response.data
            state.lastPage = response.meta.last
            state.tasks.addAll(addedTasks.map { TaskSummary.fromTaskResourceDto(it) })
            withContext(Dispatchers.Main) {
                _stateLiveData.value = state
            }
            return@withContext addedTasks.count()
        }


    }
}