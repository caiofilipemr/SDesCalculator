package com.daguerreo.crypt.sdescalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.daguerreo.crypt.sdescalculator.sdes.SDesActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openSDesActivity(view: View) {
        startActivity(Intent(this, SDesActivity::class.java))
    }
}
