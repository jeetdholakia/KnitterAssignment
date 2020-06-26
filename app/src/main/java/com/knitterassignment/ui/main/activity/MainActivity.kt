package com.knitterassignment.ui.main.activity

import android.os.Bundle
import com.knitterassignment.R
import com.knitterassignment.ui.main.fragment.TimelineFragment
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerFragment

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpFragment(TimelineFragment())
    }

    private fun setUpFragment(fragment: DaggerFragment) {
        fragment.arguments = intent.extras
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentHolder, fragment)
        transaction.commit()
    }
}