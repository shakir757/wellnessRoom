package com.example.wellnessroomv1.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wellnessroomv1.R
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private val c = Calendar.getInstance()
    private val year = c.get(Calendar.YEAR)
    private var month = c.get(Calendar.MONTH) + 1
    private var day = c.get(Calendar.DAY_OF_MONTH)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_date_main.text = getDate()

        var buttonsCount = 0
        image_button_step_back.setOnClickListener {
            // пилим через костыли
            day--
            text_view_date_main.text = getDate()

            image_button_step_up.visibility = View.VISIBLE
            buttonsCount++

            when (buttonsCount) {
                2 -> {
                    image_button_step_back.visibility = View.INVISIBLE
                    text_view_calories_eaten.text = "Калорий cъедено: 960 kal"
                    text_view_calories_burned.text = "Калорий сожжено: 480 kal"
                }
                1 -> {
                    text_view_calories_eaten.text = "Калорий cъедено: 1233 kal"
                    text_view_calories_burned.text = "Калорий сожжено: 340 kal"
                }
            }
        }

        image_button_step_up.setOnClickListener {
            day++
            text_view_date_main.text = getDate()

            buttonsCount--

            when (buttonsCount) {
                2 -> {
                    image_button_step_back.visibility = View.VISIBLE
                    text_view_calories_eaten.text = "Калорий cъедено: 960 kal"
                    text_view_calories_burned.text = "Калорий сожжено: 480 kal"
                }
                1 -> {
                    image_button_step_back.visibility = View.VISIBLE
                    text_view_calories_eaten.text = "Калорий cъедено: 1233 kal"
                    text_view_calories_burned.text = "Калорий сожжено: 340 kal"
                }

                0 -> {
                    image_button_step_up.visibility = View.INVISIBLE
                    text_view_calories_eaten.text = "Калорий cъедено: 450 kal"
                    text_view_calories_burned.text = "Каллорий сожжено: 139 kal"
                }
            }
        }
    }

    private fun getDate(): String {
        var textDate: String

        // Проверка на форматирование
        textDate = when {
            (day < 10) and (month < 10) -> {
                "0$day.0$month.$year"
            }
            day < 10 -> {
                "0$day.$month.$year"
            }
            month < 10 -> {
                "$day.0$month.$year"
            }
            else -> {
                "$day.$month.$year"
            }
        }

        return textDate
    }
}