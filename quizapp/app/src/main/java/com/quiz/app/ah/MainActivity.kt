package com.quiz.app.ah

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var name: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById(R.id.nameText)


    }

    fun startBtn(view: View) {
        var text = name.text.toString()
        if(text.length<3){
            Toast.makeText(this, "name too short", Toast.LENGTH_SHORT).show()
        }else{
            val intent = Intent(this,quiz::class.java)
            intent.putExtra("name",name.text.toString())
            startActivity(intent)
            finish()
        }

    }

}


