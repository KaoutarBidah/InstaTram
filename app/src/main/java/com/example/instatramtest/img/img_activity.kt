package com.example.instatramtest.img

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.instatramtest.R

class img_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img)
        val message = intent.getStringExtra("name")
        val textView = findViewById<TextView>(R.id.textView).apply {
            text = message
        }

    }
}