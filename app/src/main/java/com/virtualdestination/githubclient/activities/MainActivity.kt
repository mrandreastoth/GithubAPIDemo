package com.virtualdestination.githubclient.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.virtualdestination.githubclient.R
import com.virtualdestination.githubclient.databinding.ActivityMainBinding
import com.virtualdestination.githubclient.fragments.ContributorsFragment

class MainActivity : AppCompatActivity() {

    private var viewBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding!!.root)

        val fm = supportFragmentManager
        fm.beginTransaction().replace(R.id.container, ContributorsFragment()).commit()
    }

    override fun onDestroy() {
        super.onDestroy()

        viewBinding = null

    }
}