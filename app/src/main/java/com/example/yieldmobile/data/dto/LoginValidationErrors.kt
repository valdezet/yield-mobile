package com.example.yieldmobile.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

class LoginValidationErrors(
    @JsonProperty("email")
    val email: List<String>?,
    @JsonProperty("password")
    val password: List<String>?,
    @JsonProperty("device_name")
    val deviceName: List<String>?
) {
    constructor() : this(emptyList(), emptyList(), emptyList())
}