package com.example.instatramtest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*

class AccueilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocate()
        setContentView(R.layout.activity_accueil)

        val btn:Button =findViewById(R.id.btn)
        btn.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setLocate(Lang: String) {
        val locale= Locale(Lang)
        Locale.setDefault(locale)
        val config= Configuration()
        config.locale=locale
        baseContext.resources.updateConfiguration(config,baseContext.resources.displayMetrics)

        val editor=getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang",Lang)
        editor.apply()
    }

    private fun loadLocate(){
        val sharedPreferences=getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language=sharedPreferences.getString("My_Lang","")
        if (language != null) {
            setLocate(language)
        }
    }
}