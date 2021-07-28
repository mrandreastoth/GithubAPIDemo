package com.virtualdestination.githubclient.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.virtualdestination.githubclient.R
import com.virtualdestination.githubclient.fragments.ContributorsFragment

class ContributorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        val fm = supportFragmentManager
        fm.beginTransaction().replace(R.id.container, ContributorsFragment()).commit()
    }
}