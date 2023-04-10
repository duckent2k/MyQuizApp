package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myquizapp.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnStart.setOnClickListener {

            if (binding.etName.text!!.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this@MainActivity, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, binding.etName.text.toString())
                startActivity(intent)
                finish()

            }

        }
    }

}