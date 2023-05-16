package com.example.yieldmobile.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

@SuppressWarnings("unused")
class PaginatedResourceCollectionMeta(
    @JsonProperty("current_page")
    val currentPage: Int,

    @JsonProperty("from")
    val from: Int,

    @JsonProperty("last_page")
    val last: Int,

    @JsonProperty("links")
    val links: List<Map<String, String?>>,

    @JsonProperty("path")
    val path: String,

    @JsonProperty("per_page")
    val itemsPerPage: Int,

    @JsonProperty("to")
    val to: Int,

    @JsonProperty("total")
    val total: Int
) {
    constructor() : this(0, 0, 0, emptyList(), "", 0, 0, 0)
}