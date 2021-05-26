package com.example.instatramtest.img

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.instatramtest.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import kotlin.random.Random

import kotlinx.android.synthetic.main.activity_img.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlinx.coroutines.tasks.await

class img_activity : AppCompatActivity() {
    val CAMERA_REQUEST_CODE=0
    val storage= Firebase.storage
    val storageRef=storage.reference
    var num= Random.nextInt(100000).toString()
    var curFile: Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img)
        val message = intent.getStringExtra("name")
        val textView = findViewById<TextView>(R.id.textView).apply {
            text = message
        }
        //Test connction to firebase
        Toast.makeText(this,"Firebase connection Success", Toast.LENGTH_LONG).show()
        iconcam.setOnClickListener {
            val callCameraIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(callCameraIntent.resolveActivity(packageManager)!=null){
                startActivityIfNeeded(callCameraIntent,CAMERA_REQUEST_CODE)
            }
        }
        downloadImage()
        listFiles()
    }

    @SuppressLint("WrongViewCast")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val message = intent.getStringExtra("name")
        when(requestCode){
            CAMERA_REQUEST_CODE->{
                if(resultCode== Activity.RESULT_OK && data !=null){
                    val bitmap: Bitmap
                    bitmap=data.extras?.get("data") as Bitmap
                    PhotoImageView.setImageBitmap(bitmap)
                    val baos : ByteArrayOutputStream
                    baos= ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
                    val img=baos.toByteArray()

                    val storage= Firebase.storage
                    val storageRef=storage.reference
                    var num= Random.nextInt(100000).toString()
                    var uploadTask=storageRef.child("/"+message+"/"+num).putBytes(img)
                }

            }
            else->{
                Toast.makeText(this,"Unrecongnized request code", Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun listFiles() = CoroutineScope(Dispatchers.IO).launch {
        val message = intent.getStringExtra("name")
        try {
            val images = storageRef.child(message+"/").listAll().await()
            val imageUrls = mutableListOf<String>()
            for(image in images.items) {
                val url = image.downloadUrl.await()
                imageUrls.add(url.toString())
            }

            withContext(Dispatchers.Main) {
                val imageAdapter = ImageAdapter(imageUrls)
                rvImages.apply {
                    adapter = imageAdapter
                    layoutManager = LinearLayoutManager(this@img_activity)
                }
            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@img_activity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
    fun downloadImage() {
        val imageView=findViewById<ImageView>(R.id.stationImage)
        try {
            Glide.with(this)
                .load(storageRef)
                .into(imageView)
        }catch(e: Exception){
            Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()

        }

    }
    }
