package com.example.musinsa_assignment.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Inventory(
    @JsonProperty("brand_name")
    val brandName: String,

    @JsonProperty("top")
    val top: Int,

    @JsonProperty("outer")
    val outer: Int,

    @JsonProperty("trouser")
    val trouser: Int,

    @JsonProperty("sneaker")
    val sneaker: Int,

    @JsonProperty("bag")
    val bag: Int,

    @JsonProperty("hat")
    val hat: Int,

    @JsonProperty("sock")
    val sock: Int,

    @JsonProperty("accessory")
    val accessory: Int,
)
