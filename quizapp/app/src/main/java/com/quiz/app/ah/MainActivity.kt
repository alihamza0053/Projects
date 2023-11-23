package com.quiz.app.ah

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //declaring variables
    lateinit var name: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getting view form xml by using id
        name = findViewById(R.id.nameText)
    }

    //function for start button
    fun startBtn(view: View) {
        var text = name.text.toString()
        if (text.length < 3) {
            Toast.makeText(this, "name too short", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, quiz::class.java)
            intent.putExtra("name", name.text.toString())
            startActivity(intent)
            finish()
        }

    }

}


