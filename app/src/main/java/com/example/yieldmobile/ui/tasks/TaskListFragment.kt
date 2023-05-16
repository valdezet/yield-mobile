package com.example.yieldmobile.ui.tasks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yieldmobile.R
import com.example.yieldmobile.data.model.TaskListScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private lateinit var listRecycler: RecyclerView
    private lateinit var recyclerAdapter: TaskListRecyclerAdapter
    private val taskListViewModel: TaskListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)
        bindViews(view)
        requireActivity().actionBar?.title = getString(R.string.label_tasks)
        return view
    }

    private fun bindViews(fragmentView: View) {
        listRecycler = fragmentView.findViewById(R.id.task_list_recycler_tasks)
        recyclerAdapter = TaskListRecyclerAdapter(taskListViewModel.stateLiveData.value!!.tasks)
        listRecycler.adapter = recyclerAdapter
        listRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    override fun onStart() {
        super.onStart()
        loadMoreTasks()
        taskListViewModel.stateLiveData.observe(viewLifecycleOwner, viewModelTasksChangedObserver)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val viewModelTasksChangedObserver: Observer<TaskListScreenState> = Observer {
        if (it.tasks.hashCode() != recyclerAdapter.data.hashCode()) {
            recyclerAdapter.data = it.tasks
            recyclerAdapter.notifyDataSetChanged()
        }
    }

    private fun loadMoreTasks() {
        lifecycleScope.launch {
            val itemsAdded = taskListViewModel.loadTasks()
            if (itemsAdded > 0) {
                recyclerAdapter.notifyItemRangeInserted(
                    recyclerAdapter.data.count() - itemsAdded - 1,
                    itemsAdded
                )
            }
        }
    }
}