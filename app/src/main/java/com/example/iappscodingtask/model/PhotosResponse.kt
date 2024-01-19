package com.example.iappscodingtask.model

import com.example.iappscodingtask.data.local.PhotoEntity
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

fun Items.toItemModel() = PhotoEntity(
    title = this.title ?: "",
    link = this.link ?: "",
    media = this.media?.m ?: "",
    description = this.description ?: "",
    published = this.published ?: ""
)

fun List<Items>.toPhotoEntityModelList(): List<PhotoEntity> {
    return this.map {
        it.toItemModel()
    }
}
