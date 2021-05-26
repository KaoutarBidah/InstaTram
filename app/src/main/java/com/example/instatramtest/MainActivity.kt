package com.example.instatramtest


import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager.widget.ViewPager
import com.example.instatramtest.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocate()
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id:Int=item.itemId
        if(id==R.id.home){
            Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show()
        }
        if(id==R.id.map){
            Toast.makeText(this,"Map",Toast.LENGTH_SHORT).show()
        }
        if(id==R.id.language){
            Toast.makeText(this,"Language",Toast.LENGTH_SHORT).show()
            showChangeLang()
           /* val intent = Intent(this, LanguageActivity::class.java)
             startActivity(intent)*/
        }
        if(id==R.id.theme){
            Toast.makeText(this,"Theme",Toast.LENGTH_SHORT).show()
            showChangeTheme()

        }
        return super.onOptionsItemSelected(item)
    }
    private fun showChangeTheme(){
        val listItems= arrayOf("Light Mode ","Night Mode")
        val mBuilder= AlertDialog.Builder(this )
        mBuilder.setTitle("Change the Theme")
        mBuilder.setSingleChoiceItems(listItems,-1){dialog,which ->
            if(which==0){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                recreate()
            }else if (which==1){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                recreate()
            }
            dialog.dismiss()
        }
        val mDialog=mBuilder.create()
        mDialog.show()
    }

    private fun showChangeLang(){
        val listItems= arrayOf("English","Spanish")
        val mBuilder= AlertDialog.Builder(this )
        mBuilder.setTitle("Change the Language")
        mBuilder.setSingleChoiceItems(listItems,-1){dialog,which ->
            if(which==0){
                setLocate("en")
                recreate()
            }else if (which==1){
                setLocate("es")
                recreate()
            }
            dialog.dismiss()
        }
        val mDialog=mBuilder.create()
        mDialog.show()
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