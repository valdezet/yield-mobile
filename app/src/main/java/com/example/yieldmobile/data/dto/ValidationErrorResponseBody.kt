package com.example.yieldmobile.data.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

class ValidationErrorResponseBody(
    @JsonProperty("errors")
    val errors: JsonNode,

    @JsonProperty("message")
    val message: String
)