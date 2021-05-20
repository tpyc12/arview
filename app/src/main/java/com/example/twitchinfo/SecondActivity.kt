package com.example.twitchinfo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.twitchinfo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var ui: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        ui = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.buttonSend.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}