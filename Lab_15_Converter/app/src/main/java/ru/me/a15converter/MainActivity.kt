package ru.me.a15converter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val shrekRadioButton: RadioButton = findViewById(R.id.radio_button_shrek)
        val inputEditText: EditText = findViewById(R.id.editText)

        val button: Button = findViewById(R.id.button_converter)
        button.setOnClickListener {
            if (inputEditText.text.isEmpty()) {
                Toast.makeText(
                    applicationContext, "Введите длину кота",
                    Toast.LENGTH_LONG).show()
            }
            else {
                val inputValue = inputEditText.text.toString().toFloat()
                if (shrekRadioButton.isChecked) {
                    inputEditText.setText(convertShrekToBrick(inputValue).toString())
                } else {
                    inputEditText.setText(convertBrickToShrek(inputValue).toString())
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Конвертируем в метры
    private fun convertShrekToBrick(shrek: Float): Float = (shrek * 213 / 5).toFloat()

    // Конвертируем в попугаи
    private fun convertBrickToShrek(brick: Float): Float = (213 / (brick * 5)).toFloat()
}