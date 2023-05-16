package com.example.yieldmobile.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TaskResource(
    @JsonProperty("id")
    val id: Long,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("notes")
    val notes: String?,

    @JsonProperty("is_completed")
    val isCompleted: Boolean,

    @JsonProperty("completed_at")
    val completedAtString: String?
) {
}