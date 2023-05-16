package com.example.yieldmobile.data.dto

@SuppressWarnings("unused")
class PaginatedTaskResourceCollectionResponse(
    val data: List<TaskResource>,
    val meta: PaginatedResourceCollectionMeta,
    val links: Map<String, String?>
) {
    constructor() : this(emptyList(), PaginatedResourceCollectionMeta(), emptyMap())
}