package com.syukron.submission1_bajp_dicoding.data.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tv_show_entities")
@Parcelize
data class EntityTvShow(
    @ColumnInfo(name = "title")
    @SerializedName("original_name")
    var title: String = "",

    @ColumnInfo(name = "releasedate")
    @SerializedName("first_air_date")
    var releaseDate: String = "",

    @ColumnInfo(name = "score")
    @SerializedName("vote_average")
    var score: String = "",

    @ColumnInfo(name = "description")
    @SerializedName("overview")
    var description: String = "",

    @ColumnInfo(name = "imagePath")
    @SerializedName("poster_path")
    var imagePath: String = "",

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String = ""
) : Parcelable