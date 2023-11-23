package com.quiz.app.ah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //declaring variables
    lateinit var name: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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


