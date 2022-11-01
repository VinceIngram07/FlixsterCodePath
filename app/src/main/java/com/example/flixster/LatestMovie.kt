package com.example.flixster

import com.google.gson.annotations.SerializedName

class LatestMovie {
    @SerializedName("poster_path")
    var moiveImageUrl: String? = null

    // @JvmField
    @SerializedName("title")
    var title: String? = null

    @SerializedName("overview")
    var description : String? = null
}