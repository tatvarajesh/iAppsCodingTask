package com.example.iappscodingtask.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.iappscodingtask.model.Items
import com.example.iappscodingtask.model.Media

@Entity
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "link")
    val link: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "published")
    val published: String,

    @ColumnInfo(name = "media")
    val media: String
)

fun PhotoEntity.toItemModel() = Items(
    title = title,
    link = link,
    media = Media(m = media),
    description = description,
    published = published
)