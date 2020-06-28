package com.knitterassignment.repository.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.knitterassignment.util.Constants.Companion.tableName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = tableName)
data class Post(
    @PrimaryKey var id: String,
    var userID: String,
    var title: String,
    var body: String
) : Parcelable