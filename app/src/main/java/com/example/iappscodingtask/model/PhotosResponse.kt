package com.example.iappscodingtask.model

import com.google.gson.annotations.SerializedName

data class PhotosResponse(
    @SerializedName("title") val title: String? = null,

    @SerializedName("modified") val modified: String? = null,

    @SerializedName("items") var items: List<Items>? = null
)

data class Items(

    @SerializedName("title") val title: String? = null,

    @SerializedName("link") val link: String? = null,

    @SerializedName("description") val description: String? = null,

    @SerializedName("published") val published: String? = null,

    @SerializedName("media") val media: Media? = null,
)

data class Media(
    @SerializedName("m") val m: String? = null
)
