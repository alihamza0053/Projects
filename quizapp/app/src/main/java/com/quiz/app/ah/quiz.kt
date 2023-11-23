package com.quiz.app.ah

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import kotlin.time.Duration.Companion.microseconds
import kotlin.time.Duration.Companion.seconds

class quiz : AppCompatActivity() {
    //declaring variables
    lateinit var timer: CountDownTimer
    lateinit var questionsText: TextView
    lateinit var option1Text: TextView
    lateinit var option2Text: TextView
    lateinit var option3Text: TextView
    lateinit var option4Text: TextView
    lateinit var quizTimeText: TextView
    lateinit var quizNoText: TextView
    lateinit var submitBtn: MaterialButton
    lateinit var firstOption: LinearLayout
    lateinit var secondOption: LinearLayout
    lateinit var thireOption: LinearLayout
    lateinit var fourthOption: LinearLayout

    //name variable store the name of user which he added at start screen
    lateinit var name: String
    //optionCheck variable for string the selected option to check is it correct or wrong
    var optionCheck = ""
    //totalQuestions variable count the questions
    var totalQuestions = 0
    //sum variable show the total sum of correct answers
    var sum = 0

    //array of questions, options and correct answers
    var questions = arrayOf(
        "1. Who is the father of Computers?",
        "2. What is the full form of CPU?",
        "3. Which of the following is the brain of the computer?",
        "4. Which of the following part of a processor contains the hardware necessary to perform all the operations required by a computer?",
        "5. Which of the following is used in EBCDIC?",
        "6. Which of the following defines the assigned ordering among the characters used by the computer?",
        "7. Which of the following are the input devices that enable direct data entry into a computer system from source documents?"
    )
    var option1 = arrayOf(
        "James Gosling",
        "Computer Processing Unit",
        "Central Processing Unit",
        "Controller",
        "Super Computers",
        "Accumulation",
        "System Access devices"
    )
    var option2 = arrayOf(
        "Charles Babbage",
        "Computer Principle Unit",
        "Memory",
        "Registers",
        "Mainframes",
        "Sorting",
        "Data acquiring devices"
    )
    var option3 = arrayOf(
        "Dennis Ritchie",
        "Central Processing Unit",
        "Arithmetic and Logic unit",
        "Cache",
        "Machine Codes",
        "Collating Sequence",
        "Data retrieving devices"
    )
    var option4 = arrayOf(
        "Bjarne Stroustrup",
        "Control Processing Unit",
        "Control unit",
        "Data path",
        "Programming",
        "Unicode",
        "Data Scanning devices"
    )
    var answers = arrayOf(
        "Charles Babbage",
        "Central Processing Unit",
        "Central Processing Unit",
        "Data path",
        "Mainframes",
        "Collating Sequence",
        "Data Scanning devices"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //getting name string from the start screen
        val intentName = getIntent()
        name = intentName.getStringExtra("name")!!.toString()
        Toast.makeText(this, "Welcome " + name, Toast.LENGTH_SHORT).show()


        //getting views form xml by using id
        questionsText = findViewById(R.id.questionText)
        option1Text = findViewById(R.id.option1Text)
        option2Text = findViewById(R.id.option2Text)
        option3Text = findViewById(R.id.option3Text)
        option4Text = findViewById(R.id.option4Text)
        quizTimeText = findViewById(R.id.quizTimeText)
        quizNoText = findViewById(R.id.quizNoText)
        submitBtn = findViewById(R.id.submitBtnId)
        firstOption = findViewById(R.id.firstOptionId)
        secondOption = findViewById(R.id.secondOptionId)
        thireOption = findViewById(R.id.thirdOptionId)
        fourthOption = findViewById(R.id.fourthOptionId)

        //setting time for 100 seconds
        timer = object : CountDownTimer(100000, 1) {
            override fun onTick(millisUntilFinished: Long) {
                quizTimeText.text = "Time: " + (millisUntilFinished / 1000).toString() + "s"
            }

            override fun onFinish() {

                //if time finish the quiz will automatically end.
                Toast.makeText(this@quiz, "Times end!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@quiz, result::class.java)
                intent.putExtra("result", sum.toString())
                intent.putExtra("name", name)
                startActivity(intent)
                finish()
            }
        }
        timer.start()


        //setting first question on app start
        questionsText.text = questions[0]
        option1Text.text = option1[0]
        option2Text.text = option2[0]
        option3Text.text = option3[0]
        option4Text.text = option4[0]

        //option selection onClickListener start
        //first option layout
        firstOption.setOnClickListener(View.OnClickListener() {
            firstOption.setBackgroundResource(R.drawable.selected_option_shape)
            secondOption.setBackgroundResource(R.drawable.option_shape)
            thireOption.setBackgroundResource(R.drawable.option_shape)
            fourthOption.setBackgroundResource(R.drawable.option_shape)
            optionCheck = option1Text.text.toString()
        })
        //second option layout
        secondOption.setOnClickListener(View.OnClickListener() {
            firstOption.setBackgroundResource(R.drawable.option_shape)
            secondOption.setBackgroundResource(R.drawable.selected_option_shape)
            thireOption.setBackgroundResource(R.drawable.option_shape)
            fourthOption.setBackgroundResource(R.drawable.option_shape)
            optionCheck = option2Text.text.toString()
        })
        //third option layout
        thireOption.setOnClickListener(View.OnClickListener() {
            firstOption.setBackgroundResource(R.drawable.option_shape)
            secondOption.setBackgroundResource(R.drawable.option_shape)
            thireOption.setBackgroundResource(R.drawable.selected_option_shape)
            fourthOption.setBackgroundResource(R.drawable.option_shape)
            optionCheck = option3Text.text.toString()
        })
        //fourth option layout
        fourthOption.setOnClickListener(View.OnClickListener() {
            firstOption.setBackgroundResource(R.drawable.option_shape)
            secondOption.setBackgroundResource(R.drawable.option_shape)
            thireOption.setBackgroundResource(R.drawable.option_shape)
            fourthOption.setBackgroundResource(R.drawable.selected_option_shape)
            optionCheck = option4Text.text.toString()
        })
        //option selection onClickListener end
    }


    //button function for next option and submition
    fun submitBtn(view: View) {
        if (optionCheck != "") {
            firstOption.setBackgroundResource(R.drawable.option_shape)
            secondOption.setBackgroundResource(R.drawable.option_shape)
            thireOption.setBackgroundResource(R.drawable.option_shape)
            fourthOption.setBackgroundResource(R.drawable.option_shape)
            if (optionCheck == answers[totalQuestions]) {

                //if answers correct 1 point added
                sum++
            }
            //making empty the answers variable
            optionCheck = ""

            //counting the questions
            totalQuestions++

            //setting next questions and options
            when (totalQuestions) {
                1 -> {
                    questionsText.text = questions[1]
                    option1Text.text = option1[1]
                    option2Text.text = option2[1]
                    option3Text.text = option3[1]
                    option4Text.text = option4[1]
                    quizNoText.text = "Quiz: 2/7"
                }

                2 -> {
                    questionsText.text = questions[2]
                    option1Text.text = option1[2]
                    option2Text.text = option2[2]
                    option3Text.text = option3[2]
                    option4Text.text = option4[2]
                    quizNoText.text = "Quiz: 3/7"
                }

                3 -> {
                    questionsText.text = questions[3]
                    option1Text.text = option1[3]
                    option2Text.text = option2[3]
                    option3Text.text = option3[3]
                    option4Text.text = option4[3]
                    quizNoText.text = "Quiz: 4/7"
                }

                4 -> {
                    questionsText.text = questions[4]
                    option1Text.text = option1[4]
                    option2Text.text = option2[4]
                    option3Text.text = option3[4]
                    option4Text.text = option4[4]
                    quizNoText.text = "Quiz: 5/7"
                }

                5 -> {
                    questionsText.text = questions[5]
                    option1Text.text = option1[5]
                    option2Text.text = option2[5]
                    option3Text.text = option3[5]
                    option4Text.text = option4[5]
                    quizNoText.text = "Quiz: 6/7"
                }

                6 -> {
                    questionsText.text = questions[6]
                    option1Text.text = option1[6]
                    option2Text.text = option2[6]
                    option3Text.text = option3[6]
                    option4Text.text = option4[6]
                    submitBtn.text = "Submit"
                    quizNoText.text = "Quiz: 7/7"


                }

                //finishing the testing at the end of questions
                else -> {
                    timer.cancel()
                    Toast.makeText(this, sum.toString(), Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, result::class.java)
                    intent.putExtra("result", sum.toString())
                    intent.putExtra("name", name)
                    startActivity(intent)
                    finish()

                }
            }
        } else {
            //in case of no option select by user
            Toast.makeText(this, "Select an option first.", Toast.LENGTH_SHORT).show()
        }
    }
}

