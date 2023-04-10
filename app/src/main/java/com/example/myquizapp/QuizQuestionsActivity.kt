package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myquizapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityQuizQuestionsBinding

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mUserName: String? = null
    private var mCorrectAnswer: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mUserName = intent.getStringExtra(Constants.USER_NAME)



        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)


        mQuestionsList = Constants.getQuestions()

        setQuestion()

    }

    private fun setQuestion() {

        defaultOptionsView()
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        binding.ivImage.setImageResource(question.image)
        binding.progressBar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition/${binding.progressBar.max}"
        binding.tvQuestion.text = question.question
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour

        if (mCurrentPosition == mQuestionsList!!.size) {
            binding.btnSubmit.text = "FINISH"
        } else {
            binding.btnSubmit.text = "SUBMIT"
        }


    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        binding.tvOptionOne.let {
            options.add(0, it)
        }
        binding.tvOptionTwo.let {
            options.add(0, it)
        }
        binding.tvOptionThree.let {
            options.add(0, it)
        }
        binding.tvOptionFour.let {
            options.add(0, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_border_bg
            )

        }


    }


    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                binding.tvOptionOne.let {
                    selectedOptionView(it, 1)
                }
            }

            R.id.tv_option_two -> {
                binding.tvOptionTwo.let {
                    selectedOptionView(it, 2)
                }
            }

            R.id.tv_option_three -> {
                binding.tvOptionThree.let {
                    selectedOptionView(it, 3)
                }
            }

            R.id.tv_option_four -> {
                binding.tvOptionFour.let {
                    selectedOptionView(it, 4)
                }
            }

            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                            startActivity(intent)
                            finish()


                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswer ++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)


                    if (mCurrentPosition == mQuestionsList!!.size) {
                        binding.btnSubmit.text = "FINISH"
                    } else {
                        binding.btnSubmit.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0


                }
            }
        }
    }


    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

}