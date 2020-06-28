package com.knitterassignment.repository.db.comments

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.knitterassignment.util.Constants.Companion.commentsTableName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = commentsTableName)
data class Comment(@PrimaryKey var id: String, var postId: String, var name: String, var email: String, var body: String): Parcelable