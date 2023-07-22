package com.grifalion.starwars.data.network.dto.starship

import com.google.gson.annotations.SerializedName

data class StarshipResultDto (
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("model")
    val model: String? = null,
    @SerializedName("manufacturer")
    val manufacturer: String? = null,
    @SerializedName("passengers")
    val passengers: String? = null,
)