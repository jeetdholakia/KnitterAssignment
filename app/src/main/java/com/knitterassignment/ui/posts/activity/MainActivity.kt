package com.knitterassignment.ui.posts.activity

import android.os.Bundle
import com.knitterassignment.R
import com.knitterassignment.ui.comments.CommentsFragment
import com.knitterassignment.ui.posts.fragment.TimelineFragment
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerFragment

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpFragment(TimelineFragment(), false)
    }

    private fun setUpFragment(fragment: DaggerFragment, isBackRequired: Boolean) {
        fragment.arguments = intent.extras
        val transaction = supportFragmentManager.beginTransaction()
        if(isBackRequired) {
            transaction.addToBackStack(fragment.javaClass.name)
        }
        transaction.replace(R.id.fragmentHolder, fragment)
        transaction.commit()
    }

    fun postFragmentClicked() {
        setUpFragment(CommentsFragment(), true)
    }
}