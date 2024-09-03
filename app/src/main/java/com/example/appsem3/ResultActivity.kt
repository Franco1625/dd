package com.example.appsem3

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("SCORE", 0)
        val scoreTextView: TextView = findViewById(R.id.scoreTextView)
        val messageTextView: TextView = findViewById(R.id.messageTextView)

        scoreTextView.text = "Your Score: $score"
        messageTextView.text = "Congratulations! You have completed the quiz."
    }
}