package com.knitterassignment.repository.db.posts

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.knitterassignment.util.Constants.Companion.postsTableName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = postsTableName)
data class Post(
    @PrimaryKey var id: String,
    var userID: String,
    var title: String,
    var body: String
) : Parcelable {



    // use for ordering the items in view

    companion object {
        public var DIFF_CALLBACK: ItemCallback<Post> = object : ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id;
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id;
            }
        }
    }
}
