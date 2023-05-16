package com.example.yieldmobile.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yieldmobile.R
import com.example.yieldmobile.data.model.TaskSummary

class TaskListRecyclerAdapter(
    var data: MutableList<TaskSummary>
) : RecyclerView.Adapter<TaskListRecyclerAdapter.TaskListRecyclerViewHolder>() {


    inner class TaskListRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.item_task_list_recycler_name)
        val notesView: TextView = itemView.findViewById(R.id.item_task_list_recycler_notes)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task_list_recycler, parent, false)
        return TaskListRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun onBindViewHolder(holder: TaskListRecyclerViewHolder, position: Int) {
        holder.nameView.text = data[position].name
        holder.notesView.text = data[position].notes ?: "No Notes"
    }
}