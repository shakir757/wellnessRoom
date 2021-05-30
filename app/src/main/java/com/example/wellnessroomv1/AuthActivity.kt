package com.example.wellnessroomv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.withStyledAttributes
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        button_auth.setOnClickListener {
            // полные костыли если что
            if (edit_text_phone.text.isEmpty()) {
                Toast.makeText(this, "Пожалуйста введите номер телефона!", Toast.LENGTH_SHORT).show()
            } else if (edit_text_phone.text.length < 11) {
                Toast.makeText(this, "Введите корректный номер!", Toast.LENGTH_SHORT).show()
            } else if (edit_text_phone_code.text.isEmpty()){
                Toast.makeText(this, "Пожалуйста введите код!", Toast.LENGTH_SHORT).show()
            } else {
                progressBar.visibility = View.VISIBLE
                val intent = Intent(this, MainActivity::class.java)
                // таймер блять, типо загрузка
                val timer = object: CountDownTimer(500, 500){
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                        startActivity(intent)
                        overridePendingTransition(R.anim.anim_right, R.anim.anim_alpha)
                    }
                }
                timer.start()
            }
        }

        image_button_message.setOnClickListener {
            edit_text_phone_code.visibility = View.VISIBLE
            val anim = AnimationUtils.loadAnimation(this, R.anim.anim_edit_text_number_code)
            edit_text_phone_code.startAnimation(anim)
        }
    }
}