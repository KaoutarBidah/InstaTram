package com.example.instatramtest.ui.main

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.instatramtest.R

class image_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_activity)
        val message = intent.getStringExtra("name")
        val textView = findViewById<TextView>(R.id.textView).apply {
            text = message
        }

    }

}