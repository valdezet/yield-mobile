package com.example.yieldmobile.data.model

import com.example.yieldmobile.data.dto.TaskResource

data class TaskSummary(
    val id: Long,
    var name: String,
    var notes: String?,
    var isCompleted: Boolean
) {
    companion object {
        @JvmStatic
        fun fromTaskResourceDto(dto: TaskResource): TaskSummary {
            return TaskSummary(
                id = dto.id,
                name = dto.name,
                notes = dto.notes,
                isCompleted = dto.isCompleted
            )
        }
    }
}
