package com.example.dwkim.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dwkim.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragments()
    }

    private fun initFragments(){
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fl_aMain_container,
                fragment,
                tag
            )
            .commit()
    }
}
