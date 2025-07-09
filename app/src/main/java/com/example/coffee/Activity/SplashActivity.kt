package com.example.coffee.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.coffee.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding:ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivitySplashBinding.inflate(layoutInflater) //Menghubungkan file XML activity_splash.xml
        setContentView(binding.root)

       binding.startBtn.setOnClickListener { //Saat tombol "Get Started" ditekan, user diarahkan ke MainActivity

           startActivity(Intent(this, MainActivity::class.java))
           finish()

       }

    }
}