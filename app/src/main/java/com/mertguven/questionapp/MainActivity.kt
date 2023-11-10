package com.mertguven.questionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private val correctAnswer = true
    private var questionAnswered = false
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var tvQuestion: TextView
    private lateinit var tvTimer: TextView
    private lateinit var tvMessage: TextView
    private lateinit var correctImageView: ImageView
    private lateinit var wrongImageView: ImageView
    private lateinit var countDownTimer: CountDownTimer
    private var secondsRemaining: Long? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }
    private fun initUI(){
        tvQuestion = findViewById(R.id.tvQuestion)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        correctImageView = findViewById(R.id.correctImageView)
        wrongImageView = findViewById(R.id.wrongImageView)
        tvTimer = findViewById(R.id.tvTimer)
        tvMessage = findViewById(R.id.tvMessage)

        wrongImageView.visibility = ImageView.GONE
        correctImageView.visibility = ImageView.GONE
        setQuestionText("Which character did the actress in the picture play on the movie series 'Harry Potter'?")
        setMessageText("You have 10 seconds to answer the question")
        startTimer(10000,1000)

        btn1.setOnClickListener {
            if (!questionAnswered) checkAnswer(true)
        }
        btn2.setOnClickListener {
            if (!questionAnswered) checkAnswer(false)
        }
    }

    private fun setQuestionText(question: String) {
        tvQuestion.text = question
    }

    private fun setMessageText(message: String) {
        tvMessage.text = message
    }

    private fun checkAnswer(userAnswer: Boolean){
        questionAnswered = true
        countDownTimer.cancel()
        if (userAnswer == correctAnswer) {
            correctImageView.visibility = ImageView.VISIBLE
            setMessageText("You have answered the question correctly with $secondsRemaining seconds remaining")
            tvTimer.text = ""
        }
        else {
            wrongImageView.visibility = ImageView.VISIBLE
            setMessageText("You have answered the question incorrectly with $secondsRemaining seconds remaining")
            tvTimer.text = ""
        }
    }
    private fun startTimer(duration: Long, interval: Long) {
        countDownTimer = object : CountDownTimer(duration, interval) {
            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished/1000
                tvTimer.text = "$secondsRemaining"
            }
            override fun onFinish() {
                if(!questionAnswered) setMessageText("Time's Up! You didn't answer in time")
            }
        }
        countDownTimer.start()
    }
}