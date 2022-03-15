package com.briggittelew.wikistarswars

import com.google.gson.annotations.SerializedName

data class PeopleResult(
    @SerializedName("cuenta") var count: Int,
    @SerializedName("siguiente") var next: String,
    @SerializedName("anterior") var previous: String,
    @SerializedName("resultado") var results: List<String>)

//data class PeopleResult(val result: List<People>)

/*data class PeopleResult(
    @SerializedName("nombre") val name: String,
    @SerializedName("altura") val height: Int,
    @SerializedName("nacimiento") val birth_year: String,
    @SerializedName("peliculas") val films: List<String>)*/