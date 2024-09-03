package com.example.appsem3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val questions = listOf(
        "What is the capital of France?" to listOf("Paris", "London", "Berlin", "Madrid"),
        "What is 2 + 2?" to listOf("3", "4", "5", "6"),
        "What is the largest planet?" to listOf("Earth", "Mars", "Jupiter", "Saturn")
    )
    private val correctAnswers = listOf("Paris", "4", "Jupiter")
    private var currentQuestionIndex = 0
    private var score = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val questionTextView: TextView = findViewById(R.id.questionTextView)
        val answersRadioGroup: RadioGroup = findViewById(R.id.answersRadioGroup)
        val submitButton: Button = findViewById(R.id.submitButton)

        loadQuestion(questionTextView, answersRadioGroup)

        submitButton.setOnClickListener {
            val selectedRadioButtonId = answersRadioGroup.checkedRadioButtonId
            if (selectedRadioButtonId != -1) {
                val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
                val selectedAnswer = selectedRadioButton.text.toString()
                checkAnswer(selectedAnswer)
                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                    loadQuestion(questionTextView, answersRadioGroup)
                } else {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("SCORE", score)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadQuestion(questionTextView: TextView, answersRadioGroup: RadioGroup) {
        val (question, answers) = questions[currentQuestionIndex]
        questionTextView.text = question
        answersRadioGroup.removeAllViews()
        answers.forEach { answer ->
            val radioButton = RadioButton(this)
            radioButton.text = answer
            answersRadioGroup.addView(radioButton)
        }
    }

    private fun checkAnswer(selectedAnswer: String) {
        if (selectedAnswer == correctAnswers[currentQuestionIndex]) {
            score++
        }
    }
}