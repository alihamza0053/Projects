package com.quiz.app.ah

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class result : AppCompatActivity() {
    lateinit var resultText: TextView
    lateinit var regardsText: TextView
    lateinit var regardsText2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultText = findViewById(R.id.resultText)
        regardsText = findViewById(R.id.regardsText)
        regardsText2 = findViewById(R.id.regardsText2)

        val intent = getIntent()
        if (intent.getStringExtra("result")!!.toInt() < 5) {
            resultText.setTextColor(Color.parseColor("#FF0000"))
            resultText.text =
                "You Got: " + intent.getStringExtra("result") + " Points"
            regardsText.text = "Shame! "+ intent.getStringExtra("name")
            regardsText2.text = "Answers will not reveal to you because you can cheat next time.!"

        } else {
            resultText.text = "You Got: " + intent.getStringExtra("result") + " Points"
            regardsText.text = "Excellent! "+ intent.getStringExtra("name")
            regardsText2.text = "No need to reveal answers, you already know them."
        }
    }
}