package ru.arseniy.lab61_anotherscreen

import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    companion object {
        const val THIEF = "THIEF"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener { _, optionId ->
            val answerIntent = Intent()
            when (optionId) {
                R.id.radioButton1 -> answerIntent.putExtra(THIEF, "Лорд Фаркуад")
                R.id.radioButton2 -> answerIntent.putExtra(THIEF, "Фея Крестная")
                R.id.radioButton3 -> answerIntent.putExtra(THIEF, "Осел")
            }

            setResult(RESULT_OK, answerIntent) // Возвращаем результат
            finish() // Закрываем SecondActivity
        }

        // Обработка insets для корректного отображения
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
