package com.grifalion.starwars.data.network.dto.people

import com.google.gson.annotations.SerializedName

data class PeopleResultDto(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
    @SerializedName("starships")
    val starships: List<String>? = null
)
