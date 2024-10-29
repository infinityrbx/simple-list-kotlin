package com.example.simple_list

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNumber: EditText
    private lateinit var rbEven: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var lvNumbers: ListView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNumber = findViewById(R.id.et_number)
        rbEven = findViewById(R.id.rb_even)
        rbOdd = findViewById(R.id.rb_odd)
        rbSquare = findViewById(R.id.rb_square)
        btnShow = findViewById(R.id.btn_show)
        lvNumbers = findViewById(R.id.lv_numbers)
        tvError = findViewById(R.id.tv_error)

        btnShow.setOnClickListener {
            tvError.text = ""
            val input = etNumber.text.toString()
            if (input.isNotEmpty()) {
                val n = input.toIntOrNull()
                if (n != null && n > 0) {
                    val numbers = when {
                        rbEven.isChecked -> getEvenNumbers(n)
                        rbOdd.isChecked -> getOddNumbers(n)
                        rbSquare.isChecked -> getSquareNumbers(n)
                        else -> listOf()
                    }
                    lvNumbers.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
                } else {
                    tvError.text = "Vui lòng nhập số nguyên dương."
                }
            } else {
                tvError.text = "Vui lòng nhập số."
            }
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        return (0..n).mapNotNull {
            val sqrt = kotlin.math.sqrt(it.toDouble()).toInt()
            if (sqrt * sqrt == it) it else null
        }
    }
}