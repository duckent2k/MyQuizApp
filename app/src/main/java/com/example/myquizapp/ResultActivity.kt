package com.example.myquizapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myquizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvName.text = intent.getStringExtra(Constants.USER_NAME)

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswer = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)


        binding.tvScore.text = "Your Score is $correctAnswer out of $totalQuestions"

        binding.btnFinish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }


    }


}